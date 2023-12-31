public class BSTNode {
	private Record record;
	private BSTNode leftChild;
	private BSTNode rightChild;
	private BSTNode parent;
	
	
	// constructor
	public BSTNode(Record item) {
        this.record = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
	} // constructor method
	
	
	//	-- GETTERS -- \\
	public Record getRecord() {
		return this.record;
	} // get record method
	
	
	public BSTNode getLeftChild() {
		return this.leftChild;
	} // get left child method
	
	
	public BSTNode getRightChild() {
		return this.rightChild;
	} // get right child  method
	
	
	public BSTNode getParent() {
		return this.parent;
	} // get parent  method
	
	
	//	-- SETTERS -- \\
	public void setRecord(Record d) {
		this.record = d;
	} // set record method
	
	
	public void setLeftChild(BSTNode u) {
		this.leftChild = u;
	} // set left child
	
	
	public void setRightChild(BSTNode u) {
		this.rightChild = u;
	} // set right child
	
	
	public void setParent(BSTNode u) {
		this.parent = u;
	} // set parent node
	
	
	public boolean isLeaf() {
		if (this.leftChild ==  null && this.rightChild ==  null && record ==  null) return true;
		return false;
	} // is leaf method
	
	
} // class BSTNode
