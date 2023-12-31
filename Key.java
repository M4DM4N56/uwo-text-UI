public class Key {
    private String label;
    private int type;
    
    
	// constructor method
	public Key(String theLabel, int theType) {
		this.label = theLabel.toLowerCase();
	    this.type = theType;
	} // constructor key
		
	
	public String getLabel() {
    	return this.label;
    } // method get label

	
    public int getType() {
    	return this.type;
    } // method get type
    
    
    public int compareTo(Key k) {
        // checks if label and type are equal
        if (this.label.equals(k.getLabel()) && this.type == k.getType()) return 0;
        // checks if this is smaller than k in regards to label or type
        else if (this.label.compareTo(k.getLabel()) < 0 || (this.label.equals(k.getLabel()) && this.type < k.getType())) return -1;
        else return 1;
        
    } // method compare to
    
    
	
} // class key
