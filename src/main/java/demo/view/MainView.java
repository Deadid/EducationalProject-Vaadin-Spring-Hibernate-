package demo.view;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.grid.MGrid;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.sort.Sort;
import com.vaadin.data.util.AbstractBeanContainer;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import demo.factory.LazyContainersFactoryImpl;
import demo.model.Book;
import demo.model.Student;
import demo.service.BookService;
import demo.service.StudentService;

/**
 * Main view that used for navigation via another views,
 *  contains lists of students and books,
 *  also buttons for navigation to the adding forms.
 * @author smakhov
 *
 */
@SpringView(name = MainView.VIEW_NAME)
public class MainView  extends VerticalLayout implements View{

	public static final String VIEW_NAME = "";
	
	@Autowired
	private LazyContainersFactoryImpl lazyContainerFactory;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private BookService bookService;
	
	private Grid studentGrid;
	
	private Grid bookGrid;
	
	/**
	 * Adds first grid that contains students of university
	 */
	private void addStudentsGrid() {
		// Used for lazy loading
		JPAContainer<Student> studentContainer = lazyContainerFactory.makeReadOnly(Student.class);
		studentGrid = new Grid(studentContainer);
		studentGrid.setWidth("100%");
		studentGrid.setColumnOrder("id", "surname", "name");
		studentGrid.setCaption("University students");
		
		// This listener navigates us to the Student view by selecting a row. Student id used as parameter to determine which student was selected. 
		studentGrid.addSelectionListener(e -> {
			Integer id = (Integer) e.getSelected().stream().findFirst().get();
			getUI().getNavigator().navigateTo(StudentView.VIEW_NAME + "/" + id);
		});
		addComponent(studentGrid);
	}
	
	/**
	 * Adds second grid to the view that contains books list.
	 */
	private void addBooksGrid() {
		List<Book> bookList = bookService.findAll();
		/* Used bean container because BeanItemContainer uses equals() 
		(it checks all fields) to check existing bean. I need only id equation.
		You can change only title and author, nor id, neither students that hold them (use studentView).
		*/
		BeanContainer<Integer,Book> bookContainer = new BeanContainer<>(Book.class);
		bookContainer.setBeanIdResolver(new AbstractBeanContainer.BeanIdResolver<Integer, Book>() {

			@Override
			public Integer getIdForBean(Book bean) {
				return bean.getId();
			}
		});
		
		bookContainer.addAll(bookList);
		bookGrid = new Grid(bookContainer);
		bookGrid.setColumnOrder("id", "author", "title");
		bookGrid.getColumn("student").setEditable(false);
		bookGrid.setEditorEnabled(true);
		bookGrid.setSortOrder(Sort.by("id").build());
		bookGrid.getColumn("id").setEditable(false);
		bookGrid.setSelectionMode(SelectionMode.NONE);
		
		//Updates db with new values.
		bookGrid.getEditorFieldGroup().addCommitHandler(new CommitHandler() {
			
			@Override
			public void preCommit(CommitEvent commitEvent) throws CommitException {
			}
			
			@Override
			public void postCommit(CommitEvent commitEvent) throws CommitException {
				Book editedBook = bookContainer.getItem(bookGrid.getEditedItemId()).getBean();
				bookService.update(editedBook);
				
			}
		});
		
		addComponent(bookGrid);
		
	}
	/**
	 * Adds last two buttons to the form.
	 */
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
