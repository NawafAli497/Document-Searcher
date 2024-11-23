

public class MyBST<T> {
    private MyBSTNode<T> root, current;
	
	/** Creates a new instance of BST */
	public MyBST() {
		root = current = null;
	}
	public boolean empty() {
		return root == null;
	}
	public boolean full() {
		return false;
	}
	public T retrieve () {
		return current.data;
	}
    public boolean update(String key, T data){
		remove_key(current.key);
		return insert(key, data);
	}
    public void deleteSubtree(){
		if(current == root){
			current = root = null;
		}
		else {
			MyBSTNode<T> p = current;
			find(Relative.Parent);
			if(current.left == p)
				current.left = null;
			else 
				current.right = null;
			current = root;
		}
	}
    public boolean find(Relative rel){
		switch (rel) {
		   case Root:	// Easy case
			current = root;
			return true;
		   case Parent:
			if(current == root)
                return false;
			current = findparent(current, root);
			return true;
		   case LeftChild:
			if(current.left == null)
                return false;
			current = current.left;
			return true;
		   case RightChild:
			if(current.right == null)
                return false;
			current = current.right;
			return true;
		   default:
			return false;
		}
	}
    private MyBSTNode<T> findparent(MyBSTNode<T> p, MyBSTNode<T> t) {
		if(t == null)
			return null;	// empty tree
		if(t.right == null && t.left == null)
			return null;
		else if(t.right == p || t.left == p)
			return t;	// parent is t
		else {
			MyBSTNode<T> q = findparent(p, t.left);
			if (q != null)
				return q;
			else
				return findparent(p, t.right);
		}
	}
    public boolean findkey(String tkey) {
		MyBSTNode<T> p = root, q = root;	
		if(empty())
			return false;	
		while(p != null) {
			q = p;
			if(p.key.equalsIgnoreCase(tkey)) {
				current = p;
				return true;
			}
			else if(tkey.compareToIgnoreCase(p.key)<0)
				p = p.left;
			else
				p = p.right;
		}	
		current = q;
		return false;
	}
    public boolean insert(String k, T val) {
		MyBSTNode<T> p, q = current;
		if(findkey(k)) {
			
			current = q;// findkey() modified current
			
			return false; // key already in the BST
		}
		p = new MyBSTNode<T>(k, val);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			// current is pointing to parent of the new key
			if (k.compareToIgnoreCase(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}
    
    public boolean remove_key(String tkey){
		BooleanWrapper removed = new BooleanWrapper(false);
		MyBSTNode<T> p;
		p = remove_aux(tkey, root, removed);
		current = root = p;
		return removed.getValue();
	}
    private MyBSTNode<T> remove_aux(String key, MyBSTNode<T> p, BooleanWrapper flag) {
		MyBSTNode<T> q, child = null;
		if(p == null)
			return null;
		if(key.compareToIgnoreCase(p.key) <0)
			p.left = remove_aux(key, p.left, flag); //go left
		else if(key.compareToIgnoreCase(p.key) <0)
			p.right = remove_aux(key, p.right, flag); //go right
		else { // key is found
			flag.setValue(true);
			if (p.left != null && p.right != null) { //two children
				q = find_min(p.right);
				p.key = q.key;
				p.data = q.data;
				p.right = remove_aux(q.key, p.right, flag);
			}
            else {
			    if (p.right == null) //one child
				    child = p.left;
			    else if (p.left == null) //one child
				    child = p.right;
			    return child;
		    }
	    }
		return p;
    }
    private MyBSTNode<T> find_min(MyBSTNode<T> p){
		if(p == null)
			return null;
		while(p.left != null){
			p = p.left;
		}
		return p;
	}
	public void showDoc(String key) {
		if(findkey(key))
			System.out.println(current.toString());
		else System.out.println(key+": not found");
		
	}
	
	public String toString() {
	    if (root == null) {
	        return "The tree is empty.";
	    }
	    return traverseAndCollect(root);
	}

	// Helper method to traverse the BST and collect the result
	private String traverseAndCollect(MyBSTNode<T> node) {
	    if (node == null) return "";

	    // Recursively traverse the left subtree
	    String result = traverseAndCollect(node.left);

	    // Append the current key and the toString of its data
	    result +=  "" + node.toString() + "\n";

	    // Recursively traverse the right subtree
	    result += traverseAndCollect(node.right);

	    return result;
	}

	
}
