package myy803.socialbookstore.mappers;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import myy803.socialbookstore.datamodel.Book;
import org.springframework.data.jpa.repository.Query;

public interface BookMapper extends JpaRepository<Book, Integer> {
	List<Book> findByTitle(String title);
	List<Book> findByTitleContaining(String title);

	@Query(value = "SELECT * FROM Books ORDER BY RAND() LIMIT 1", nativeQuery = true)
	Optional<Book> findRandomEntity();

}
