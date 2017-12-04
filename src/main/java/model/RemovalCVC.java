package model;

import java.util.Date;

public class RemovalCVC {

	private Date removalDate;
	//change in enum for other motivation
	private MotivationRemoval motivation;
	private String otherMotivation;
	private boolean cvcTipCulture;
	//private boolean cvcBloodCulture;
	private String cvcBloodCulture;
	private boolean transferWithCvc;
	private Date transferDate;
	private String transferLocation;
	private boolean cvcInfection;
	private boolean cvcBacteremia;
	
	public RemovalCVC(Date removalDate, MotivationRemoval motivation, boolean cvcTipCulture, boolean cvcInfection, boolean cvcBacteremia) {
		this.removalDate=removalDate;
		this.motivation=motivation;
		this.cvcInfection=cvcInfection;
		this.cvcBacteremia=cvcBacteremia;
	}
	
	public RemovalCVC(Date removalDate, MotivationRemoval motivation, boolean cvcTipCulture, String cvcBloodCulture, boolean cvcInfection, boolean cvcBacteremia) {
		this.removalDate=removalDate;
		this.motivation=motivation;
		this.cvcInfection=cvcInfection;
		this.cvcBacteremia=cvcBacteremia;
	}
	

	public RemovalCVC(Date removalDate, MotivationRemoval motivation, boolean cvcTipCulture, Date transferDate, String transferLocation, boolean cvcInfection, boolean cvcBacteremia) {
		this.removalDate=removalDate;
		this.motivation=motivation;
		this.cvcTipCulture=cvcTipCulture;
		this.cvcInfection=cvcInfection;
		this.cvcBacteremia=cvcBacteremia;
	}
	
	public RemovalCVC(Date removalDate, MotivationRemoval motivation, boolean cvcTipCulture, String cvcBloodCulture, Date transferDate, String transferLocation, boolean cvcInfection, boolean cvcBacteremia) {
		this.removalDate=removalDate;
		this.motivation=motivation;
		this.cvcTipCulture=cvcTipCulture;
		this.cvcBloodCulture=cvcBloodCulture;
		this.transferDate=transferDate;
		this.transferLocation=transferLocation;
		this.cvcInfection=cvcInfection;
		this.cvcBacteremia=cvcBacteremia;
	}
	
	public Date getRemovalDate() {
		return this.removalDate;
	}
	
}
