package M226a.project;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * The class SnackImporter is used to handle the SQL Statements and to hide the construction of the SQL Statement in a class.
 * These functions are used with SQL Statements in the parameter, to delete, insert, edit, etc. a snack.
 */
public class SnackImporter {
    /**
     * The function testConnection() is to test the connection between the java program to the database.
     * It contains the variables userName, password and URL from the database.
     * The connection is solved within a try catch, if it connects successfully, the program will print out
     * "Successfully connected to Database". If the connection is not working the program will catch a SQL Exception
     * and it will print it out.
     */
    public void testConnection() {
        Connection connection = null;
        String userName = "stduser";
        String password = "stduserpw";
        String URL = "jdbc:mariadb://localhost:3306/user";
        String driver = "org.mariadb.jdbc.Driver";

        try {
            connection = DriverManager.getConnection(URL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully connected to Database");
    }

    /**
     * The function grabData() is to get the data from the database. It is getting used within a "SELECT" statement.
     * @param query is for the other functions to update, add, delete, etc. something from the database.
     * @return ArrayList called tempSnacks
     */
    public ArrayList<Snacks> grabData(String query) {
        String output = "";
        ArrayList<Snacks> tempSnacks = new ArrayList<>();
        try {
            String connectionUrl = "jdbc:mariadb://localhost:3306/user";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl, "stduser", "stduserpw");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                tempSnacks.add(new Snacks((String) rs.getObject(2), ((BigDecimal) rs.getObject(3)).doubleValue(),
                        (Long) rs.getObject(4)));
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return tempSnacks;
    }

    /**
     *
     * @param query is to use this function to update data. This param query is used as a "UPDATE" statement.
     * @return a boolean called successful. If it's "true" it's not catching an Exception and the boolean will be returned
     * as "false". If there's no error the boolean will be set on "true" and returned.
     */
    public boolean updateData(String query){
        boolean successful = true;

        try {
            String connectionUrl = "jdbc:mariadb://localhost:3306/user";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl, "stduser", "stduserpw");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            successful = false;
        }
        return successful;
    }

    /**
     * The function pushData() is to insert snacks into the snackMachine. As a Manager you got the option to add a snack.
     * If you add a snack it will automatically insert into the database.
     * @param query is to insert the Snack into the database. It's used within a "INSERT INTO" statement.
     */
    public void pushData(String query){
        try {
            String connectionUrl = "jdbc:mariadb://localhost:3306/user";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl, "stduser", "stduserpw");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is to delete a snack from the snackMachine. The Manager got the option to delete a snack. If he deletes
     * a snack in this program, it will also be deleted in the database.
     * @param query This param is used to create a "DELETE" statement, for the snack to be deleted in the database.
     */
    public void deleteData(String query){
        try {
            String connectionUrl = "jdbc:mariadb://localhost:3306/user";
            Connection con = null;
            Statement stmt = null;
            ResultSet rs = null;
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection(connectionUrl, "stduser", "stduserpw");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
