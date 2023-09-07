package entity;

public class Book {
        private int bookId;
        private String title;
        private String author;
        private int quantity;
        private String ISBN;

    public Book(String title, String author, int quantity, String ISBN) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.ISBN = ISBN;
    }

    public Book(int bookId, String title, String author, int quantity, String ISBN) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.quantity = quantity;
            this.ISBN = ISBN;

        }

        public int getBookId() {
            return bookId;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public String getISBN() {
            return ISBN;
        }

    @Override
    public String toString() {
        return "Book :  "+ title +" ---- "+ author +"---- "+ quantity + "---- "+ ISBN;
    }
}