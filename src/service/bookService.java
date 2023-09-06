package service;

import config.DbConnection;

import java.sql.*;


public class bookService {
    Connection connection = DbConnection.dbConnection();

    public void insert() throws SQLException {


        String sql = "INSERT INTO books (title,author,quantity,isbn ) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "test");
        statement.setString(2, "mee");
        statement.setString(3, "12");
        statement.setString(4, "tre57UUi");

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Book was inserted successfully!");
        }
    }

    public void getAll() throws SQLException {
        String sql = "SELECT * FROM books";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);

        int count = 0;

        while (result.next()){
            String title = result.getString(2);
            String author = result.getString(3);
            String quantity = result.getString("quantity");
            String isbn = result.getString("isbn");

            String output = "Book : %s - %s - %s - %s";
            System.out.println(String.format(output,  title, author, quantity, isbn));
        }
    }

    public void update() throws SQLException {
        String sql = "UPDATE books SET title=?, author=?, quantity=? WHERE bookId=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "editTest");
        statement.setString(2, "William Henry Bill Gates");
        statement.setString(3, "16");
        statement.setString(4, "1");

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
    System.out.println("An existing user was updated successfully!");
        }
    }

    public void delete() throws SQLException {
        String sql = "DELETE FROM books WHERE bookId=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "2");

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A user was deleted successfully!");
        }
    }
}
