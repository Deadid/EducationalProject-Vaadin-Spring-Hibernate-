package demo.dao;

import java.util.List;

import demo.model.Book;
import demo.model.Student;

public interface StudentDao {
	List<Student> findAll();
	
	void update(Student student);
	
	Student findById(Integer id);
	
	void create(Student student);
}
