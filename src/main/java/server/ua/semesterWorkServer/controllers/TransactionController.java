package server.ua.semesterWorkServer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import server.ua.semesterWorkServer.entities.Book;
import server.ua.semesterWorkServer.entities.Transaction;
import server.ua.semesterWorkServer.entities.User;
import server.ua.semesterWorkServer.services.BookService;
import server.ua.semesterWorkServer.services.TransactionService;
import server.ua.semesterWorkServer.services.UserService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Transaction> createTransaction(
            @RequestParam("userId") Long userId,
            @RequestParam("bookId") Long bookId,
            @RequestParam("action") String action) {

        User currentUser = userService.getUser(userId);
        Book currentBook = bookService.getBook(bookId);
        Transaction transaction = transactionService.createTransaction(currentUser, currentBook, action);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transaction);
    }

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> putTransaction(
            @PathVariable("id") Long transactionId,
            @RequestParam("action") String newAction) {

        Transaction transaction = transactionService.putTransaction(transactionId, newAction);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transaction);
    }

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable("id") Long transactionId) {
        Transaction transaction = transactionService.getTransaction(transactionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(transaction);
    }

    @CrossOrigin (origins = "*")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long transactionId) {
        transactionService.deleteTransaction(transactionId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Transaction with ID: " + transactionId + " was deleted successfully.");
    }
}
