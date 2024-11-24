package server.ua.semesterWorkServer.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import server.ua.semesterWorkServer.entities.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
