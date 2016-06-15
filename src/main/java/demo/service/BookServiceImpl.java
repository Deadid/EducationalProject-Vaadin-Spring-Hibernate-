package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.model.Book;
import demo.repository.BookRepository;

@Service("bookService")
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findAll() {
		// Used page request because wanted to try it (never used Spring Data before) :)
		Page<Book> foundPage = bookRepository.findAll(new PageRequest(0, 3));
		return foundPage.getContent();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Book> findAvailableBooks() {
		return bookRepository.findByStudentIsNull();
	}

	@Override
	public void remove(Book book) {
		bookRepository.delete(book);
	}

	/**
	 * Update book in database
	 * @throws RuntimeException if book with id is not existed before.
	 */
	@Override
	public void update(Book book) {
		Book oldBook = bookRepository.findOne(book.getId());
		if(oldBook == null) {
			throw new RuntimeException("Book not exists");
		}
		bookRepository.save(book);
	}

	@Override
	public Book findById(Integer id) {
		return bookRepository.findOne(id);
	}

	@Override
	public void create(Book book) {
		bookRepository.save(book);
	}

}
