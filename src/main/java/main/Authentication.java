package main;

import java.net.UnknownHostException;

import com.vaadin.ui.UI;

import model.Encode;
import model.User;



public class Authentication {
	
	private User user = null;
	
	public Authentication() {
	}

	public User getUser() {
		return this.user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Boolean authenticate(String userID, String psw){
		
		psw = Encode.cryptingString(psw);
		
		if(dao.UserDAO.userExists(userID)) {
			System.out.println("returned true from user exists");
			try {
				setUser(dao.UserDAO.getUser(userID, psw)); 
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			
			if(this.user != null) { 
				System.out.println("User: " + this.user.getName() + " autenticato.");
				UI.getCurrent().getSession().setAttribute("AUTH", this);
				return true;
			}
			else {
				System.out.println("Incorrect psw.");
			}
		}
		else {
			System.out.println("User not found.");
		}
		
		return false;
		
	}
	

	public void doLogout() {
		System.out.println("Utente: " + this.user.getName() + " è stato disconnesso.");
		this.user = null;
		UI.getCurrent().getSession().setAttribute("AUTH", this);
	}

}