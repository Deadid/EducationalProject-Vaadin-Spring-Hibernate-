package demo.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.VerticalLayout;

import demo.dto.ClickedStudentDto;
import demo.model.Book;
import demo.model.Student;
import demo.service.BookService;
import demo.service.StudentService;

@SpringView(name = MainView.VIEW_NAME)
public class MainView  extends VerticalLayout implements View{

	public static final String VIEW_NAME = "";
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private ClickedStudentDto clickedStudentDto;
	
	private Grid studentGrid;
	
	@PostConstruct
	public void init() {
		setMargin(true);
		
	}
	
	
	
	@Override
	public void enter(ViewChangeEvent event) {
		List<Student> studentList = studentService.getAll();
		
		BeanItemContainer<Student> studentContainer = new BeanItemContainer<>(Student.class, studentList);
		studentGrid = new Grid(studentContainer);
		studentGrid.setWidth("100%");
		studentGrid.setCaption("University students");
		studentGrid.setSelectionMode(SelectionMode.SINGLE);
		studentGrid.addItemClickListener(e -> {
			clickedStudentDto.setClickedStudent((Student) e.getItemId());
			getUI().getNavigator().navigateTo(StudentView.VIEW_NAME);
		});
		addComponent(studentGrid);
	}
	
}
