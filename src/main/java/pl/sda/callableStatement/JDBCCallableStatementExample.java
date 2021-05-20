package pl.sda.callableStatement;

import pl.sda.util.JDBCUtil;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCCallableStatementExample {
    public static void main(String[] args) {
        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            CallableStatement callableStatement = connection.prepareCall("{call selectFromPerson()}");

            ResultSet rs = callableStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String pesel = rs.getString("pesel");
                System.out.println(id + " " + name + " " + lastName + " " + pesel);
            }
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
