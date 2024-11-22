
public class MyBSTNode<T> {
	public String key;
	public T data;
	public MyBSTNode<T> left, right;
	
	/** Creates a new instance of BSTNode */
	public MyBSTNode(String k, T val) {
		key = k;
		data = val;
		left = right = null;
	}
	
	public MyBSTNode(String k, T val, MyBSTNode<T> l, MyBSTNode<T> r) {
		key = k;
		data = val;
		left = l;
		right = r;
	}
}
