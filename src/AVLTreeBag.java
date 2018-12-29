
/**
 * A class representing a bag ADT for storing integers using an AVL tree.
 */
public class AVLTreeBag
{
	ATNode root;		// the root of an internal AVL tree

	public AVLTreeBag(){
		root = null;
	}

	/**
	 * Inserts a new element "object" into this bag.
	 * @param element - the new element to be inserted
	 * @postcondition
	 *	A new copy of the element has been added to this bag.
	 **/
	public void add(String key, String value){
		if (root == null)
			root = new ATNode(key, value, null, null);
		else
			root.add(key, value);
	}
        public void addValue(String key, String value){
		if (root == null)
			root = new ATNode(key, value, null, null);
		else
			root.addValue(key, value);
	}
        
        public String getValueByKey(String key){
		if (root == null)
			return "";
		else
			return root.getValueByKey(key);
	}

	/**
	 * Removes one copy of a specified element from this bag.
	 * @param target - the element to be removed from the bag
	 * @postcondition
	 *  If "target" was found in the bag, then one copy of "target" has been removed and the method returns true.
	 *  Otherwise, the bag remains unchanged and the method returns false.
	 **/
	public boolean remove(String key){
		if(root == null)
			return false;
		if(!root.has(key))
			return false;
		root = root.remove(key);
		return true;
	}

	/**
	 * Returns the height of the internal tree.
	 * @return - the height of the internal tree
	 */
	public int getHeight(){
		if(root == null)
			return -1;
		return root.getHeight();
	}

	/**
	 * Returns the balance factor of the root of the internal tree.
	 * @return - the balance factor of the root of the internal tree
	 */
	public int getBalance(){
		if(root == null)
			return 0;
		return root.getBalanceFactor();
	}

	/**
	 * Determines the number of elements in this bag.
	 * @return - the number of elements in this bag
	 **/
	public int size(){
		return ATNode.treeSize(root);
	}
        
        public void print(){
		root.print();
	}
        public void generateHeap(IntBinomialHeap heap){
		root.generateHeap(heap);
	}
}
