package entity;

import entity.User;

import java.util.Date;

public class Reservation {
    private int reservationId;
    private User user;
    private Book book;
    private Date borrowDate;
    private String returnDate;
    private ReservationStatus reservationStatus;

    public Reservation(int reservationId, User user, Book book, Date borrowDate, String returnDate) {
        this.reservationId = reservationId;
        this.user = user;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public int getReservationId() {
        return reservationId;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }
}
