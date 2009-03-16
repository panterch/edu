/*
 * Copyright 2006 Lasse Koskela.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.tddinaction.data.util;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.sql.Connection;
import java.sql.Statement;

import junit.framework.JUnit4TestAdapter;

import org.junit.Test;


public class SqlExecutorTest {

    private static final String SQL2 = "CREATE TABLE bar (id INTEGER NOT NULL)";

    private static final String SQL1 = "CREATE TABLE foo (id INTEGER NOT NULL)";

    @Test
    public void testSingleStatement() throws Exception {
        String data = SQL1;
        String[] expected = { SQL1 };
        executeAndVerify(data, expected);
    }

    @Test
    public void testMultipleStatementsOnSeparateLines() throws Exception {
        String data = SQL1 + ";\n" + SQL2 + ";";
        String[] expected = { SQL1, SQL2 };
        executeAndVerify(data, expected);
    }

    @Test
    public void testSeparatorCharactersInsideQuotedString() throws Exception {
        String sql = "INSERT INTO foo VALUES ('bar', ';', ';'';', 'abc')";
        String[] expected = new String[] { sql };
        executeAndVerify(sql + ";", expected);
    }

    private void executeAndVerify(String rawData, String[] expectedSql)
            throws Exception {
        Connection connection = createMock(Connection.class);
        Statement statement = createMock(Statement.class);
        expect(connection.createStatement()).andReturn(statement);
        for (String sql : expectedSql) {
            expect(statement.execute(sql.trim())).andReturn(true);
        }
        statement.close();
        replay(connection, statement);
        SqlExecutor executor = new SqlExecutor(connection);
        executor.execute(rawData);
        verify(connection, statement);
    }

    public static junit.framework.Test suite() {
        return new JUnit4TestAdapter(SqlExecutorTest.class);
    }
}
