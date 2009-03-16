package com.tddinaction.data.hibernate;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.JUnit4TestAdapter;

import org.hibernate.*;
import org.hibernate.classic.Session;
import org.junit.*;

import com.tddinaction.data.person.Person;

public class HibernatePersonDaoTest {

    private SessionFactory factory;

    private Session session;

    private Query query;

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(HibernatePersonDaoTest.class);
    }

    @Before
    public void setUp() {
        factory = createMock(SessionFactory.class);
        session = createMock(Session.class);
        query = createMock(Query.class);
    }

    @Test
    public void testFindByLastname() throws Exception {
        String hql = "from Person p where p.lastname = :lastname";
        String lastname = "Smith";

        List<Person> theSmiths = new ArrayList<Person>();
        theSmiths.add(new Person("Alice", lastname));
        theSmiths.add(new Person("Billy", lastname));
        theSmiths.add(new Person("Clark", lastname));

        expect(factory.getCurrentSession()).andReturn(session);
        expect(session.createQuery(hql)).andReturn(query);
        expect(query.setParameter("lastname", lastname)).andReturn(
                query);
        expect(query.list()).andReturn(theSmiths);

        replay(factory, session, query);

        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(factory);
        assertEquals(theSmiths, dao.findByLastname(lastname));

        verify(factory, session, query);
    }

    @Test
    public void testFindByLastnameThrowsRuntimeExceptionUponFailure()
            throws Exception {
        String hql = "from Person p where p.lastname = :lastname";
        String name = "Smith";
        HibernateException hibernateError = new HibernateException("");

        expect(factory.getCurrentSession()).andReturn(session);
        expect(session.createQuery(hql)).andReturn(query);
        expect(query.setParameter("lastname", name)).andReturn(
                query);
        expect(query.list()).andThrow(hibernateError);

        replay(factory, session, query);

        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(factory);

        try {
            dao.findByLastname(name);
            fail("should've thrown an exception");
        } catch (RuntimeException expected) {
            assertSame(hibernateError, expected.getCause());
        }

        verify(factory, session, query);
    }
}
