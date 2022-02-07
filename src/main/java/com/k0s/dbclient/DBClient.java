package com.k0s.dbclient;

import com.k0s.dbclient.connection.Connect;
import com.k0s.dbclient.connection.Property;

import java.io.*;
import java.sql.*;

public class DBClient {
    public static void main(String[] args) {
        Property property = new Property();
        property.load(args);

        try(Connection connection = Connect.getConnection(property.getProperties());
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))){
            System.out.println("conected ..");
            String input = "";
            while (!(input = userInput.readLine()).equals("quit")) {
                try {
                    SQLexecutor.executeQuery(connection, input,
                            property.getProperties().getProperty("report"));
                    } catch (SQLException e) {
                        System.out.println("Bad SQL command \n" + e);
                    }
                }
        } catch (IOException | SQLException e) {
                    throw new RuntimeException(e);
        }
    }
}

