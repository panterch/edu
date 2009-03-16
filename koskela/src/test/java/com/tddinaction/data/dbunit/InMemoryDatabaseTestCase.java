package com.tddinaction.data.dbunit;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Before;

import com.tddinaction.data.util.SqlExecutor;

public abstract class InMemoryDatabaseTestCase extends
        DatabaseTestCase {

    @Before
    public void setUp() throws Exception {
        createSchema();
        super.setUp();
    }

    private void createSchema() throws Exception {
        String resource = "/database-schema.sql";
        InputStream schema = getClass().getResourceAsStream(resource);
        assertNotNull("Resource \"" + resource + "\" not found",
                schema);
        new SqlExecutor(getJdbcConnection()).execute(schema);
    }

    protected Connection getJdbcConnection() throws SQLException {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return DriverManager.getConnection("jdbc:hsqldb:mem:testdb");
    }

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        return new DatabaseConnection(getJdbcConnection());
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return getDataSet("initial");
    }

    protected IDataSet getDataSet(String name) throws Exception {
        String resource = getClass().getSimpleName() + "." + name
                + ".xml";
        InputStream stream = getClass().getResourceAsStream(resource);
        assertNotNull("Resource " + resource + " not found.", stream);
        return new FlatXmlDataSet(stream);
    }

}
