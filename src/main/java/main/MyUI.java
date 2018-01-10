package main;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import model.User;
import view.AddCVCView;
import view.AddPatientView;
import view.LoginView;
import view.SearchView;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 * 
 */
@SuppressWarnings("serial")
@Theme("mytheme")
@Title("MonitoraggioCVC")
@PreserveOnRefresh

public class MyUI extends UI {
	
	@Override
    protected void init(VaadinRequest vaadinRequest) {
		
		Authentication auth = new Authentication();
		VaadinSession.getCurrent().setAttribute("AUTH", auth);
    	
    	setTheme("mytheme");
    	addStyleName(ValoTheme.UI_WITH_MENU);
    System.out.println("banana");  
    	updateContent();
        
    }
	
	public void userLoggedOut() {
		Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
    		localAuth.doLogout();
    		UI.getCurrent().getSession().setAttribute("AUTH", localAuth);
        VaadinSession.getCurrent().close();
        Page.getCurrent().reload();
    }

    public void updateContent() {
    	
    	System.out.println("view???");
    	Authentication localAuth = (Authentication) UI.getCurrent().getSession().getAttribute("AUTH");
    User user = localAuth.getUser();
    	Page.getCurrent().setTitle("CVC");
    	setContent(new AddCVCView("VR382155"));
    	/*
    	if(user==null) {
    		setContent(new SearchView());
    		}
    	else {
    		setContent(new SearchView());
    		System.out.println("view di ricerca");
    		}
   */
    }
    
 
 
    
    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
}