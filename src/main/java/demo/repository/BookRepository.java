package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import demo.model.Book;

/**
 * Book dao.
 * @author smakhov
 *
 */
public interface BookRepository extends PagingAndSortingRepository<Book, Integer> {
	
	/**
	 * Finds available books (no one student hold them).
	 * @return
	 */
	List<Book> findByStudentIsNull();
	
}
