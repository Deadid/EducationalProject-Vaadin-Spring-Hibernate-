package demo.service;

import java.util.List;

import demo.model.Student;

public interface StudentService {
	List<Student> getAll();
	
	void update(Student student);
}
