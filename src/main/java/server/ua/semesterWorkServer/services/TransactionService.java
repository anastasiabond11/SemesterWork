package server.ua.semesterWorkServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.ua.semesterWorkServer.entities.Book;
import server.ua.semesterWorkServer.entities.Transaction;
import server.ua.semesterWorkServer.entities.User;
import server.ua.semesterWorkServer.repos.TransactionRepo;

import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    public Transaction createTransaction(User currentUser, Book currentBook, String action) {
        Transaction transaction = new Transaction();
        transaction.setUser(currentUser);
        transaction.setBook(currentBook);
        transaction.setAction(action);
        transaction.setDate(new java.util.Date());
        return transactionRepo.save(transaction);
    }

    public Transaction putTransaction(Long transactionId, String newAction) {
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        transaction.setAction(newAction);

        return transactionRepo.save(transaction);
    }

    public Transaction getTransaction(Long transactionId) {
        return transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));
    }

    public void deleteTransaction(Long transactionId) {
        Transaction transaction = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + transactionId));

        transactionRepo.delete(transaction);
    }
}
