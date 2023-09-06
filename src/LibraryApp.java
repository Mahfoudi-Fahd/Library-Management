import config.DbConnection;
import entity.Book;
import entity.Reservation;
import entity.User;
import service.bookService;

import java.security.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class LibraryApp {
    public static void main(String[] args) throws SQLException {



        Book book1 = new Book(1, "test entity.Book 1", "Author 1", 11, 12345);
        User user1 = new User(1, "Fahd");


        Reservation reservation1 = new Reservation(1001, user1, book1,new Date(),"23-04-2024");


        /*System.out.println("Book Title: " + book1.getTitle());
        System.out.println("Borrowed by: " + reservation1.getUser().getName());
        System.out.println("Reservation Date: " + reservation1.getBorrowDate());
        System.out.println("Return Date: " + reservation1.getReturnDate());*/


        bookService obj = new bookService();
        /*obj.insert();*/
obj.getAll();
    }
}
