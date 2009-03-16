package com.tddinaction.data.spring.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.tddinaction.data.person.Person;

public class PersonRowMapper implements RowMapper {

    public Object mapRow(ResultSet rs, int rowNum)
            throws SQLException {
        return new Person(rs.getString("first_name"), rs
                .getString("last_name"));
    }
}
