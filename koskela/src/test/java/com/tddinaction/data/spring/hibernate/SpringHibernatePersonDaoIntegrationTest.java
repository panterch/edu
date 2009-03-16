package com.tddinaction.data.spring.hibernate;

import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tddinaction.data.hibernate.util.HibernateIntegrationTestCase;
import com.tddinaction.data.person.Person;

public class SpringHibernatePersonDaoIntegrationTest extends
        HibernateIntegrationTestCase {

    private List<Person> theSmiths;

    private SessionFactory sf;

    private Transaction tx;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(
                SpringHibernatePersonDaoIntegrationTest.class);
    }

    @Before
    public void setUp() throws Exception {
        sf = getSessionFactory();
        tx = sf.getCurrentSession().beginTransaction();

        createSchema("/database-schema.sql");

        theSmiths = new ArrayList<Person>();
        theSmiths.add(new Person("Alice", "Smith"));
        theSmiths.add(new Person("Billy", "Smith"));
        theSmiths.add(new Person("Clark", "Smith"));

        List<Person> allPeople = new ArrayList<Person>();
        allPeople.addAll(theSmiths);
        allPeople.add(new Person("John", "Doe"));
        allPeople.add(new Person("Smith", "Jones"));

        persist(allPeople, sf);
    }

    @After
    public void tearDown() {
        if (tx != null) {
            tx.rollback();
        }
    }

    private void persist(List<? extends Object> objects,
            SessionFactory sf) {
        Session s = sf.getCurrentSession();
        for (Object object : objects) {
            s.save(object);
        }
        s.flush();
    }

    @Test
    public void testFindByLastname() throws Exception {
        SpringHibernatePersonDao dao = new SpringHibernatePersonDao();
        dao.setSessionFactory(sf);
        Assert.assertEquals(theSmiths, dao.findByLastname("Smith"));
    }

}
