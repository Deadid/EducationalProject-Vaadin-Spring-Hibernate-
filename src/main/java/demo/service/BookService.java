package demo.service;

import java.util.List;

import demo.model.Book;

public interface BookService {
	List<Book> getAll();
	
	void remove(Book book);
	
	void update(Book book);
	
	List<Book> findAvailableBooks();
}
