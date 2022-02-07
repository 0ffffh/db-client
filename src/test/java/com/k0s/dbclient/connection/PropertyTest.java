package com.k0s.dbclient.connection;

import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class PropertyTest {

    @Test
    void getProperties() {
        String[] args = {"-Durl=jdbc:mysql//localhost:3808", "-Duser=root", "-Dpassword=root"};
        Property property = new Property();
        property.load(args);
        Properties properties = property.getProperties();


        assertEquals("jdbc:mysql//localhost:3808", properties.getProperty("url"));
        assertEquals("root", properties.getProperty("user"));
        assertEquals("root", properties.getProperty("password"));

    }

    @Test
    void setProperties() {
        String[] args = new String[0];
        Property property = new Property();
        property.load(args);
        Properties properties = property.getProperties();
        assertEquals("jdbc:postgresql://localhost:5432/testdb", properties.getProperty("url"));
        assertEquals("postgres", properties.getProperty("user"));
        assertEquals("root", properties.getProperty("password"));

    }
}