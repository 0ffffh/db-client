package com.k0s.dbclient;

import com.k0s.dbclient.report.ReportGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SQLexecutor {

    public static void executeQuery(Connection connection, String sql, String reportPath) throws SQLException {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

            if (preparedStatement.execute()){

                ResultSet resultSet = preparedStatement.getResultSet();
                ReportGenerator.createReport(resultSet, reportPath);

            } else {
                int count = preparedStatement.getUpdateCount();
                System.out.println("Update " + count + " row");
            }
        }
    }
}

