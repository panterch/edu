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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

/**
 * Executes SQL statements from a batch file.
 * 
 * @author Lasse Koskela
 */
public class SqlExecutor {

    private Connection connection;

    private char separator = ';';

    private Logger logger;

    /**
     * @param connection
     *            The JDBC connection to execute the SQL statements with.
     */
    public SqlExecutor(Connection connection) {
        this.connection = connection;
        this.logger = Logger.getLogger(getClass().getName());
    }

    /**
     * Override the default separator character (';').
     */
    public void setSeparator(char separator) {
        this.separator = separator;
    }

    /**
     * Execute SQL statements read from the given <tt>InputStream</tt>.
     * 
     * @param source
     *            Source of SQL statements.
     */
    public void execute(InputStream source) throws SQLException,
            IOException {
        execute(new InputStreamReader(source));
    }

    /**
     * Execute SQL statements read from the given <tt>java.io.File</tt>.
     * 
     * @param file
     *            Source of SQL statements.
     */
    public void execute(File file) throws SQLException, IOException {
        execute(new FileReader(file));
    }

    /**
     * Execute SQL statements parsed from the given <tt>String</tt>.
     * 
     * @param sql
     *            A string containing SQL statements.
     */
    public void execute(String sql) throws SQLException, IOException {
        execute(new StringReader(sql));
    }

    /**
     * Execute SQL statements read from the given <tt>java.net.URL</tt>.
     * 
     * @param source
     *            Source of SQL statements.
     */
    public void execute(URL source) throws SQLException, IOException {
        execute(source.openStream());
    }

    /**
     * Execute SQL statements read from the given <tt>java.io.Reader</tt>.
     * 
     * @param source
     *            Source of SQL statements.
     */
    public void execute(Reader source) throws SQLException,
            IOException {
        execute(new PushbackReader(source));
    }

    private void execute(PushbackReader reader) throws SQLException,
            IOException {
        Statement statement = connection.createStatement();
        String sql = null;
        while ((sql = readNextStatement(reader)) != null) {
            try {
                logger.fine("Executing SQL: " + sql);
                statement.execute(sql);
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        statement.close();
    }

    private String readNextStatement(PushbackReader reader)
            throws IOException {
        if (peek(reader) == -1) {
            return null;
        }
        StringBuffer sql = new StringBuffer();
        int c = -1;
        boolean insideQuotedString = false;
        while ((c = reader.read()) != -1) {
            if (c == separator && !insideQuotedString) {
                break;
            }
            if (c == '\'') {
                insideQuotedString = !insideQuotedString;
            }
            sql.append((char) c);
        }
        return sql.toString().trim();
    }

    private int peek(PushbackReader reader) throws IOException {
        int c = reader.read();
        reader.unread(c);
        return c;
    }

}
