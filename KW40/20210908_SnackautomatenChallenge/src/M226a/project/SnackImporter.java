package M226a.project;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public class SnackImporter {
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
