package com.tddinaction.data.spring.jdbc;

import static org.easymock.EasyMock.aryEq;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.isA;
import static org.easymock.classextension.EasyMock.createMock;
import static org.easymock.classextension.EasyMock.replay;
import static org.easymock.classextension.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.tddinaction.data.person.Person;

public class JdbcTemplatePersonDaoTest {

    @Test
    public void testFindByLastname() throws Exception {
        final String lastName = "Smith";
        final List<Person> smiths = createListOfPeopleWithLastname(lastName);

        JdbcTemplate template = createMock(JdbcTemplate.class);
        template.query(
                eq("SELECT * FROM employee WHERE last_name = ?"),
                aryEq(new Object[] { lastName }),
                isA(PersonRowMapper.class));
        expectLastCall().andReturn(smiths);

        replay(template);

        JdbcTemplatePersonDao dao = new JdbcTemplatePersonDao();
        dao.setJdbcTemplate(template);
        assertEquals(smiths, dao.findByLastname(lastName));

        verify(template);
    }

    private List<Person> createListOfPeopleWithLastname(
            String lastName) {
        List<Person> expectedList = new ArrayList<Person>();
        expectedList.add(new Person("Alice", lastName));
        expectedList.add(new Person("Billy", lastName));
        expectedList.add(new Person("Clark", lastName));
        return expectedList;
    }

    @Test
    public void testFindAll() throws Exception {
        List<Person> expected = createListOfPeopleWithLastname("Smith");
        JdbcTemplate template = createMock(JdbcTemplate.class);
        template.query(eq("SELECT * FROM employee"),
                aryEq(new Object[0]), isA(PersonRowMapper.class));
        expectLastCall().andReturn(expected);

        replay(template);

        JdbcTemplatePersonDao dao = new JdbcTemplatePersonDao();
        dao.setJdbcTemplate(template);
        assertEquals(expected, dao.findAll());

        verify(template);
    }
}
