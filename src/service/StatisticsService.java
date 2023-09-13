package service;

import repository.ReservationRepository;
import repository.StatisticsRepository;

import java.sql.SQLException;

public class StatisticsService {
    public void getStatistics() throws SQLException {
        StatisticsRepository.generateLibraryStatistics();
    }


}
