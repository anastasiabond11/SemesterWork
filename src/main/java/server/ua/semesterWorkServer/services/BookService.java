package server.ua.semesterWorkServer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import server.ua.semesterWorkServer.entities.Book;
import server.ua.semesterWorkServer.repos.BookRepo;


@Service
public class BookService {

    @Autowired
    private BookRepo bookRepo;

    public Book getBook(Long bookId) {
        return bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book with ID " + bookId + " was not found."));
    }

    public Book createBook(Book book) {
        return bookRepo.save(book);
    }


    public void deleteBook(Long id) {
        Book book = getBook(id);
        bookRepo.delete(book);
    }



    public Book putBook(Long id, Book updatedBook) {
        Book existingBook = getBook(id);
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor((updatedBook.getAuthor()));
        existingBook.setIsbn(updatedBook.getIsbn());
        existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
        return updatedBook;
    }
}