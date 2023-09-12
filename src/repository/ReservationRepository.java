package repository;

import config.DbConnection;
import entity.ReservationStatus;

import java.sql.*;
import java.util.Scanner;
//import java.sql.Date;


public class ReservationRepository {
    static Connection connection = DbConnection.dbConnection();

    public static void reserve() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("**************** Add a New Reservation *****************");
        System.out.println("Enter User Name : ");
        String userName = scanner.nextLine(); // Input the user's name
        System.out.println("Enter Book Title : ");
        String bookTitle = scanner.nextLine(); // Input the book's title
        System.out.println("Enter Borrow Date : ");
        String borrowDate = scanner.nextLine();
        System.out.println("Enter Return Date : ");
        String returnDate = scanner.nextLine();

        // Retrieve User ID from the database based on the user's name
        int userId = getUserIdFromDatabase(connection, userName);

        // Retrieve Book ID from the database based on the book's title
        int bookId = getBookIdFromDatabase(bookTitle);
        int bookQuantity = getBookQuantity(connection,bookId);


        if (userId != -1 && bookId != -1  ) {
            // Both User ID and Book ID were successfully retrieved from the database
            ReservationStatus reservationStatus = ReservationStatus.RESERVED; // Set the status to RESERVED
            if (bookQuantity>0) {
                String sql = "INSERT INTO reservations (user_id, book_id, borrowDate, returnDate,reservationStatus) VALUES (?, ?, ?,?, ?)";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setInt(1, userId);
                    statement.setInt(2, bookId);
                    statement.setString(3, borrowDate);
                    statement.setString(4, returnDate);
                    statement.setString(5, reservationStatus.toString());

                    int rowsInserted = statement.executeUpdate();
                    if (rowsInserted > 0) {

                        decrementBookQuantity(connection, bookId);

                        System.out.println("A new reservation was created successfully!");
                    }
                }
            }else
            {
                System.out.println("All units From this Book Are reserved");
            }
        } else {
            System.out.println("Failed to retrieve User ID or Book ID from the database.");
        }
    }

    private static int getUserIdFromDatabase(Connection connection, String userName) throws SQLException {
        String sql = "SELECT id FROM users WHERE name = ?";
        try (PreparedStatement statement = ReservationRepository.connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("users.id");
            }
        }
        return -1; // Return -1 if the user is not found in the database
    }

    private static int getBookIdFromDatabase(String bookTitle) throws SQLException {
        String sql = "SELECT id FROM books WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, bookTitle);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("books.id");
            }
        }
        return -1; // Return -1 if the book is not found in the database
    }

    private static void decrementBookQuantity(Connection connection, int bookId) throws SQLException {
        // Check if the current quantity is greater than 0
        if (getBookQuantity(connection, bookId) > 0) {
            String updateSql = "UPDATE books SET quantity = quantity - 1 WHERE id = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                updateStatement.setInt(1, bookId);
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("Book quantity decremented successfully.");
                } else {
                    System.out.println("Failed to update book quantity.");
                }
            }
        } else {
            System.out.println("Book quantity is already at 0. Cannot decrement further.");
        }
    }

    private static int getBookQuantity(Connection connection, int bookId) throws SQLException {
        String query = "SELECT quantity FROM books WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("quantity");
            }
        }
        return 0; // Return 0 if the book is not found
    }



    public static void returnBook() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        // ...

        // Prompt the user to enter their username
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();

        // Return the book associated with the user's username
        boolean bookReturned = returnBookByUsername(username);

        if (bookReturned) {
            System.out.println("Book has been returned.");
        } else {
            System.out.println("No reservations found for the user or book has already been returned.");
        }

    }

    public static boolean returnBookByUsername(String username) throws SQLException {
            int userId = getUserIdFromDatabase(connection, username);

            if (userId != -1) {
                String sql = "UPDATE reservations SET reservationStatus = ? " +
                        "WHERE user_id = ? AND reservationStatus = ?";

                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    statement.setString(1, ReservationStatus.RETURNED.toString());
                    statement.setInt(2, userId);
                    statement.setString(3, ReservationStatus.RESERVED.toString());

                    int rowsUpdated = statement.executeUpdate();

                    return rowsUpdated > 0;
                }
            }


        return false;
    }




}
