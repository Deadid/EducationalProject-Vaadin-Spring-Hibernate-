package demo.dao;

import java.util.List;

import demo.model.Book;

public interface BookDao {
	List<Book> findAll();

	void remove(Book book); 
	
	void update(Book book);
	
	Book findById(Integer id);
	
	void create(Book book);
	
	List<Book> findAvailableBooks();
}
