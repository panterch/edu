package com.tddinaction.data.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tddinaction.data.PersonDao;
import com.tddinaction.data.person.Person;

public class HibernatePersonDao implements PersonDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @SuppressWarnings("unchecked")
    public List<Person> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from Person p";
        Query query = session.createQuery(hql);
        List people = query.list();
        return people;
    }

    @SuppressWarnings("unchecked")
    public List<Person> findByLastname(String name) {
        try {
            Session session = sessionFactory.getCurrentSession();
            String hql = "from Person p where p.lastname = :lastname";
            Query query = session.createQuery(hql);
            query.setParameter("lastname", name);
            return query.list();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.save(person);
        session.flush();
    }

    public Person find(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Person) session.get(Person.class, id);
    }
}
