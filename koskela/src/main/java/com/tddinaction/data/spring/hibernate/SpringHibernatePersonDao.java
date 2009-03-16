package com.tddinaction.data.spring.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.tddinaction.data.PersonDao;
import com.tddinaction.data.person.Person;

public class SpringHibernatePersonDao extends HibernateDaoSupport implements
        PersonDao {

    public List<Person> findAll() {
        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Person> findByLastname(String lastname) {
        return getHibernateTemplate()
                .find("from Person p where p.lastname = ?",
                        new Object[] { lastname });
    }

    public void save(Person person) {
    }

    public Person find(Long id) {
        return null;
    }

}
