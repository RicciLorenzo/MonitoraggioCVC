package model;

public class Complication {

	private boolean complication;
	private boolean hematoma;
	private boolean arteryPuncture;
	private boolean pnx;
	private boolean other;
	private String otherS;
	
	public Complication(boolean complication) {
		this.complication=complication;
	}
	
	public Complication(boolean complication, boolean hematoma, boolean arteryPuncture, boolean pnx, boolean other) {
		this.complication=complication;
		this.hematoma=hematoma;
		this.arteryPuncture=arteryPuncture;
		this.pnx=pnx;
		this.other=other;
	}
	
	public Complication(boolean complication, boolean hematoma, boolean arteryPuncture, boolean pnx, boolean other, String otherS) {
		this.complication=complication;
		this.hematoma=hematoma;
		this.arteryPuncture=arteryPuncture;
		this.pnx=pnx;
		this.other=other;
		this.otherS=otherS;
	}
	

}
