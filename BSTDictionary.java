public class BSTDictionary implements BSTDictionaryADT {
	BinarySearchTree bst;
	
	public BSTDictionary() {
		bst = new BinarySearchTree();
	} // constructor BSTDictionary
	
	
	@Override
	public Record get(Key k) {
		return bst.get(bst.getRoot(), k).getRecord();
	} // get method

	
	@Override
	public void put (Record d) throws DictionaryException{
		bst.insert(bst.getRoot(), d);
	} // put method

	@Override
	public void remove(Key k) throws DictionaryException {
		bst.remove(bst.getRoot(), k);
	} // remove method


	@Override
	public Record successor(Key k) {
		return bst.successor(bst.getRoot(), k).getRecord();
	} // successor method


	@Override
	public Record predecessor(Key k) {
		return bst.predecessor(bst.getRoot(), k).getRecord();
	} // predecessor method


	@Override
	public Record smallest() {
		return bst.smallest(bst.getRoot()).getRecord();
	} // smallest method


	@Override
	public Record largest() {
		return bst.largest(bst.getRoot()).getRecord();
	} // largest method
	
} // class BSTDictionary
