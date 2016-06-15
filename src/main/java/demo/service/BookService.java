package demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import demo.model.Book;

/**
 * Book service, should contain bussiness logic.
 * @author smakhov
 *
 */
public interface BookService {
	List<Book> findAll();
	
	void remove(Book book);
	
	void update(Book book);
	
	void create(Book book);
	
	Book findById(Integer id);
	
	/**
	 * Finds books available in library.
	 * @return list of available books.
	 */
	List<Book> findAvailableBooks();
}
