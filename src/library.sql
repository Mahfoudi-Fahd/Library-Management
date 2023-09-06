DROP DATABASE IF EXISTS library;
CREATE DATABASE library;
USE library;

CREATE TABLE books(
                      bookId    INT PRIMARY KEY AUTO_INCREMENT ,
                      title     VARCHAR(255),
                      author    VARCHAR(255),
                      isbn      VARCHAR(255),
                      quantity       INT(255)
);
CREATE TABLE users(
                     userId           INT PRIMARY KEY AUTO_INCREMENT ,
                     name         VARCHAR(255)
);
CREATE TABLE reservations(
                    reservationId     INT PRIMARY KEY AUTO_INCREMENT,
                    userId            INT,
                    bookId            INT,
                    borrowDate        DATE,
                    returnDate        VARCHAR(255),
                    reservationStatus ENUM('RETURNED','LOST'),
                    FOREIGN KEY (userId) REFERENCES users (userId),
                    FOREIGN KEY (bookId) REFERENCES books (bookId)
);

