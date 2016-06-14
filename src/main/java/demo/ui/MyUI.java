package demo.ui;

import java.awt.Label;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.spring.server.SpringVaadinServlet;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import demo.model.Book;
import demo.model.Student;
import demo.service.BookService;
import demo.service.StudentService;

@Theme("valo")
@SpringUI
@Title("Library")
@SuppressWarnings("serial")
public class MyUI extends UI {

	Navigator navigator;
	
	@Autowired
    private SpringViewProvider viewProvider;

	
	@WebServlet(urlPatterns = {"/*"}, asyncSupported = true)
	public static class Servlet extends SpringVaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final Panel viewContainer = new Panel();
        viewContainer.setSizeFull();
        root.addComponent(viewContainer);
        root.setExpandRatio(viewContainer, 1.0f);
		navigator = new Navigator(this, viewContainer);
		navigator.addProvider(viewProvider);
	}

}
