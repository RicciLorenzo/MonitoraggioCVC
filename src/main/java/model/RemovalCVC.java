package model;

import java.time.LocalDate;

public class RemovalCVC {

	private int idCVC;
	private LocalDate removalDate;
	private String motivation;
	private boolean cvcTipCulture;
	private boolean cvcBacteremia;
	private boolean closed;
	
	public RemovalCVC(LocalDate removalDate, String motivation, boolean cvcTipCulture, boolean cvcBacteremia, boolean closed ) {
		this.removalDate=removalDate;
		this.motivation=motivation;
		this.cvcTipCulture=cvcTipCulture;
		this.cvcBacteremia=cvcBacteremia;
	}
	
	public RemovalCVC(int idCVC, LocalDate removalDate, String motivation, boolean cvcTipCulture, boolean cvcBacteremia, boolean closed ) {
		this(removalDate, motivation, cvcTipCulture, cvcBacteremia, closed);
		this.idCVC=idCVC;
	}
	public int getId() {
		return this.idCVC;
	}
	
	public LocalDate getRemovalDate() {
		return this.removalDate;
	}
	
	public String getMotivation() {
		return this.motivation;
	}
	
	public boolean getCVCTip() {
		return this.cvcTipCulture;
	}
	
	public boolean getCVCBact() {
		return this.cvcBacteremia;
	}
	
	public boolean getClosed() {
		return this.closed;
	}
	
}
