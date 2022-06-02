package databasetest2;

import databasetest.ConnectionType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    // create method to connect data base

    public static Connection connectToDataBaseServer(
            String dburl, String dbPort, String defaultDatabase,
            String dbuserName, String dbpassWord, ConnectionType connectionType) {
        // DEFINE A CONNECTION OBJECT;
        Connection connection = null;
        String JTDS_Driver = "net.sourceforge.jtds.jdbc.Driver";//sql driver connect
        String MySQL_Driver = "com.mysql.cj.jdbc.Driver";//mysql driver connect

        switch (connectionType) {
            case MSSQL:
                try {
                    Class.forName(JTDS_Driver);////initialize the SQL server driver
                } catch (ClassNotFoundException e) {
                    // e.printStackTrace();
                    new RuntimeException("Please check the sql server driver information");

                }
                String connectionURL = "jdbc:jtds:sqlserver://" + dburl + ":" + dbPort + ";DataBaseName=" + defaultDatabase;
                try {
                    connection = DriverManager.getConnection(connectionURL, dbuserName, dbpassWord);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case MYSQL:
                try {
                    Class.forName(MySQL_Driver).newInstance();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                String mySQLConnectionUrl = "jdbc:mysql://" + dburl + ":" + dbPort + "/" + defaultDatabase + "?useSSL=false";
                try {
                    connection = DriverManager.getConnection(mySQLConnectionUrl, dbuserName, dbpassWord);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            default:
                System.out.println("You need to specify a database connection type MSSQl or MYSQL ");
        }

                try {
                    if(!connection.isClosed()){
                        System.out.println("Database connection is established!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }


        return  connection;
    }
    //write a method to close the connection
    public static void closeDataBaseConnection(Connection connection){
        try {
            if (connection.isClosed()){
                System.out.println(" connection already closed!");
            }
            else {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
