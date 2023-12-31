public class Record {
	private Key theKey;
	String data;
	
	// record constructor
	public Record(Key k, String theData) {
		this.theKey = k;
		this.data = theData;
	} // constructor record
	
	
	public Key getKey() {
		return this.theKey;
	} // method get key 
	
	
	public String getDataItem() {
		return this.data;
	} // method get data item
	
	
} // class record
