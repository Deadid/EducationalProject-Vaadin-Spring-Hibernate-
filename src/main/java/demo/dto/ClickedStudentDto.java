package demo.dto;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.spring.annotation.UIScope;

import demo.model.Student;

@Component
@UIScope
public class ClickedStudentDto {

	private Student clickedStudent;
	public Student getClickedStudent() {
		return clickedStudent;
	}

	public void setClickedStudent(Student student) {
		clickedStudent = student;
	}

}
