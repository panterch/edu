package com.tddinaction.data.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.tddinaction.data.person.Person;

public class HibernatePersonDaoIntegrationTest {

    @Test
    public void persistedObjectCanBeRetrievedAfterwards()
            throws Exception {
        SessionFactory sf = getSessionFactory();
        HibernatePersonDao dao = new HibernatePersonDao();
        dao.setSessionFactory(sf);

        Person person = new Person("John", "Doe");
        dao.save(person);
        assertNotNull(person.getId());

        Session s = sf.openSession();
        assertEquals(person, s.get(Person.class, person.getId()));
    }

    private SessionFactory getSessionFactory() throws Exception {
        Configuration cfg = createConfiguration();
        return cfg.buildSessionFactory();
    }

    private Configuration createConfiguration() throws Exception {
        Configuration cfg = loadProductionConfiguration();
        loadTestConfigurationInto(cfg, "/hibernate.test.properties");
        return cfg;
    }

    private Configuration loadProductionConfiguration() {
        return new Configuration().configure();
    }

    private void loadTestConfigurationInto(Configuration cfg,
            String path) throws Exception {
        Properties properties = loadPropertiesFrom(path);
        Enumeration keys = properties.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = properties.getProperty(key);
            cfg.setProperty(key, value);
        }
    }

    private Properties loadPropertiesFrom(String path)
            throws Exception {
        InputStream stream = getClass().getResourceAsStream(path);
        assertNotNull("Resource not found: " + path, stream);
        Properties props = new Properties();
        props.load(stream);
        stream.close();
        return props;
    }
}
