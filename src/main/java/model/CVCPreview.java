package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CVCPreview {
	
	private int idCVC;
	private String name;
	private String surname;
	private LocalDate birthday;
	private String fiscalCode;
	private String site;
	private boolean closed;
	
	public CVCPreview(int idCVC, String name, String surname, LocalDate birthday, String fiscalCode, String site, boolean closed ) {
		this.idCVC=idCVC;
		this.name=name;
		this.surname=surname;
		this.birthday=birthday;
		this.fiscalCode=fiscalCode;
		this.site=site;
		this.closed=closed;
	}
	
	public int getCVCId() {
		return idCVC;
	}
	
	public String getName() {
		return name.replace('_', ' ');
	}
	
	public String getSurname() {
		return surname.replace('_', ' ').replace("\'", "'");
	}
	
	public String getBirthday() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		try {
			 return birthday.format(dtf);
		} catch (java.time.format.DateTimeParseException e) {
		   e.printStackTrace();
		}
		return "";
	}
	
	public String getFiscalCode() {
		return fiscalCode;
	}
	
	public String getSite() {
		return site;
	}
	
	public boolean getClosed() {
		return closed;
	}
}
