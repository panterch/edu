package com.tddinaction.data.dbunit;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.NoColumnsFoundException;

/**
 * Utility methods for doing stuff with DbUnit.
 * 
 * @author Lasse Koskela
 */
public class DbUnitUtils {

    public static void print(Connection connection) throws DataSetException,
            SQLException {
        print(new DatabaseConnection(connection).createDataSet());
    }

    public static void print(IDataSet dataSet) throws DataSetException {
        String[] tableNames = dataSet.getTableNames();
        System.out.println("" + tableNames.length + " TABLES FOUND");
        for (int i = 0; i < tableNames.length; i++) {
            System.out.println();
            try {
                print(dataSet.getTable(tableNames[i]));
            } catch (NoColumnsFoundException e) {
                System.out.println("No columns found for " + e.getMessage());
            }
        }
    }

    private static void print(ITable table) throws DataSetException {
        String[][] data = convertToStringArray(table);
        trimColumnValues(data, 30);
        Map<Integer, Integer> lengths = determineMaxColumnLengths(data);
        padColumnsToMaxLength(data, lengths);
        print(table.getTableMetaData().getTableName(), data, lengths);
    }

    private static void print(String tableName, String[][] tableData,
            Map<Integer, Integer> columnLengths) {
        String separator = makeLine(columnLengths, true);
        System.out.println(makeLine(columnLengths, false));
        System.out.println(makeTableNameLine(tableName, columnLengths));
        printHorizontalLine(columnLengths);
        int columnCount = tableData[0].length;
        for (int j = 0; j < tableData.length; j++) {
            System.out.print("| ");
            for (int k = 0; k < columnCount; k++) {
                String columnData = tableData[j][k];
                if (k > 0) {
                    System.out.print(" | ");
                }
                System.out.print(columnData);
            }
            System.out.println(" |");
            if (j < 1) {
                System.out.println(separator);
            }
        }
        if (tableData.length > 1) {
            System.out.println(separator);
        }
    }

    private static String makeTableNameLine(String tableName,
            Map<Integer, Integer> columnLengths) {
        String sep = makeLine(columnLengths, false);
        sep = sep.replaceAll("\\+", "|");
        sep = sep.replaceAll("\\-", " ");
        while (sep.length() < (tableName.length() + 4)) {
            sep = sep.substring(0, 2) + " " + sep.substring(2);
        }
        char[] line = sep.toCharArray();
        char[] name = tableName.toCharArray();
        System.arraycopy(name, 0, line, 2, name.length);
        return new String(line);
    }

    private static void printHorizontalLine(Map<Integer, Integer> columnLengths) {
        System.out.println(makeLine(columnLengths));
    }

    private static String makeLine(Map<Integer, Integer> columnLengths) {
        return makeLine(columnLengths, true);
    }

    private static String makeLine(Map<Integer, Integer> columnLengths,
            boolean columnSeparators) {
        StringBuffer line = new StringBuffer();
        line.append("+-");
        for (int k = 0; k < columnLengths.size(); k++) {
            int length = columnLengths.get(k);
            if (k > 0) {
                if (columnSeparators) {
                    line.append("-+-");
                } else {
                    line.append("---");
                }
            }
            line.append(pad("", length, '-'));
        }
        line.append("-+");
        return line.toString();
    }

    private static void padColumnsToMaxLength(String[][] data,
            Map<Integer, Integer> lengths) {
        for (int j = 0; j < data.length; j++) {
            for (int k = 0; k < data[0].length; k++) {
                int length = lengths.get(k);
                data[j][k] = pad(data[j][k], length, ' ');
            }
        }
    }

    private static Map<Integer, Integer> determineMaxColumnLengths(
            String[][] data) {
        Map<Integer, Integer> lengths = new HashMap<Integer, Integer>();
        for (int j = 0; j < data.length; j++) {
            for (int k = 0; k < data[0].length; k++) {
                int oldMax = lengths.get(k) != null ? lengths.get(k) : 0;
                int newMax = data[j][k].length();
                lengths.put(k, Math.max(oldMax, newMax));
            }
        }
        return lengths;
    }

    private static void trimColumnValues(String[][] data, int maxlength) {
        for (int j = 0; j < data.length; j++) {
            for (int k = 0; k < data[0].length; k++) {
                if (data[j][k] != null && data[j][k].length() > maxlength) {
                    String oldValue = data[j][k];
                    String newValue = "";
                    if (oldValue.length() > maxlength - 3) {

                    } else {
                        newValue = oldValue.substring(0, maxlength);
                    }
                    data[j][k] = newValue;
                }
            }
        }
    }

    private static String[][] convertToStringArray(ITable table)
            throws DataSetException {
        Column[] columns = table.getTableMetaData().getColumns();
        String[][] data = new String[table.getRowCount() + 1][columns.length];
        // populate column names
        int i = 0;
        for (Column column : columns) {
            data[0][i++] = column.getColumnName();
        }
        // populate data rows
        for (int row = 0; row < table.getRowCount(); row++) {
            i = 0;
            for (Column column : columns) {
                String value = String.valueOf(table.getValue(row, column
                        .getColumnName()));
                data[row + 1][i++] = value;
            }
        }
        return data;
    }

    private static String pad(String string, int length, char padding) {
        StringBuffer s = new StringBuffer(length);
        s.append(string);
        while (s.length() < length) {
            s.append(padding);
        }
        return s.toString();
    }

}
