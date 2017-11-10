package com.example.MonitoraggioCVC;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("mytheme")
public class Test1 extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Label title = new Label(
                "<h1 style=\"text-align: center;\">\r\n" + 
                "<strong>Gestore Cateteri</strong></h1>");
        title.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);

        layout.addComponent(title);
        layout.setComponentAlignment(title, Alignment.TOP_CENTER);
        
        
        
        
        TextField name = new TextField();
        name.setPlaceholder("Nome Utente");
        name.setMaxLength(20);
        //updateCaption(0);
 
        name.addValueChangeListener(event -> updateCaption(event.getValue().length()));
        
        layout.addComponent(name);

        

        setContent(layout);
    }
    
    private Object updateCaption(int length) {
		// TODO Auto-generated method stub
    	test.getCurrent().push();
		return null;
	}
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
