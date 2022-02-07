package com.k0s.dbclient.connection;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.*;

class ConnectTest {

    @Test
    @Disabled
    @DisplayName("Connect to database from properties file")
    void getConnectionNoParam() throws SQLException {
        Property property = new Property();
        String[] args = new String[0];
        property.load(args);

        Connection connection = Connect.getConnection(property.getProperties());

        assertNotNull(connection);
        assertTrue(connection.isValid(0));
        connection.close();
    }

    @Test
    @Disabled
    @DisplayName("Connect to database postgres")
    void getConnectionWithParamPostgres() throws SQLException {
        String[] args = {"-Durl=jdbc:postgresql://localhost:5432/testdb", "-Duser=postgres", "-Dpassword=root"};
        Property property = new Property();
        property.load(args);

        Connection connection = Connect.getConnection(property.getProperties());

        assertNotNull(connection);
        assertTrue(connection.isValid(0));
        connection.close();

    }

    @Test
    @Disabled
    @DisplayName("Connect to database mysql")
    void getConnectionWithParamMysql() throws SQLException {
        String[] args = {"-Durl=jdbc:mysql://localhost:3808/testdb", "-Duser=root", "-Dpassword=root"};
        Property property = new Property();
        property.load(args);

        Connection connection = Connect.getConnection(property.getProperties());

        assertNotNull(connection);
        assertTrue(connection.isValid(0));
        connection.close();

    }

    @Test
    @DisplayName("Connect to database")
    public void testStaticMockWithVerification() throws SQLException {
        Connection conn = mock(Connection.class);
        Properties properties = mock(Properties.class);
        mockStatic(DriverManager.class);
        mockStatic(DatabaseMetaData.class);
        when(DriverManager.getConnection(anyString(),anyString(),anyString())).thenReturn(conn);

        Connect.getConnection(properties);

        verify(DriverManager.getConnection(anyString(),anyString(),anyString()));

    }
}