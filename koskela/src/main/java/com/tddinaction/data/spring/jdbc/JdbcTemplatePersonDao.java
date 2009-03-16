package com.tddinaction.data.spring.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.tddinaction.data.PersonDao;
import com.tddinaction.data.person.Person;

public class JdbcTemplatePersonDao extends JdbcDaoSupport implements
        PersonDao {

    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        return getJdbcTemplate().query("SELECT * FROM employee",
                new Object[0], new PersonRowMapper());
    }

    @SuppressWarnings("unchecked")
    public List<Person> findByLastName(String lastName) {
        String sql = "SELECT * FROM employee WHERE last_name = ?";
        Object[] args = { lastName };
        RowMapper mapper = new PersonRowMapper();
        return getJdbcTemplate().query(sql, args, mapper);
    }

    @SuppressWarnings("unchecked")
    public List<Person> findByLastname(String lastname) {
        String sql = "SELECT * FROM employee WHERE last_name = ?";
        String[] args = new String[] { lastname };
        RowMapper mapper = new PersonRowMapper();
        return getJdbcTemplate().query(sql, args, mapper);
    }

    public void save(final Person person) {
        getJdbcTemplate()
                .execute(
                        "INSERT INTO employee (employee_uid, start_date, first_name, last_name, ssn) VALUES (?, NOW, ?, ?, ?)",
                        new PreparedStatementCallback() {

                            public Object doInPreparedStatement(
                                    PreparedStatement ps)
                                    throws SQLException,
                                    DataAccessException {
                                ps.setInt(1, 4);
                                ps
                                        .setString(2, person
                                                .getFirstname());
                                ps.setString(3, person.getLastname());
                                ps.setString(4, person.getSsn());
                                ps.executeUpdate();
                                return null;
                            }
                        });
    }

    public Person find(Long id) {
        return null;
    }

}
