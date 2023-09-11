import repository.BookRepository;
import entity.Book;
import entity.Reservation;
import entity.User;
import repository.ReservationRepository;
import repository.StatisticsRepository;
import service.BookService;
import service.ReservationService;
import service.StatisticsService;
import java.util.Scanner;

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

        ReservationService reserve = new ReservationService();
//        reserve.reserveBook();
//    reserve.returnBorrowedBook();
        StatisticsService statistics = new StatisticsService();
//        statistics.getStatistics();



                BookService obj = new BookService();

                Scanner scanner = new Scanner(System.in);
                boolean isRunning = true;

                while (isRunning) {
                    System.out.println("\n\n\n------------------------------------------");

                    System.out.println("Choose an action:");
                    System.out.println("1. Create New Book");
                    System.out.println("2. Edit a Book");
                    System.out.println("3. Delete a Book");
                    System.out.println("4. Show All Books");
                    System.out.println("5. Search Book By Title");
                    System.out.println("6. Search Book By Author");
                    System.out.println("7. Borrow  Book");
                    System.out.println("8. return  Book");
                    System.out.println("9. Generate Statistics");
                    System.out.println("10. Exit");
                    System.out.print("Enter your choice: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    switch (choice) {
                        case 1:
                            System.out.println("You selected Action 1");

                            obj.saveBook();
                            break;
                        case 2:
                            System.out.println("You selected Action 2");

                            obj.editBook();
                            break;
                        case 3:
                            System.out.println("You selected Action 3");

                            obj.deleteBook();
                            break;
                        case 4:
                            System.out.println("You selected Action 4");

                            obj.getAllBooks();
                            break;
                        case 5:
                            System.out.println("You selected Action 5");

                            obj.searchByTitle();
                            break;
                        case 6:
                            System.out.println("You selected Action 6");

                            obj.searchByAuthor();
                            break;
                        case 7:
                            System.out.println("You selected Action 7");

                            reserve.reserveBook();
                            break;
                        case 8:
                            System.out.println("You selected Action 8");

                            reserve.returnBorrowedBook();
                            break;
                        case 9:
                            System.out.println("You selected Action 9");

                            statistics.getStatistics();
                            break;
                        case 10:
                            System.out.println("Exiting the program.");
                            isRunning = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please try again.");
                    }
                }

                // Close resources or perform cleanup here
                scanner.close();
            }
        }


