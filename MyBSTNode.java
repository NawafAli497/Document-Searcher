

public class MyBSTNode<T> {
    public String  key;
	//public LinkedList<Integer> data;
	public BST<Integer> data;
	
	
	
	//insert word from doc
	//
	
	public MyBSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
	public MyBSTNode(String k, int val) {
		//data=new LinkedList<Integer>();
		data=new BST<Integer>();
		key = k;
		data.insert(val,1);
		left = right = null;
	}
	
	public MyBSTNode(String k, int val, MyBSTNode<T> l, MyBSTNode<T> r,int n) {
		//data=new LinkedList<Integer>();
		data=new BST<Integer>();
		key = k;
		data.insert(val,1);
		left = l;
		right = r;
	}
	public void insertDoc(int x) {
		if(data.findkey(x)) {
			int t=data.retrieve()+1;
		data.update(x,t );}
		
		data.insert(x,1);
	}
	@Override
	public String toString() {
	    String result = key + ": ";
	    
	    if (data.empty()) {
	        result += "No data";
	    } else {
	        result += traverseAndCollect(data.root);
	    }
	    
	    return result;
	}

	// Helper method to traverse the BST and collect data as a string
	private String traverseAndCollect(BSTNode<Integer> node) {
	    if (node == null) return "";

	    
	    String left = traverseAndCollect(node.left);

	    
	    String current = "(" + node.key + ", " + node.data + ")";

	  
	    String right = traverseAndCollect(node.right);

	   
	    String result = left;
	    if (!left.isEmpty()) {
	        result += ", ";
	    }
	    result += current;
	    if (!right.isEmpty()) {
	        result += ", " + right;
	    }

	    return result;
	}

}
