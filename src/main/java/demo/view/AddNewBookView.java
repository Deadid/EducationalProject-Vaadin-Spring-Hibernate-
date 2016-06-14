package demo.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

import demo.model.Book;
import demo.service.BookService;

@SpringView(name = AddNewBookView.VIEW_NAME)
public class AddNewBookView extends FormLayout implements View{
	public static final String VIEW_NAME = "add-new-book";

	@Autowired
	private BookService bookService;
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		Label title = new Label("Add new book");
		addComponent(title);
		
		TextField authorField = new TextField("Author:");
		authorField.setRequired(true);
		
		TextField bookTitleField = new TextField("Title:");
		bookTitleField.setRequired(true);
		
		Button submit = new Button("Add book");
		submit.addClickListener(e -> {
			if(authorField.isValid() && bookTitleField.isValid()) {
				String author = authorField.getValue();
				String bookTitle = bookTitleField.getValue();
				bookService.create(new Book(author, bookTitle));
				Notification.show("Added new book!");
			}
		});
		addComponents(authorField, bookTitleField, submit);
	}
	
}
