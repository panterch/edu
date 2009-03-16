package com.tddinaction.data.jdbc;

import javax.sql.*;
import java.sql.*;
import java.util.*;

import com.tddinaction.data.PersonDao;
import com.tddinaction.data.person.Person;

public class JdbcPersonDao implements PersonDao {

    private DataSource datasource;

    public void setDatasource(DataSource datasource) {
        this.datasource = datasource;
    }

    public List<Person> findByLastname(String lastname) {
        try {
            Connection conn = datasource.getConnection();
            String sql = "SELECT * FROM people WHERE last_name = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, lastname);
            ResultSet rset = stmt.executeQuery();
            List<Person> people = new ArrayList<Person>();
            while (rset.next()) {
                String firstName = rset.getString("first_name");
                String lastName = rset.getString("last_name");
                people.add(new Person(firstName, lastName));
            }
            rset.close();
            stmt.close();
            conn.close();
            return people;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Person person) {
    }

    public Person find(Long id) {
        return null;
    }

    public List<Person> findAll() {
        return null;
    }
}
