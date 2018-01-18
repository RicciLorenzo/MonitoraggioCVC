package model;


//TO BE MODIFIED!!!
public enum Structure {
	CVC_TUNNELLIZZATO("CVC tunnellizzato"),
	CVC_NON_TUNNELLIZZATO("CVC non tunnellizzato"),
	TOTALMENTE_IMPIANTATO("totalmente impiantato"),
	PICC("PICC"),
	CATETERE_MIDLINE("Catetere Midline"),
	PICC_CUFFIATO("PICC cuffiato"),
	MINIMIDLINE("Minimidline");
	
	private String displayName;

    Structure(String displayName) {
        this.displayName = displayName;
    }

    public String displayName() { return displayName; }

    // Optionally and/or additionally, toString.
    @Override public String toString() { return displayName; }
	
}
