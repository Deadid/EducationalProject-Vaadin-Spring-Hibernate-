package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.model.Student;
import demo.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public List<Student> getAll() {
		return studentRepository.findAll();
	}

	@Override
	public void update(Student student) {
		Student oldStudent = studentRepository.findOne(student.getId());
		if(oldStudent == null) {
			throw new RuntimeException("Student not found");
		}
		studentRepository.save(student);
	}

	@Override
	public void create(Student student) {
		studentRepository.save(student);
	}

	@Override
	public Student findById(Integer id) {
		return studentRepository.findOne(id);
	}

}
