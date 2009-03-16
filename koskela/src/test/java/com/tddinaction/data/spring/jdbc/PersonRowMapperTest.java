package com.tddinaction.data.spring.jdbc;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.jdbc.core.RowMapper;

import com.mockobjects.sql.MockSingleRowResultSet;
import com.tddinaction.data.person.Person;
import com.tddinaction.data.spring.jdbc.PersonRowMapper;

import junit.framework.JUnit4TestAdapter;
import static org.junit.Assert.*;

public class PersonRowMapperTest {

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(PersonRowMapperTest.class);
    }

    @Test
    public void testMappingRow() throws Exception {
        Person expected = new Person("John", "Doe");

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("first_name", expected.getFirstname());
        data.put("last_name", expected.getLastname());
        MockSingleRowResultSet rs = new MockSingleRowResultSet();
        rs.addExpectedNamedValues(data);

        RowMapper mapper = new PersonRowMapper();
        assertEquals(expected, mapper.mapRow(rs, 1));
    }
}
