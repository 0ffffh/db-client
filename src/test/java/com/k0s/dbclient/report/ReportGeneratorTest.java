package com.k0s.dbclient.report;

import org.junit.jupiter.api.Test;
import java.sql.*;

import static org.mockito.Mockito.*;

class ReportGeneratorTest {

    @Test
    void createReport() throws SQLException{
        ResultSet resultSet = mock(ResultSet.class);
        ResultSetMetaData resultSetMetaData = mock(ResultSetMetaData.class);

        String reportPath = "report";

        when(resultSet.getMetaData()).thenReturn(mock(ResultSetMetaData.class));
        when(resultSetMetaData.getColumnCount()).thenReturn(1);
        when(resultSetMetaData.getColumnLabel(1)).thenReturn("columnLabel");
        when(resultSetMetaData.getTableName(1)).thenReturn("tablename");

        ReportGenerator.createReport(resultSet, reportPath);

        verify(resultSet).beforeFirst();
        verify(resultSet, atLeastOnce()).next();
        verify(resultSet, atLeastOnce()).next();
        verify(resultSet, atLeastOnce()).getMetaData();
    }
}