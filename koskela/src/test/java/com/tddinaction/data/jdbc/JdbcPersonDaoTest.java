package com.tddinaction.data.jdbc;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;

import com.mockobjects.sql.MockMultiRowResultSet;
import com.tddinaction.data.person.Person;

public class JdbcPersonDaoTest {

    @Test
    public void testFindByLastname() throws Exception {
        DataSource datasource = createMock(DataSource.class);
        Connection connection = createMock(Connection.class);
        expect(datasource.getConnection()).andReturn(connection);

        String sql = "SELECT * FROM people WHERE last_name = ?";
        PreparedStatement stmt = createMock(PreparedStatement.class);
        expect(connection.prepareStatement(sql)).andReturn(stmt);
        stmt.setString(1, "Smith");

        MockMultiRowResultSet resultset = new MockMultiRowResultSet();
        String[] columns = new String[] { "first_name", "last_name" };
        resultset.setupColumnNames(columns);
        List<Person> smiths = createListOfPeopleWithLastname("Smith");
        resultset.setupRows(asResultSetArray(smiths));
        expect(stmt.executeQuery()).andReturn(resultset);

        resultset.setExpectedCloseCalls(1);
        stmt.close();
        connection.close();

        replay(datasource, connection, stmt);

        JdbcPersonDao dao = new JdbcPersonDao();
        dao.setDatasource(datasource);
        List<Person> people = dao.findByLastname("Smith");

        assertEquals(smiths, people);
        verify(datasource, connection, stmt);
        resultset.verify();
    }

    private List<Person> createListOfPeopleWithLastname(
            String lastName) {
        List<Person> expected = new ArrayList<Person>();
        expected.add(new Person("Alice", lastName));
        expected.add(new Person("Billy", lastName));
        expected.add(new Person("Clark", lastName));
        return expected;
    }

    private Object[][] asResultSetArray(List<Person> people) {
        Object[][] array = new Object[people.size()][2];
        for (int i = 0; i < array.length; i++) {
            Person person = people.get(i);
            array[i] = new Object[] { person.getFirstname(),
                    person.getLastname() };
        }
        return array;
    }
}
