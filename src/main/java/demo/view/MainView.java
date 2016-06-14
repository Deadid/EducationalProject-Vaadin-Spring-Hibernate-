package demo.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

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
	private BookService bookService;
	
	private Grid studentGrid;
	
	private Grid bookGrid;
	
	private void addStudentsGrid() {
		List<Student> studentList = studentService.getAll();
		BeanItemContainer<Student> studentContainer = new BeanItemContainer<>(Student.class, studentList);
		studentGrid = new Grid(studentContainer);
		studentGrid.setWidth("100%");
		studentGrid.setCaption("University students");
		studentGrid.setSelectionMode(SelectionMode.SINGLE);
		studentGrid.addSelectionListener(e -> {
			Integer id = ((Student) e.getSelected()).getId();
			getUI().getNavigator().navigateTo(StudentView.VIEW_NAME + "/" + id);
		});
		//studentGrid.setEditorEnabled(true);
		addComponent(studentGrid);
	}
	
	private void addBooksGrid() {
		List<Book> bookList = bookService.getAll();
		BeanItemContainer<Book> bookContainer = new BeanItemContainer<>(Book.class, bookList);
		bookGrid = new Grid(bookContainer);
		bookGrid.setColumnOrder("id", "author", "title");
		bookGrid.removeColumn("student");
		bookGrid.setEditorEnabled(true);
		bookGrid.getEditorFieldGroup().addCommitHandler(new CommitHandler() {
			
			@Override
			public void preCommit(CommitEvent commitEvent) throws CommitException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void postCommit(CommitEvent commitEvent) throws CommitException {
				Book editedBook = (Book) bookGrid.getEditedItemId();
				bookService.update(editedBook);
			}
		});
		addComponent(bookGrid);
		bookGrid.getColumn("id").setEditable(false);
	}
	
	private void addFooterButtons() {
		HorizontalLayout buttonsFooterLayout = new HorizontalLayout();
		Button addNewStudentButton = new Button("Add new student", e -> {
			getUI().getNavigator().navigateTo(AddNewStudentView.VIEW_NAME);
		});
		Button addNewBookButton = new Button("Add new book", e -> {
			getUI().getNavigator().navigateTo(AddNewBookView.VIEW_NAME);
		});
		buttonsFooterLayout.setMargin(true);
		buttonsFooterLayout.setSpacing(true);
		buttonsFooterLayout.addComponents(addNewBookButton, addNewStudentButton);
		addComponent(buttonsFooterLayout);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		addStudentsGrid();
		addBooksGrid();
		addFooterButtons();
	}
	
}
