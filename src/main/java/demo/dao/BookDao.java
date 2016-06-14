package demo.dao;

import java.util.List;

import demo.model.Book;

public interface BookDao {
	List<Book> findAll();

	void remove(Book book); 
	
	void update(Book book);
	
	List<Book> findAvailableBooks();
}
