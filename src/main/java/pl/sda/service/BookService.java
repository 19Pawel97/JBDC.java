package pl.sda.service;

import pl.sda.model.Book;
import pl.sda.util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    //Stworzyć metodę, która zwróci wszystkie książki (listę obiektów) wydane przed zadaną w argumencie datą.
    public List<Book> getBooksPublishedBefore(Date publicationDate) {

        List<Book> books = new ArrayList<>();

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet allBooks = statement.executeQuery("select * from book");

            while (allBooks.next()) {
                Date release_date = allBooks.getDate("release_date");
                if (release_date.before(publicationDate)) {
                    int id = allBooks.getInt("id");
                    String author = allBooks.getString("author");
                    String description = allBooks.getString("description");
                    String isbn = allBooks.getString("isbn");
                    String title = allBooks.getString("title");
                    Book book = new Book(id, author, description, isbn, release_date, title);
                    books.add(book);
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

        return books;
    }

    //Stworzyć metodę, która zwróci wszystkie książki, których autorem jest osoba wskazana jako argument metody.
    public List<Book> getBookWrittenBy(String bookAuthor) {

        List<Book> books = new ArrayList<>();

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet allBooks = statement.executeQuery("select * from book");

            while (allBooks.next()) {
                String author = allBooks.getString("author");
                if (author.equals(bookAuthor)) {
                    int id = allBooks.getInt("id");
                    String description = allBooks.getString("description");
                    String isbn = allBooks.getString("isbn");
                    Date release_date = allBooks.getDate("release_date");
                    String title = allBooks.getString("title");
                    Book book = new Book(id, author, description, isbn, release_date, title);
                    books.add(book);
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

        return books;
    }

    //Stworzyć metodę, która zwróci wszystkie książki, których autorem jest osoba wskazana jako argument metody z preparedStatement
    public List<Book> getBookWrittenByWithPreparedStatement(String bookAuthor) {

        List<Book> books = new ArrayList<>();

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            String getTheBook = "select * from book where author = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(getTheBook);

            preparedStatement.setString(1, bookAuthor);

            ResultSet theBook = preparedStatement.executeQuery();

            while (theBook.next()) {
                int id = theBook.getInt("id");
                String author = theBook.getString("author");
                String description = theBook.getString("description");
                String isbn = theBook.getString("isbn");
                Date release_date = theBook.getDate("release_date");
                String title = theBook.getString("title");
                Book book = new Book(id, author, description, isbn, release_date, title);
                books.add(book);
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

        return books;
    }

    //Stworzyć metodę, która zwróci wszystkie książki posortowane od A do Z po tytule.
    public List<Book> getAllBooksAlphabetically() {

        List<Book> books = new ArrayList<>();

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet allBooks = statement.executeQuery("select * from book order by title asc");

            while (allBooks.next()) {
                int id = allBooks.getInt("id");
                String author = allBooks.getString("author");
                String description = allBooks.getString("description");
                String isbn = allBooks.getString("isbn");
                Date release_date = allBooks.getDate("release_date");
                String title = allBooks.getString("title");
                Book book = new Book(id, author, description, isbn, release_date, title);
                books.add(book);
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

        return books;
    }

    //Stworzyć metodę, która zwróci książkę lub null, na podstawie isbn przekazanego jako parametr metody.
    public Book getParticularBookByIsbn(String bookIsbn) {

        Book theBook = null;

        Connection connection = null;

        try {
            connection = JDBCUtil.getConnection();

            Statement statement = connection.createStatement();

            ResultSet allBooks = statement.executeQuery("select * from book");

            while (allBooks.next()) {
                String isbn = allBooks.getString("isbn");
                if (isbn.equals(bookIsbn)) {
                    int id = allBooks.getInt("id");
                    String author = allBooks.getString("author");
                    String description = allBooks.getString("description");
                    Date release_date = allBooks.getDate("release_date");
                    String title = allBooks.getString("title");
                    Book book = new Book(id, author, description, isbn, release_date, title);
                    theBook = book;
                }
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

        return theBook;
    }
}
