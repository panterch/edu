package com.tddinaction.data.hibernate.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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
public abstract class IncrementalHibernateIntegrationTestCase {

    private static SessionFactory sf;

    private static Set<Class> knownClasses = new HashSet<Class>();

    /**
     * Returns a <tt>SessionFactory</tt> which knows about the persistent
     * classes passed in as parameters. A new <tt>SessionFactory</tt> instance
     * is only created if there is no existing instance or if some of the
     * classes passed in as parameters were not already configured for the
     * existing instance.
     * 
     * @param persistentClasses
     *            The persistent classes the <tt>SessionFactory</tt> should
     *            know how to persist. There must be a "[ClassName].hbm.xml"
     *            mapping document located in the classpath next to the class
     *            file itself.
     * @return A <tt>SessionFactory</tt> that can be used for persisting the
     *         classes passed in as arguments.
     */
    protected static synchronized SessionFactory getSessionFactory(
            Class... persistentClasses) {
        Set<Class> newClasses = new HashSet<Class>(knownClasses);
        newClasses.addAll(Arrays.asList(persistentClasses));
        if (sf == null || !newClasses.equals(knownClasses)) {
            try {
                Configuration cfg = createConfiguration(newClasses);
                sf = cfg.buildSessionFactory();
                knownClasses = newClasses;
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
        Assert.assertNotNull("Resource not found: " + resource, schema);
        Connection connection = sf.openSession().connection();
        new SqlExecutor(connection).execute(schema);
        connection.commit();
        connection.close();
    }

    private static Configuration createConfiguration(
            Set<Class> persistentClasses) throws IOException {
        Configuration cfg = new Configuration();
        String path = "/hibernate.test.properties";
        Properties properties = loadPropertiesFrom(path);
        cfg.setProperties(properties);
        for (Class c : persistentClasses) {
            cfg.addClass(c);
        }
        return cfg;
    }

    private static Properties loadPropertiesFrom(String path)
            throws IOException {
        Class context = IncrementalHibernateIntegrationTestCase.class;
        InputStream stream = context.getResourceAsStream(path);
        Assert.assertNotNull("Resource not found: " + path, stream);
        Properties props = new Properties();
        props.load(stream);
        return props;
    }
}
