package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.dao.StudentDao;
import demo.model.Student;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public List<Student> getAll() {
		return studentDao.findAll();
	}

	@Override
	public void update(Student student) {
		studentDao.update(student);
	}

}
