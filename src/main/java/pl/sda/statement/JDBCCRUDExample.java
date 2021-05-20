package pl.sda.statement;

import pl.sda.util.JDBCUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCCRUDExample {

    public static void main(String[] args) {

        String insertIntoPerson = "INSERT INTO person VALUES(4, 'Michał', 'Nowak', '97122309876')";

        String updatePerson = "UPDATE person SET pesel = '98765432110'  WHERE id = 1";

        String createTable = "create table exemplary_table (id smallint unsigned auto_increment, name varchar(10) not null, surname varchar(10) not null, primary key (id))";

        String insertIntoExemplary = "INSERT INTO exemplary_table VALUES(1, 'Michał', 'Nowak')";

        String insertIntoExemplary1 = "INSERT INTO exemplary_table VALUES(2, 'Adam', 'Nowak')";

        String deleteFromExemplary = "delete from exemplary_table where name = 'Michał'";

        String dropTable = "drop table exemplary_table";

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            statement.execute(dropTable);

//            System.out.println(rowCounter + " rows updated");

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
