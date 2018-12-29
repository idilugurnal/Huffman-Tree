
/**
 * A class representing a node in an AVL tree for storing integers.
 *
 */
 public class ATNode
{
	String key;			// data to be stored in the node
        String value;
        int occurence;
	ATNode left;		// a reference to the left child
	ATNode right;	// a reference to the right child
	int height;			// the height of this tree (The height of a leaf node is 0.)

	/**
	 * The constructor.
	 * @param _key - the initial value of "key"
	 * @param _left - the initial value of "left"
	 * @param _right - the initial value of "right"
	 */
	public ATNode(String _key, String _value, ATNode _left, ATNode _right){
		key = _key;
        value = _value;
		left = _left;
		right = _right;
		height = 0;		// assuming that this node is a leaf node initially
                occurence=1;
	}

	/**
	 * Indicates if this node is a leaf node.
	 * @return - true if this node is a leaf node, and false otherwise.
	 */
	public boolean isLeaf(){
		return (left == null) && (right == null);
	}

	public String getKey(){return key;}
    
    public String getValue(){return value;}

	public int getHeight(){return height;}

	/**
	 * Updates the height of this node based on the heights of the children.
	 * @precondition
	 *  The heigkeyata of the children are up-to-date and valid.
	 */
	void updateHeight()
	{
		int leftHeight = -1;
		if(left != null) leftHeight = left.getHeight();
		int rightHeight = -1;
		if(right != null) rightHeight = right.getHeight();
		height = 1 + Math.max(leftHeight, rightHeight);
	}

	/**
	 * Calculates the balance factor of this node based on the heights of the children.
	 * @return - the balance factor of this node
	 * @precondition
	 *  The height data of the children are up-to-date and valid.
	 */
	int getBalanceFactor()
	{
		int leftHeight = -1;
		if(left != null) leftHeight = left.getHeight();
		int rightHeight = -1;
		if(right != null) rightHeight = right.getHeight();
		return leftHeight - rightHeight;
	}

    /**
     * Returns the data of the leftmost node of the tree starting from this node.
     * @return - the data from the leftmost node that can be reached from this node.
     */
	public String getLeftmostData( )
	{
		if (left == null)
			return key;
		else
			return left.getLeftmostData( );
	}

    /**
     * Returns the data of the rightmost node of the tree starting from this node.
     * @return - the data from the rightmost node that can be reached from this node.
     */
	public String getRightmostData( )
	{
		if (right == null)
			return key;
		else
			return right.getRightmostData( );
	}

    /**
     * Removes the leftmost node of the tree starting from this node.
     * @return - the root node of the tree after the leftmost node is removed.
     */
	public ATNode removeLeftmost( )
	{
		if (left == null)
			return right;
		else
		{
			left = left.removeLeftmost( );
			balance();
			return this;
		}
	}

    /**
     * Removes the rightmost node of the tree starting from this node.
     * @return - the root node of the tree after the rightmost node is removed.
     */
	public ATNode removeRightmost( )
	{
		if (right == null)
			return left;
		else{
			right = right.removeRightmost( );
			balance();
			return this;
		}
	}

	/**
	 * Adds a new item "object" to the tree starting from this node.
	 * @param object - a new item to be added
	 * @precondition
	 * 	The tree starting from this node is an AVL tree.
	 * @postcondition
	 * 	The tree starting from this node is an AVL tree.
	 */
	public void add(String object, String value)
	{
                if(compareWords(object, key)==0){
                    occurence++;
                }
                else if (compareWords(object, key) == 1) {
			if(left == null)
				left = new ATNode(object, value, null, null);
			else
				left.add(object, value);
		} else {
			if(right == null)
				right = new ATNode(object, value, null, null);
			else
				right.add(object, value);
		}
		balance();		// in any case
	}
        public void addValue(String object, String value)
	{
                if(compareWords(object, key)==0){
                    this.value=value;
                }
                else if (compareWords(object, key) == 1) {
			if(left == null)
				left = new ATNode(object, value, null, null);
			else
				left.addValue(object, value);
		} else {
			if(right == null)
				right = new ATNode(object, value, null, null);
			else
				right.addValue(object, value);
		}
		balance();		// in any case
	}
        
        public String getValueByKey(String object)
	{
                if(compareWords(object, key)==0){
                    return value;
                }
                else if (compareWords(object, key) == 1) {
			if(left == null)
				return "";
			else
				return left.getValueByKey(object);
		} 
                else {
			if(right == null)
				return "";
			else
				return right.getValueByKey(object);
		}		// in any case
	}

	/**
	 * Indicates if a given item "object" is in the tree starting from this node.
	 * @param object - a target item to search for
	 * @return - true if the given item is in the tree, and false otherwise.
	 */
	public boolean has(String object)
	{
		if (compareWords(object, key) == 0)
			return true;
		else if (compareWords(object, key) == 1 && left != null) 
			return left.has(object);
		else if (compareWords(object, key) == -1 && right != null) 
			return right.has(object);
        else return false; 
	}

	/**
	 * Removes a given item "object" from the tree starting from this node.
	 * @param object - an item to be removed from the tree.
	 * @precondition
	 *	The given item "object" is already in the tree.
	 */
	public ATNode remove(String object)
	{
		if(!has(object))
			throw new IllegalArgumentException();

		// To be completed by students
		// ...
        return null;
	}

	/**
	 * Restore the balance condition of an AVL tree after add or remove operation.
	 */
	void balance()
	{
		updateHeight();
		if (getBalanceFactor() > 1){
			if (left.getBalanceFactor() > 0)
				doLLRotation();
			else
				doLRRotation();
		}
		else if (getBalanceFactor() < -1) {
			if (right.getBalanceFactor() < 0)
				doRRRotation();
			else
				doRLRotation();
		}
	}

	/**
	 * Performs an LL-rotation.
	 */
	void doLLRotation()
	{
		ATNode temp= new ATNode(key, value, left.right, right);
        temp.updateHeight();
        key=left.key;
        value=left.value;
        left=left.left;
        right=temp; 
        updateHeight(); 
	}

	/**
	 * Performs an RR-rotation.
	 */
	void doRRRotation()
	{
		ATNode temp= new ATNode(key, value, left, right.left);
        temp.updateHeight();
        key=right.key;
        value=right.value;
        right=right.right;
        left=temp;  
        updateHeight(); 
        
	}

	/**
	 * Performs an LR-rotation.
	 */
	void doLRRotation()
	{
		left.doRRRotation();
        doLLRotation();
	}

	/**
	 * Performs an RL-rotation.
	 */
	void doRLRotation()
	{
		right.doLLRotation();
        doRRRotation();
	}
    /**
    * It compares two words in dictionary order. If the first word precedes the second word in alphabetical order return 1;
    * Unless, return -1;
    * It is used to construct the tree in dictionary order.
    * NOTE1 : If even one chracter of arguments is not alphabet, it throws an IllegalArgumentException. 
    * That means two parameters,word1, word2 should not contain numeric character, symbol, and space.
    * NOTE2: If two parameters are same string, it returns 0;
    * NOTE3: It converts every characters of parameters to the lowercase. 
    *       So we consider the uppercase and the lowercase are identical. 
    */

   int compareWords(String word1, String word2)
	{
		if(word1.length() != 1 || word2.length() != 1)
		{
			throw new IllegalArgumentException("word should be length 1");
		}
		 
		int ascii1 = (int) word1.toCharArray()[0];
		int ascii2 = (int) word2.toCharArray()[0];
		if(ascii1 == ascii2)
		{
			return 0;
		}
		else if (ascii1 > ascii2)
		{
			return -1;
		}
		else
		{
			return 1;
		}
	}


    
	/**
	 * Calculates the number of nodes in the tree starting from a given node "root".
	 * @param root - the root of the tree
	 * @return - the number of nodes in the tree starting from the given node "root"
	 */
	public static int treeSize(ATNode root)
	{
		if (root == null)
			return 0;
		else
			return 1 + treeSize(root.left) + treeSize(root.right);
	}
        
        public void print(){
            if(left!=null){
                left.print();
            }
            System.out.println(key +": "+occurence);
            if(right!=null){
                right.print();
            }
            
        }
         public void generateHeap(IntBinomialHeap heap){
            if(left!=null){
                left.generateHeap(heap);
            }
            heap.add(occurence, key);
            if(right!=null){
                right.generateHeap(heap);
            }
            
        }
}
