package com.k0s.dbclient.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ReportGenerator {
    public static void createReport(ResultSet resultSet, String reportPath) throws SQLException {
        DBTablePrinter.printResultSet(resultSet);
        File report = new File(reportPath);
        if (!report.isDirectory()){
            report.mkdir();
        }
        String reportFile = "report-" + resultSet.getMetaData().getTableName(3) + ".html";
        File file = new File(reportPath, reportFile);
        resultSet.beforeFirst();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(getPage(resultSet));
        } catch (IOException e) {
            System.out.println("Can't create report file " + file);
            e.printStackTrace();
        }
    }




    static String getPage(ResultSet resultSet) throws SQLException {

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        StringBuilder response = new StringBuilder();

        response.append("<html>")
                .append("<head><title>").append(resultSet.getMetaData().getTableName(3)).append("</title></head>")
                .append("<body><center><h1>")
                .append("<table>")
                .append("<tr>");
        for (int i = 1; i <= columnCount; i++) {
            response.append("<th>").append(metaData.getColumnLabel(i)).append("</th>");
        }
        response.append("</tr>")
                .append("<tr>");

        while (resultSet.next()){

            for (int i = 1; i <= columnCount; i++) {
                response.append("<td>").append(resultSet.getString(i)).append("</td>");
            }
            response.append("</tr>");
        }
        response.append("</table>")
                .append("</h1></center></body>")
                .append("</html>");
        return response.toString();
    }
}
