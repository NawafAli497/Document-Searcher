

public class MyNode<T,V> {
	public T data1;
	public V data2;
	public MyNode<T,V> next;
	
    public MyNode(T data1,V data2) {
        this.data1 = data1;
        this.data2 = data2;
        this.next = null;
        
    }public MyNode(T data1) {
        this.data1 = data1;
        this.data2 = null;
        this.next = null;
    }
    public MyNode() {
        this.data1 = null;
        this.data2 = null;
        this.next = null;
    }
    public MyNode(T data1,V data2, MyNode<T,V> next) {
    	this.data1 = data1;
        this.data2 = data2;
        this.next = next;
    }
}