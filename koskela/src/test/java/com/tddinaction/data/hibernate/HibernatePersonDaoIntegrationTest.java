package com.tddinaction.data.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tddinaction.data.hibernate.util.HibernateIntegrationTestCase;
import com.tddinaction.data.person.Person;

public class HibernatePersonDaoIntegrationTest extends
        HibernateIntegrationTestCase {

    private SessionFactory sf;

    private Transaction tx;

    private Session parallel;

    private Session session;
    
    private HibernatePersonDao dao;

    @Before
    public void setUp() throws Exception {
        sf = getSessionFactory();
        dao = new HibernatePersonDao();
        dao.setSessionFactory(sf);

        tx = sf.getCurrentSession().beginTransaction();
        tx.begin();

        session = sf.getCurrentSession();
        parallel = sf.openSession();
    }

    @After
    public void tearDown() {
        if (tx != null) {
            tx.rollback();
        }
    }

    @Test
    public void testSaveGivesThePersonAnIdentity() throws Exception {
        Person john = new Person("John", "Doe");
        assertNull(john.getId());
        dao.save(john);
        assertNotNull(john.getId());
    }

    @Test
    public void testSaveActuallyPersistsThePerson() throws Exception {
        Person john = new Person("John", "Doe");
        dao.save(john);
        assertNotNull(session.get(Person.class, john.getId()));
        assertNotNull(parallel.get(Person.class, john.getId()));
    }

    @Test
    public void testFindByLastname() throws Exception {
        String lastname = "Smith";

        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Alice", lastname));
        people.add(new Person("Billy", lastname));
        people.add(new Person("Clark", lastname));
        persist(people);

        assertEquals(people, dao.findByLastname(lastname));
    }

    @Test
    public void testFindByLastname2() throws Exception {
        String lastname = "Smith";

        List<Person> people = new ArrayList<Person>();
        people.add(new Person("Alice", lastname));
        people.add(new Person("Billy", lastname));
        people.add(new Person("Clark", lastname));
        persist(people);

        assertEquals(people, dao.findByLastname(lastname));
    }

    @Test
    public void testFindingAllSmiths() throws Exception {
        List<Person> theSmiths = new ArrayList<Person>();
        theSmiths.add(new Person("Alice", "Smith"));
        theSmiths.add(new Person("Billy", "Smith"));

        List<Person> allPeople = new ArrayList<Person>();
        allPeople.addAll(theSmiths);
        allPeople.add(new Person("John", "Doe"));

        persist(allPeople);
        assertEquals(theSmiths, dao.findByLastname("Smith"));
    }

    private void persist(List<? extends Object> objects) {
        Session s = sf.getCurrentSession();
        for (Object object : objects) {
            s.save(object);
        }
        s.flush();
    }
}
