package service;

import config.DbConnection;
import entity.Book;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class bookService {
    Connection connection = DbConnection.dbConnection();

    public void insert() throws SQLException {


        String sql = "INSERT INTO books (title,author,quantity,isbn ) VALUES (?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, "Book2");
        statement.setString(2, "FAHD");
        statement.setString(3, "10");
        statement.setString(4, "FXRR4");

        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new Book was inserted successfully!");
        }
    }

    public void getAll() throws SQLException {
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

    public void search() throws SQLException, IOException {
        Scanner scan =new Scanner(System.in);

        System.out.print("Enter Title: ");
        String title = scan.nextLine();

        System.out.print("Enter Author: ");
        String author = scan.nextLine();

        String sql= "SELECT * FROM `books` WHERE title=? OR author=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,title);
        statement.setString(2,author);
        System.out.println(title+author);
        ResultSet result=statement.executeQuery();

//        statement.setString(1, "editTest");

        while (result.next()){
            String resTitle = result.getString(2);
            String resAuthor = result.getString(3);
            int resQuantity = result.getInt("quantity");
            String resIsbn = result.getString("isbn");

            Book book = new Book(resTitle,resAuthor,resQuantity, resIsbn);
//            String output = "Book :  %s ---- %s ---- %s ---- %s";
            System.out.println(book);
        }
    }
}
