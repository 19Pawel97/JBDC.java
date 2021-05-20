package pl.sda.statement;

import pl.sda.util.JDBCUtil;

import java.sql.*;

public class JDBCStatementExample {

    public static void main(String[] args) {

        try {
            // opening connection using url, user and password
            Connection connection = JDBCUtil.getConnection();

            //creating statement - giving possibility to use the sql queries in java
            Statement statement = connection.createStatement();

            //executeQuery returns kinda map - ResultSet
            ResultSet rs = statement.executeQuery("SELECT * FROM person");

            //counting the number of rows
            int counter = 0;

            //while there is some next element - continue
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String pesel = rs.getString("pesel");
                System.out.println(id + " " + name + " " + lastName + " " + pesel);
                counter++;
            }

            System.out.println("The number of rows in person table: " + counter);

            // closing connection
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
