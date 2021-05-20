package pl.sda.service;

import pl.sda.model.Person;
import pl.sda.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonService {

    public static final String GET_ALL_PEOPLE = "SELECT * FROM person";

    // Stworzyć metodę, która zwróci wszystkie osoby (listę obiektów) urodzone przed 1990 rokiem.

    public List<Person> getPersonBornBefore1990() {

        List<Person> persons = new ArrayList<>();

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(GET_ALL_PEOPLE);

            while (rs.next()) {
                String pesel = rs.getString("pesel");
                if (isPESELOlderThan1990(pesel)) {
                    int id = rs.getInt("id");
                    String name = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    Person person = new Person(id, name, lastName, pesel);
                    persons.add(person);
                }
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return persons;
    }

    private boolean isPESELOlderThan1990(String pesel) {
        int year = Integer.parseInt(pesel.substring(0, 2));

        return year < 90;
    }

    // Stworzyć metodę, która zwróci listę imion i nazwisk jako string, dla wszystkich osób o nazwisku Kowalski

    public List<Person> getKowalscy() {

        List<Person> Kowalscy = new ArrayList<>();

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(GET_ALL_PEOPLE);

            while (rs.next()) {
                String last_name = rs.getString("last_name");
                if (isKowalski(last_name)) {
                    int id = rs.getInt("id");
                    String first_name = rs.getString("first_name");
                    String pesel = rs.getString("pesel");
                    Person Kowalski = new Person(id, first_name, last_name, pesel);
                    Kowalscy.add(Kowalski);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return Kowalscy;
    }

    public boolean isKowalski(String surname) {
        if (surname.equals("Kowalski")) {
            return true;
        } else {
            return false;
        }
    }

    // Stworzyć metodę, która zwróci prawdę lub w fałsz, w zależności czy osoba, która jest podana jako argument metody istnieje w bazie.
    public boolean isPersonInDB(Person person) {

        boolean isPersonInDataBase = false;

        List<Person> people = new ArrayList<>();

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(GET_ALL_PEOPLE);

            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String pesel = rs.getString("pesel");
                Person dbPerson = new Person(id, first_name, last_name, pesel);
                people.add(dbPerson);
            }

            for (int i = 0; i < people.size(); i++) {
                if (
                        people.get(i).getId() == person.getId()
                                && people.get(i).getFirstName().equals(person.getFirstName())
                                && people.get(i).getLastName().equals(person.getLastName())
                                && people.get(i).getPesel().equals(person.getPesel())
                ) {
                    isPersonInDataBase = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

         return isPersonInDataBase;
    }
}
