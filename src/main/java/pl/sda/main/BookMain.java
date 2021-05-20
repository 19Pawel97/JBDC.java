package pl.sda.main;

import pl.sda.service.BookService;

import java.sql.Date;

public class BookMain {
    public static void main(String[] args) {
        BookService bookService = new BookService();

        bookService.getBooksPublishedBefore(Date.valueOf("1936-01-01")).stream()
                .forEach(System.out::println);

        System.out.println();

        bookService.getBookWrittenBy("Zbigniew Herbert").stream()
                .map(book -> book.getTitle())
                .forEach(System.out::println);

        System.out.println();

        bookService.getBookWrittenByWithPreparedStatement("Zbigniew Herbert").stream()
                .map(book -> book.getTitle())
                .forEach(System.out::println);

        System.out.println();

        bookService.getAllBooksAlphabetically().stream()
                .forEach(System.out::println);

        System.out.println();

        System.out.println(bookService.getParticularBookByIsbn("54875692456321548754"));

        System.out.println("Small change for the next commit.");
    }
}
