package repository;

import config.DbConnection;
import entity.Book;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;



public class BookRepository {
    static Connection connection = DbConnection.dbConnection();

    public static void insert() throws SQLException {


        String sql = "INSERT INTO books (title,author,quantity,isbn ) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Book3");
        statement.setString(2, "FAHD");
        statement.setString(3, "10");
        statement.setString(4, "FG2XRR4");

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Book was inserted successfully!");
        }
    }

    public static List<Book> getAll() throws SQLException {
        String sql = "SELECT * FROM books";

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery(sql);


        while (result.next()){
            String title = result.getString(2);
            String author = result.getString(3);
            String quantity = result.getString("quantity");
            String isbn = result.getString("isbn");

            String output = "Book : %s - %s - %s - %s";
            System.out.println(String.format(output,  title, author, quantity, isbn));
        }
        return null;
    }

    public static void update() throws SQLException {
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

    public static void delete() throws SQLException {
        String sql = "DELETE FROM books WHERE bookId=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "2");

        int rowsDeleted = statement.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A user was deleted successfully!");
        }
    }

    public static void searchByTitle() throws SQLException, IOException {
        Scanner scan =new Scanner(System.in);

        System.out.print("Enter Title: ");
        String title = scan.nextLine();



        String sql= "SELECT * FROM `books` WHERE title LIKE ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,title+"%");

//        System.out.println(title+author);
        ResultSet result=statement.executeQuery();

//        statement.setString(1, "editTest");

        boolean found = false;
        while (result.next()){
            String resTitle = result.getString(2);
            String resAuthor = result.getString(3);
            int resQuantity = result.getInt("quantity");
            String resIsbn = result.getString("isbn");

            Book book = new Book(resTitle,resAuthor,resQuantity, resIsbn);
//            String output = "Book :  %s ---- %s ---- %s ---- %s";
            System.out.println(book);
            found = true;
        }
        if (!found) {
            System.out.println("No results");
        }
    }

    public static void searchByAuthor() throws SQLException {

        Scanner scan =new Scanner(System.in);

        System.out.print("Enter Author: ");
        String author = scan.nextLine();

        String sql= "SELECT * FROM `books` WHERE author LIKE ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,author+"%");
        ResultSet result=statement.executeQuery();


        boolean found = false;
        while (result.next()){
            String resTitle = result.getString(2);
            String resAuthor = result.getString(3);
            int resQuantity = result.getInt("quantity");
            String resIsbn = result.getString("isbn");

            Book book = new Book(resTitle,resAuthor,resQuantity, resIsbn);
//            String output = "Book :  %s ---- %s ---- %s ---- %s";
            System.out.println(book);
            found = true;
        }
        if (!found) {
            System.out.println("No results");
        }
    }
}
