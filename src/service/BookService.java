package service;

import entity.Book;
import repository.BookRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class BookService {




    public void saveBook() throws SQLException {
        BookRepository.insert();
    }

    public List<Book> getAllBooks() throws SQLException {
        return BookRepository.getAll();
    }

    public void editBook() throws SQLException {
        BookRepository.update();
    }

    public void deleteBook() throws SQLException {
        BookRepository.delete();
    }

    public void searchByAuthor() throws SQLException{
        BookRepository.searchByAuthor();
    }

    public void searchByTitle() throws SQLException, IOException {
        BookRepository.searchByTitle();
    }

}