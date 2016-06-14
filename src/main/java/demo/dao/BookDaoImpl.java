package demo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.model.Book;

@Repository
@Transactional
public class BookDaoImpl extends AbstractDao<Book> implements BookDao{

	
	@Override
	public List<Book> findAll() {
		CriteriaQuery query = getCurrentSession().getCriteriaBuilder().createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		return getCurrentSession().createQuery(query).getResultList();
	}

	@Override
	
	public List<Book> findAvailableBooks() {
		Session session = getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery query = session.getCriteriaBuilder().createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		query.where(root.get("student").isNull());
		return getCurrentSession().createQuery(query).getResultList();
	}

	@Override
	public void remove(Book book) {
		Session session = getCurrentSession();
		session.remove(session.merge(book));
	}

	@Override
	public Book findById(Integer id) {
		return getCurrentSession().get(Book.class, id);
	}

}
