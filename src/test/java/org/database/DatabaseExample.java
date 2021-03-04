package org.database;

import org.testng.annotations.Test;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Tests can work with SQL database. For it, you need to have database set up.
 * Because database is not set up for this test, this test is ignored.
 */
public class DatabaseExample {

    @Test(enabled = false)
    public static void databaseTest() throws SQLException {
        //Get connection to our database with host, port, database name, username and password
        Connection connection = DriverManager.getConnection("jdbc:mysql://host.com:port/databasename", "dbusername", "dbpassword");
        Statement statement = connection.createStatement();

        //Execute statement and save set of result. Each line of result is saved in separate element of set.
        ResultSet rs = statement.executeQuery("Select * From DatabaseTable");
        while (rs.next()) {
            String column1 = rs.getString("column1");
            String column2 = rs.getString("column2");
        }
    }
}
