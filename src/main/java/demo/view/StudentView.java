package demo.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import de.steinwedel.messagebox.MessageBox;
import demo.model.Book;
import demo.model.Student;
import demo.service.BookService;
import demo.service.StudentService;

@SpringView
public class StudentView extends VerticalLayout implements View{

	public static String VIEW_NAME = "student";
	
	@Autowired
	private BookService bookService;
	
	private BeanItemContainer<Book> takenBooksContainer;
	private BeanItemContainer<Book> availableBooksContainer;
	
	@Autowired
	private StudentService studentService;
	
	private MessageBox createDialog(String message, Runnable yesAction) {
		return MessageBox
				.createQuestion()
				.asModal(true)
				.withMessage(message)
				.withYesButton(yesAction)
				.withNoButton()
				.withSpacer();
	}
	
	private Grid createTakenBooksGrid(Student clickedStudent) {
		takenBooksContainer = new BeanItemContainer<>(Book.class, clickedStudent.getTakenBooks());
		Grid takenBooksGrid = new Grid(takenBooksContainer);
		takenBooksGrid.setWidth("50%");
		takenBooksGrid.setCaption("Taken books");
		takenBooksGrid.removeColumn("id");
		takenBooksGrid.addSelectionListener(e -> {
			Book clickedBook = (Book) e.getSelected();
			clickedBook.setStudent(null);
			createDialog("Student has returned the book?", () ->
			{
				bookService.update(clickedBook);
				takenBooksContainer.removeItem(clickedBook);
				availableBooksContainer.addItem(clickedBook);
			}).open();
		});
		return takenBooksGrid;
	}
	
	private Grid createAvailableBooksGrid(Student clickedStudent) {
		List<Book> availableBooks = bookService.findAvailableBooks();
		availableBooksContainer = new BeanItemContainer<>(Book.class, availableBooks);
		Grid availableBooksGrid = new Grid(availableBooksContainer);
		availableBooksGrid.setCaption("Available books");
		availableBooksGrid.removeColumn("student");
		availableBooksGrid.removeColumn("id");
		availableBooksGrid.addSelectionListener(e -> {
			Book clickedBook = (Book) e.getSelected();
			clickedBook.setStudent(clickedStudent);
			createDialog("Give this book to the student?", () -> {
				bookService.update(clickedBook);
				takenBooksContainer.addItem(clickedBook);
				availableBooksContainer.removeItem(clickedBook);
			}).open();
			
		});
		return availableBooksGrid;
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		Integer id = Integer.parseInt(event.getParameters());
		Student clickedStudent = studentService.findById(id);
		Label label = new Label(clickedStudent.getName() + " " + clickedStudent.getSurname());
		addComponent(label);
		
		Grid takenBooksGrid = createTakenBooksGrid(clickedStudent);
		Grid availableBooksGrid = createAvailableBooksGrid(clickedStudent);
		HorizontalLayout gridsLayout = new HorizontalLayout(takenBooksGrid, availableBooksGrid);
		addComponent(gridsLayout);
		
	}
	
}
