package repository;

import config.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StatisticsRepository {
    static Connection connection = DbConnection.dbConnection();




        public static void generateLibraryStatistics() throws SQLException {

                // Query to get the count of available books
                String availableBooksQuery = "SELECT COUNT(*) FROM books WHERE quantity > 0";
                int availableBooksCount = executeCountQuery(connection, availableBooksQuery);

                // Query to get the count of borrowed books
                String borrowedBooksQuery = "SELECT COUNT(*) FROM reservations WHERE reservationStatus = 'RESERVED'";
                int borrowedBooksCount = executeCountQuery(connection, borrowedBooksQuery);

                // Query to get the count of lost books
                String lostBooksQuery = "SELECT COUNT(*) FROM reservations WHERE reservationStatus = 'LOST'";
                int lostBooksCount = executeCountQuery(connection, lostBooksQuery);

                // Print the statistics
                System.out.println("********** Library Statistics **********");
                System.out.println("Available Books: " + availableBooksCount);
                System.out.println("Borrowed Books: " + borrowedBooksCount);
                System.out.println("Lost Books: " + lostBooksCount);
            }


        private static int executeCountQuery(Connection connection, String query) throws SQLException {
            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(query);
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
            return 0;
        }





}
