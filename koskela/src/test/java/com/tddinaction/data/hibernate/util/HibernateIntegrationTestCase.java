package com.tddinaction.data.hibernate.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Assert;

import com.tddinaction.data.util.SqlExecutor;

/**
 * A base class for database integration tests giving access to a singleton
 * <tt>SessionFactory</tt> configured programmatically.
 * 
 * @author Lasse Koskela
 */
public abstract class HibernateIntegrationTestCase {

    private static SessionFactory sf;

    /**
     * Returns a <tt>SessionFactory</tt>.
     */
    protected static synchronized SessionFactory getSessionFactory() {
        if (sf == null) {
            try {
                Configuration cfg = createConfiguration();
                sf = cfg.buildSessionFactory();
            } catch (Throwable ex) {
                throw new RuntimeException(ex);
            }
        }
        return sf;
    }

    /**
     * Loads a database schema into the integration test database from the DDL
     * statements in the referred file.
     * 
     * @param resource
     *            The path to the DDL file in classpath.
     */
    protected void createSchema(String resource) throws Exception {
        InputStream schema = getClass().getResourceAsStream(resource);
        Assert.assertNotNull("Resource not found: " + resource,
                schema);
        Connection connection = sf.openSession().connection();
        new SqlExecutor(connection).execute(schema);
        connection.commit();
        connection.close();
    }

    private static Configuration createConfiguration()
            throws IOException {
        Configuration cfg = new Configuration().configure();
        String path = "/hibernate.test.properties";
        Properties testProperties = loadPropertiesFrom(path);
        Enumeration keys = testProperties.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = testProperties.getProperty(key);
            cfg.setProperty(key, value);
        }
        return cfg;
    }

    private static Properties loadPropertiesFrom(String path)
            throws IOException {
        Class context = HibernateIntegrationTestCase.class;
        InputStream stream = context.getResourceAsStream(path);
        Assert.assertNotNull("Resource not found: " + path, stream);
        Properties props = new Properties();
        props.load(stream);
        return props;
    }
}
