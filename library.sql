DROP DATABASE IF EXISTS library;
CREATE DATABASE library;
SET GLOBAL event_scheduler = ON;
USE library;


CREATE TABLE books(
                      id    INT PRIMARY KEY AUTO_INCREMENT ,
                      title     VARCHAR(255),
                      author    VARCHAR(255),
                      isbn      VARCHAR(255),
                      quantity       INT(255)
);
CREATE TABLE users(
                     id           INT PRIMARY KEY AUTO_INCREMENT ,
                     name         VARCHAR(255)
);
CREATE TABLE reservations(
                    id     INT PRIMARY KEY AUTO_INCREMENT,
                    user_id            INT,
                    book_id            INT,
                    borrowDate        DATE,
                    returnDate        VARCHAR(255),
                    reservationStatus ENUM('RESERVED','RETURNED','LOST'),
                    FOREIGN KEY (user_id) REFERENCES users (id),
                    FOREIGN KEY (book_id) REFERENCES books (id)
);

DELIMITER //
CREATE EVENT MarkBooksAsLost
ON SCHEDULE EVERY 1 DAY
STARTS TIMESTAMP(CURRENT_DATE, '00:00:00')
DO
BEGIN
UPDATE book_reservations
SET reservation_status = 'LOST'
WHERE reservationStatus = 'RESERVED' AND returnDate < CURDATE();
END;
//
DELIMITER ;
