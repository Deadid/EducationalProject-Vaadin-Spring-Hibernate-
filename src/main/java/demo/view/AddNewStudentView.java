package demo.view;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import demo.model.Student;
import demo.service.StudentService;

@SpringView(name = AddNewStudentView.VIEW_NAME)
public class AddNewStudentView extends FormLayout implements View{
	
	public static final String VIEW_NAME = "add-new-student";
	
	@Autowired
	private StudentService studentService;

	@Override
	public void enter(ViewChangeEvent event) {
		Student newStudent;
		Label title = new Label("Add new student");
		addComponent(title);
		
		TextField nameField = new TextField("First name");
		nameField.addValidator(new StringLengthValidator("Name must be between 2 and 15 symbols", 2, 15, false));
		nameField.setRequired(true);
		
		TextField lastnameField = new TextField("Last name");
		lastnameField.addValidator(new StringLengthValidator("Last name must be between 2 and 15 symbols", 2, 15, false));
		lastnameField.setRequired(true);
		
		Button submit = new Button("Add new student");
		submit.addClickListener(e -> {
			if(nameField.isValid() && lastnameField.isValid()) {
				String firstName = nameField.getValue();
				String lastName = lastnameField.getValue();
				studentService.create(new Student(firstName, lastName));
				Notification.show("Added new student!");
			}
			
		});
		addComponents(nameField, lastnameField, submit);
	}

}
