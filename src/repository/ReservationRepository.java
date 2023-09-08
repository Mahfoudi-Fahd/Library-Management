package repository;

import config.DbConnection;
import entity.Reservation;
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
        int userId = getUserIdFromDatabase(userName);

        // Retrieve Book ID from the database based on the book's title
        int bookId = getBookIdFromDatabase(bookTitle);

        if (userId != -1 && bookId != -1) {
            // Both User ID and Book ID were successfully retrieved from the database
            ReservationStatus reservationStatus = ReservationStatus.RESERVED; // Set the status to RESERVED

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
        } else {
            System.out.println("Failed to retrieve User ID or Book ID from the database.");
        }
    }

    private static int getUserIdFromDatabase(String userName) throws SQLException {
        String sql = "SELECT id FROM users WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
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
    }
}
