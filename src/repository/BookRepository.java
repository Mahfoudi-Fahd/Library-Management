package repository;

import config.DbConnection;
import entity.Book;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Scanner;



public class BookRepository {
    static Connection connection = DbConnection.dbConnection();

    private static boolean isValidBook(String title, String author, String ISBN, int quantity) {

        if (title.isEmpty() || author.isEmpty() || ISBN.isEmpty()) {
            return false;
        }

        if (quantity <= 0) {
            return false;
        }


        return true;
    }


    private static boolean isBookAlreadyExists(String title, String ISBN) throws SQLException {
        String sql = "SELECT COUNT(*) FROM books WHERE title = ? OR isbn = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, ISBN);
        ResultSet result = statement.executeQuery();

        if (result.next()) {
            int count = result.getInt(1);
            return count > 0;
        }

        return false;
    }


    public static void insert() throws SQLException {
        Scanner scanner =new Scanner(System.in);

        System.out.println("**************** Add a New Book *****************");
        System.out.println("Enter Title : ");
        String title = scanner.nextLine();
        System.out.println("Enter Author : ");
        String author = scanner.nextLine();
        System.out.println("Enter ISBN : ");
        String ISBN =  scanner.nextLine();
        System.out.println("Enter quantity : ");
        int quantity =  scanner.nextInt();

        if (isValidBook(title, author, ISBN, quantity )) {
            if (isBookAlreadyExists(title,ISBN)) {
                System.out.println("___________________________________________________________\n   A book with the same title Or ISBN already exists. \n ________________________________________________________");
            } else {
                String sql = "INSERT INTO books (title, author, quantity, isbn) VALUES (?, ?, ?, ?)";

                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, title);
                statement.setString(2, author);
                statement.setInt(3, quantity);
                statement.setString(4, ISBN);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new Book was inserted successfully!");
                }
            }
        } else {
            System.out.println("Invalid book data. Please check your input.");
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
        Scanner scanner= new Scanner(System.in);

        System.out.println("Enter ISBN : ");
        String ISBN =  scanner.nextLine();
        System.out.println("**************** Edit Book *****************");
        System.out.println("Enter New Title : ");
        String title = scanner.nextLine();
        System.out.println("Enter New Author : ");
        String author = scanner.nextLine();

        System.out.println("Enter New quantity : ");
        int quantity =  scanner.nextInt();


        String sql = "UPDATE books SET title=?, author=?, quantity=? WHERE isbn=?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, title);
        statement.setString(2, author);
        statement.setInt(3, quantity);
        statement.setString(4, ISBN);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
    System.out.println("An existing Book was updated successfully!");
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

            System.out.println(book);
            found = true;
        }
        if (!found) {
            System.out.println("No results");
        }
    }
}
