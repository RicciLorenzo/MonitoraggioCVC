package model;

public class Insertion {
	
	private boolean insertionMode; //true urgent
	private boolean difficultyInsertion;
	private String insertionSite;
	private boolean insertionSide; //dx true, sx false

	public Insertion(boolean insertionMode, boolean difficultyInsertion, String insertionSite, boolean insertionSide) {
		this.insertionMode = insertionMode;
		this.difficultyInsertion = difficultyInsertion;
		this.insertionSite = insertionSite;
		this.insertionSide = insertionSide;
	}
	
	public boolean getInsertionMode() {
		return this.insertionMode;
	}
	
	public boolean getdiffInsertion() {
		return this.difficultyInsertion;
	}
	
	public String getInsertionSite() {
		return this.insertionSite; 
	}
	
	public boolean getInsertionSide() {
		return this.insertionSide;
	}
	
}
