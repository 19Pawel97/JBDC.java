package pl.sda.preparedStatement;

import pl.sda.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JBDCPreparedStatementExample {
    public static void main(String[] args) {

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            String selectFromPerson = "SELECT * FROM person WHERE last_name = ?";

            String updatePerson = "UPDATE person SET last_name = ? WHERE id = ?";

            //Parameterization

            PreparedStatement psUpdate = connection.prepareStatement(updatePerson);

            psUpdate.setString(1,"Kowalski");

            psUpdate.setInt(2,3);

            psUpdate.executeUpdate();


            PreparedStatement preparedStatement = connection.prepareStatement(selectFromPerson);

            preparedStatement.setString(1,"Kowalski");

            ResultSet rs = preparedStatement.executeQuery();

            int counter = 0;

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String pesel = rs.getString("pesel");
                System.out.println(id + " " + name + " " + lastName + " " + pesel);
                counter++;
            }

            System.out.println("The number of rows returned: " + counter);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
