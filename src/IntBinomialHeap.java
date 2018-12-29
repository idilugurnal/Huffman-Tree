
import java.util.NoSuchElementException;

/**
 * A class representing a priority queue for storing integers using a binomial min-heap.
 *
 * Invariants:
 * 1. The internal linked list is either empty or stores one or more binomial trees in the order of
 *    increasing degrees.
 * 2. "manyItems" is the number of all integers stored in the queue.
 */
public class IntBinomialHeap {
	Node<IntBinomialTree> head;			// a linked list storing binomial trees (a forest)
	int manyItems;						// # of data items in the forest

	/**
	 * The constructor.
	 */
	public IntBinomialHeap(){
		head = null;
		manyItems = 0;
	}

	/**
	 * Empties the queue.
	 */
	public void clear(){
		head = null;
		manyItems = 0;
	}

    /**
     * Returns one of the minimum items in the queue.
     * @return a minimum item in the queue.
     * @precondition
     * 	The queue is not empty.
     */
	public int peek(){
		if (head == null)
			throw new NoSuchElementException("Empty heap");
		return findMinTree().getKey();
	}

	/**
	 * Adds a new key and value "_key" and "_value" to the queue.
	 * @param _key  - a new key of element to be added to the queue
     * @param _value  - a new value of element to be added to the queue
	 */
	public void add(int _key, String _value){
		IntBinomialHeap queue = new IntBinomialHeap();
		IntBinomialTree tree = new IntBinomialTree(_key, _value);
		queue.appendTree (tree); 
		merge(queue);
	}

	/**
	 * Removes a minimum item from the queue.
	 * @return a minimum item in the queue
     * @precondition
     * 	The queue is not empty.
	 */
	public int remove(){
		if (head == null)
			throw new NoSuchElementException("Empty heap");
		IntBinomialTree minTree = removeMinTree();
		merge(minTree.split());
		return minTree.getKey();
	}

	/**
	 * Merges a given queue "addend" with this queue.
	 * @param addend - a queue to be merged with this queue.
	 * @precondition
	 * 	The argument "addend" is not null.
	 */
	public void merge(IntBinomialHeap addend){
		// To be completed by students
		// ...
        
        //manyItems=manyItems+addend.manyItems;
        
        Node<IntBinomialTree> cur=addend.head;
        Node<IntBinomialTree> curHead=head;
        head=null;
        manyItems=0; 
        while(cur!=null || curHead!=null){
            Node<IntBinomialTree> temp=head;
            while(temp!=null && temp.getLink()!=null){
                temp=temp.getLink(); 
            }
            IntBinomialTree tree;
            if(curHead==null){
                
                tree=cur.getData(); 
                cur=cur.getLink();
            }
            else if(cur==null){
                tree=curHead.getData();
                curHead=curHead.getLink();
            }
            else{
                if(curHead.getData().getDegree()<cur.getData().getDegree()){
                    tree=curHead.getData();
                    curHead=curHead.getLink();
                }
                else if(curHead.getData().getDegree()==cur.getData().getDegree()){
                    tree= curHead.getData();
                    tree.merge(cur.getData());
                    cur=cur.getLink();
                    curHead=curHead.getLink();
                }
                else{
                    tree=cur.getData();
                    cur=cur.getLink();
                }
            }
            
            if(temp==null){
                appendTree(tree);
            }
            else{
                if(temp.getData().getDegree()==tree.getDegree()){
                    temp.getData().merge(tree);
                    manyItems=manyItems+tree.size(); 
                }
                else{
                    appendTree(tree); 
                }
            }
        }
        
	}

	/**
	 * Appends a given binomial tree "tree" to the forest.
	 * @param tree - a binomial tree to be added to the forest
	 * @precondition
	 * 	The degree of the given tree is larger than any of the trees in the forest.
	 *  please refer to the "supplement.pdf": figure 11.10 (a)
	 *
	 */
	void appendTree(IntBinomialTree tree){
        
        manyItems=manyItems+tree.size(); 
		if(head==null){
            head= new Node<IntBinomialTree>(tree, null);
        }
        else{
            Node<IntBinomialTree> cur=head;
            while(cur.getLink()!=null){
                cur=cur.getLink(); 
            }
            cur.addNodeAfter(tree); 
        }
        
        
	}

	/**
	 * Removes one of the minimum trees from the forest.
	 * (A minimum tree means a tree containing a minimum element.)
	 * @precondition
	 * 	This forest is not empty.
	 */
	IntBinomialTree removeMinTree(){
		// To be completed by students
		// ...
        Node<IntBinomialTree> cur=head;
        Node<IntBinomialTree> prev=null;
        IntBinomialTree tree=head.getData();
       
        while(cur.getLink()!=null){ 
            if(tree.getKey()> cur.getLink().getData().getKey()){
                tree=cur.getLink().getData() ;
                prev=cur;
            }
            cur=cur.getLink(); 
        }
        
        if(prev==null){
            head=head.getLink();
            manyItems=manyItems-tree.size();
            return tree;
        }
        else{
            prev.setLink(prev.getLink().getLink());
            manyItems=manyItems-tree.size();
            return tree; 
        }
         
	}

	/**
	 * Finds and returns one of the minimum trees from the heap, if any.
	 * (A minimum tree means a tree containing a minimum element.)
	 * @return - one of the minimum trees from the forest, if any.
	 */
	IntBinomialTree findMinTree(){
		
        Node<IntBinomialTree> cur=head;
        IntBinomialTree tree=head.getData();
       
        while(cur!=null){ 
            if(tree.getKey()>cur.getData().getKey()) 
                tree=cur.getData() ;
            cur=cur.getLink(); 
        }
        return tree;     
	} 

	/**
	 * Prints the content of the queue (for debugging).
	 * You are free to modify or remove this method.
	 */
    public void print(){
    	System.out.printf("[IntBinomialTree with %d items]\n", manyItems);
        Node<IntBinomialTree> cur = head;
        while(cur != null){
        	IntBinomialTree tree = cur.getData();
        	tree.print(0);
        	cur = cur.getLink();
        }
    }
}
