

public class LinkedStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;

    /* Creates a new instance of LinkStack */
    public LinkedStack() {
        top = null;
        size = 0;
    }
    public int size() {
    	return size;
    }
    public boolean empty() {
        return top == null;
    }
    public boolean full() {
        return false;
    }
    public void push(T e) {
        Node<T> tmp = new Node<T>(e);
        tmp.next = top;
        top = tmp;
        size++;
    }
    public T pop() {
        T e = top.data;
        top = top.next;
        size--;
        return e;
    }
    
    public T peek() {
    	return top.data;
    }
}