package test33;

public class BSTNode<T> {
    public String  key;
	public LinkedList<Integer> data;
	public int current=0;
	
	public BSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
	public BSTNode(String k, int val) {
		data=new LinkedList<Integer>();
		key = k;
		data.insert(val);
		left = right = null;
	}
	
	public BSTNode(String k, int val, BSTNode<T> l, BSTNode<T> r,int n) {
		data=new LinkedList<Integer>();
		key = k;
		data.insert(val);
		left = l;
		right = r;
	}
	public void insertDoc(int x) {
		data.insert(x);
	}
	public String toString() {
		String s=key+ ": ";
		data.findFirst();
		while(!data.last()) {
			s+=data.retrieve()+", ";
			data.findNext();
		}
		s+=data.retrieve();
		return s;
		
	}
}
