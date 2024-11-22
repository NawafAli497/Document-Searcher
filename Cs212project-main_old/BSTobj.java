
public class BSTobj {
	 
		//public LinkedList<Integer> data;
		public BST<Integer> data;
		/** Creates a new instance of BSTNode */
		public BSTobj( int val) {
			//data=new LinkedList<Integer>();
			data=new BST<Integer>();
			
			data.insert(val,1);
			
		}
		
		
		public void insertDoc(int x) {
			if(data.findkey(x)) {
				int t=data.retrieve()+1;
			data.update(t);}
			
			data.insert(x,1);
		}
		@Override
		public String toString() {
		    String result = ": ";
		    
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
