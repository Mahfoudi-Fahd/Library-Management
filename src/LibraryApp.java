import repository.BookRepository;
import entity.Book;
import entity.Reservation;
import entity.User;
import repository.ReservationRepository;
import service.BookService;
import service.ReservationService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class LibraryApp {
    public static void main(String[] args) throws SQLException, IOException {



//        Book book1 = new Book(1, "test entity.Book 1", "Author 1", 11, "12345");
//        User user1 = new User(1, "Fahd");


//        Reservation reservation1 = new Reservation(1001, user1, book1,new Date(),"23-04-2024");


        /*System.out.println("Book Title: " + book1.getTitle());
        System.out.println("Borrowed by: " + reservation1.getUser().getName());
        System.out.println("Reservation Date: " + reservation1.getBorrowDate());
        System.out.println("Return Date: " + reservation1.getReturnDate());*/


//        BookService obj = new BookService();

//        obj.searchByTitle();
//        obj.searchByAuthor();
//        obj.saveBook();
//        obj.editBook();
//        obj.deleteBook();
//        obj.getAllBooks();

        ReservationService obj = new ReservationService();
        obj.reserveBook();
    }
}
