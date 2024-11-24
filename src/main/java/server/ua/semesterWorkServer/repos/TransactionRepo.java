package server.ua.semesterWorkServer.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import server.ua.semesterWorkServer.entities.Transaction;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
}
