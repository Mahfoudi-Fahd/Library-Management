package entity;

public class Book {
        private int bookId;
        private String title;
        private String author;
        private int quanity;
        private int ISBN;

        public Book(int bookId, String title, String author, int quanity, int ISBN) {
            this.bookId = bookId;
            this.title = title;
            this.author = author;
            this.quanity = quanity;
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

        public int getISBN() {
            return ISBN;
        }



}
