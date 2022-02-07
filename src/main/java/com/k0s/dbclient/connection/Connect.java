package com.k0s.dbclient.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connect {

    public static Connection getConnection(Properties properties) throws SQLException {
        System.out.println("======get connection======");
        System.out.println(properties.getProperty("url"));
        System.out.println("user = " + properties.getProperty("user"));
        System.out.println("password = " + properties.getProperty("password"));
        System.out.println("===========================");

        return DriverManager.getConnection(properties.getProperty("url"),
                properties.getProperty("user"),
                properties.getProperty("password"));
    }

}
