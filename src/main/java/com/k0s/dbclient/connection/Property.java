package com.k0s.dbclient.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private static final String DEFAULT_DB_CONFIG = "db.properties";
    private static final String DEFAULT_REPORT_PATH = "report";
    private static final String USAGE = "use: java -jar db-client.jar -Durl=<ulr> -Duser=<db user> -Dpassword=<password>" +
            "\n or set db.properties file";
    private final Properties properties = new Properties();

    public Property() {
    }

    public Properties getProperties(){
        checkProperties();
        return properties;
    }

    public void load (String [] args){
        if ( args.length == 0 ) {
           loadFile(DEFAULT_REPORT_PATH);
        } else {
            loadArgs(args);

        }
    }

    private void loadFile(String path){
        try {
            properties.load(new FileInputStream(DEFAULT_DB_CONFIG));
        } catch (IOException e) {
            System.out.println(USAGE);
            e.printStackTrace();
        }
    }
    private void loadArgs(String[] args){
        for (String arg : args) {
            if (arg.startsWith("-Durl") && arg.contains("=")) {
                properties.put("url", arg.substring(arg.indexOf("=") + 1));
            }
            if (arg.startsWith("-Duser") && arg.contains("=")) {
                properties.put("user", arg.substring(arg.indexOf("=") + 1));
            }
            if (arg.startsWith("-Dpassword") && arg.contains("=")) {
                properties.put("password", arg.substring(arg.indexOf("=") + 1));
            }
            if (arg.startsWith("-Dreport") && arg.contains("=")) {
                properties.put("report", arg.substring(arg.indexOf("=") + 1));
            }
        }
    }

    private void checkProperties(){
        if (properties.isEmpty()){
            throw new RuntimeException(USAGE);
        }
        if (properties.getProperty("url") == null){
            throw new RuntimeException("set connect URL, " + USAGE);
        }
        if (properties.getProperty("user") == null){
            throw new RuntimeException("set database user, " + USAGE);
        }
        if (properties.getProperty("password") == null){
            throw new RuntimeException("set database password, " + USAGE);
        }
        if (properties.getProperty("report") == null){
            properties.put("report", DEFAULT_REPORT_PATH);
        }

    }
}
