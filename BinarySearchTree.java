public class BinarySearchTree {
    private BSTNode root;
	
    
    // constructor
	public BinarySearchTree() {
		// creates empty tree
		this.root = new BSTNode(null);
	} // constructor method
	
	
	public BSTNode getRoot() {
		return this.root;
	} // get root method
	
	
	// gets node with corresponding key
	public BSTNode get(BSTNode r, Key k) {
		if (r.isLeaf()) return r;
		int compare = k.compareTo(r.getRecord().getKey());
		
        // no key found - base case
		if (r ==  null || r.isLeaf()) return null;
        // key found in current node - base case
        if (compare ==  0) return r; 
        
        // search recursively left  - recursive case
        else if (compare ==  -1) return get(r.getLeftChild(), k); 
        // search recursively right - recursive case
        else  return get(r.getRightChild(), k); 

	} // method get
	
	
	// as per an example in the lecture slides, 
	// p = current node, 
	// parent = parent of p, 
	// c = left child of p
	// cPrime = right child of p
	
	
	// inserts node using get method
	public void insert(BSTNode r, Record d) throws DictionaryException{
		Key key = d.getKey();
		BSTNode p = get(r, key);
		
		if (!p.isLeaf()) throw new DictionaryException ("Error: Duplicate node");
		
		else {
			p.setRecord(d);
			// makes left child, sets the parent and child connection
			BSTNode leftChild = new BSTNode(null);
			leftChild.setParent(p);
			p.setLeftChild(leftChild);
			
			// makes left child, sets the parent and child connection
			BSTNode rightChild = new BSTNode(null);
			rightChild.setParent(p);
			p.setRightChild(rightChild);
		} // else
		
	} // method insert
	
	
	// remove node
	public void remove(BSTNode r, Key k) throws DictionaryException {
        BSTNode p = get(r, k);
        BSTNode c = p.getLeftChild();
    	BSTNode cPrime = p.getRightChild();
        BSTNode parent = p.getParent();
        
        // if the found node is a leaf, the key doesn't exist. throw exception
        if(p.isLeaf()) throw new DictionaryException("Key does not exist within BST");
        
        else {
        	// check if left child is leaf
            if(c.isLeaf()) {
                if(parent !=  null) { // if the parent is null, 
                    int cmp = p.getRecord().getKey().compareTo(parent.getRecord().getKey());
                     // reconnects node without p
                    if(cmp > 0) {
                    	parent.setRightChild(cPrime);
                        cPrime.setParent(parent);
                    } // if 
                    else {
                    	parent.setLeftChild(cPrime);
                        cPrime.setParent(parent);
                    } // else
                } // if 
                else root = cPrime;
            } // if
            else {
            	// recursively call the function, removing smaller this time
                BSTNode smaller = smallest(p.getRightChild());
                p.setRecord(smaller.getRecord());
                remove(smaller, smaller.getRecord().getKey());
            } // else
        } // else
    } // method remove
	
	
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode p = get(r, k);
        BSTNode parent = p.getParent();

        // if the root is a leaf, return null
        if (root.isLeaf()) return null;
        	
        // if the right child exists, get the smallest node on the right side
    	if (!p.isLeaf() && !p.getRightChild().isLeaf()) return smallest(p.getRightChild());
    	else {
    		// if not, travel up the tree for the successor
    		while(parent !=  null && parent.getRightChild().equals(p)) {
    			p = parent;
    			parent = parent.getParent();
    		} // while
    		if (p ==  root) return null;
    		else return parent; // return predecessor
    	} // else
    	
    } // method successor
    
    
    public BSTNode predecessor(BSTNode r, Key k) {
    	BSTNode p = get(r, k);
        BSTNode parent = p.getParent();

        // if the root is a leaf, return null
        if (root.isLeaf()) return null;
        
        // if the left child exists, get the largest node on the left side
        if (!p.isLeaf() && !p.getLeftChild().isLeaf()) return largest(p.getLeftChild());
        
        else {
        	// traverse up the tree to find the predecessor
    		while(parent !=  null && parent.getLeftChild().equals(p)) {
    			p = parent;
    			parent = parent.getParent();
    		} // while	
    		if (p ==  root) return null;
    		else return parent; // return the predecessor
    	} // else
        
    } // method predecessor
	
	
    // smallest and largest nodes taken from lecture slides and translated to java code
    public BSTNode smallest(BSTNode r) {
	    if (r ==  null) return r;
	    else {
	    	BSTNode p = r;
	    	while (!p.isLeaf()) p = p.getLeftChild();
	    	return p.getParent();
	    } // else
    } // method smallest
    
    
    public BSTNode largest(BSTNode r) {
	    if (r ==  null) return r;
	    else {
	    	BSTNode p = r;
	    	while (!p.isLeaf()) p = p.getRightChild();
	    	return p.getParent();
	    } // else
    } // method largest
	

	
} // class Binary Search Tree