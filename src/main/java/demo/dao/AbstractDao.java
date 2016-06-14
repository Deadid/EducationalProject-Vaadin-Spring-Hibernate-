package demo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public abstract class AbstractDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;

	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public abstract List<T> findAll();
	
	@Transactional
	public abstract T findById(Integer id);
	
	@Transactional
	public void update(T entity) {
		getCurrentSession().saveOrUpdate(entity);;
	}
	
	@Transactional
	public void create(T entity) {
		getCurrentSession().save(entity);
	}
}
