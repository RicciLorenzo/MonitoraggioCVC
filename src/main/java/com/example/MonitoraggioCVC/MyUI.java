
package com.example.MonitoraggioCVC;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        
        Label title = new Label(
                "<h1 style=\"text-align: center;\">\r\n" + 
                "<strong>Gestore Cateteri</strong></h1>");
        title.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);

        Button button = new Button("Login");
        
        
        layout.addComponent(title);
        layout.setComponentAlignment(title, Alignment.TOP_CENTER);

        Label login = new Label(
        		"<h4>\r\nLogin</h4>\r\n");
        login.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);
        layout.addComponent(login);
        layout.setComponentAlignment(login, Alignment.TOP_CENTER);

        
        TextField nome = new TextField();
        nome.setPlaceholder("Nome Utente");
        nome.setMaxLength(20);
        
        PasswordField pass = new PasswordField();
        pass.setPlaceholder("Password");
        pass.setMaxLength(10);
 
        layout.addComponent(nome);
        layout.setComponentAlignment(nome, Alignment.BOTTOM_CENTER);
        
        layout.addComponent(pass);
        layout.setComponentAlignment(pass, Alignment.BOTTOM_CENTER);
        
        
        layout.addComponents(button);
        layout.setComponentAlignment(button, Alignment.TOP_CENTER );
        
        Label authors = new Label(
        		"<address>\r\n" + 
        		"	Author: Lorenzo Ricci - Emil Tomellini</address>\r\n");
        authors.setContentMode(com.vaadin.shared.ui.ContentMode.HTML);
        
        layout.addComponent(authors);
        layout.setComponentAlignment(authors, Alignment.BOTTOM_RIGHT);
        
        
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
