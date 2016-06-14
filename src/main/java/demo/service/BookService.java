package demo.service;

import java.util.List;

import demo.model.Book;

public interface BookService {
	List<Book> getAll();
	
	void remove(Book book);
	
	void update(Book book);
	
	void create(Book book);
	
	Book findById(Integer id);
	
	List<Book> findAvailableBooks();
}
