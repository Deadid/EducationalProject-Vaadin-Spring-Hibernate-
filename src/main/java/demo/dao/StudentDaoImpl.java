package demo.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.model.Student;

@Repository
@Transactional
public class StudentDaoImpl extends AbstractDao<Student> implements StudentDao{
	
	@Override
	public List<Student> findAll() {
		CriteriaQuery query = getCurrentSession().getCriteriaBuilder().createQuery(Student.class);
		Root<Student> root = query.from(Student.class);
		return getCurrentSession().createQuery(query).getResultList();
	}

}
