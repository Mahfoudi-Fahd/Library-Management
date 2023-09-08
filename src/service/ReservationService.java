package service;

import java.sql.SQLException;

import repository.ReservationRepository;

public class ReservationService {


    public void reserveBook() throws SQLException {

            ReservationRepository.reserve();
        }

        public void returnBorrowedBook() throws SQLException {
            ReservationRepository.returnBook();
        }
    }

