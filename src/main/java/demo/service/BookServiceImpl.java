package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.BookDao;
import demo.model.Book;

@Service("bookService")
public class BookServiceImpl implements BookService{

	@Autowired
	private BookDao bookDao;
	
	@Override
	public List<Book> getAll() {
		return bookDao.findAll();
	}

	@Override
	public List<Book> findAvailableBooks() {
		return bookDao.findAvailableBooks();
	}

	@Override
	public void remove(Book book) {
		bookDao.remove(book);
	}

	@Override
	public void update(Book book) {
		bookDao.update(book);
	}

}
