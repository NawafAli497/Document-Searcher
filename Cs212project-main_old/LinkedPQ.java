

public class LinkedPQ<T> {
    private int size;
    private PQNode<T> head;

    /* tail is of no use here. */
    public LinkedPQ() {
        head = null;
        size = 0;
    }
    public int length (){
        return size;
    }
    public boolean full () {
        return false;
    }
    public void enqueue(T e, int pty) {
        PQNode<T> tmp = new PQNode<T>(e, pty);
        if((size == 0) || (pty > head.priority)) {
            tmp.next = head;
            head = tmp;
        }
        else {
            PQNode<T> p = head;
            PQNode<T> q = null;
            while((p != null) && (pty <= p.priority)) {
                q = p;
                p = p.next;
            }
            tmp.next = p;
            q.next = tmp;
        }
        size++;
    }
    public PQElement<T> serve(){
        PQNode<T> node = head;
        PQElement<T> pqe=new PQElement<T>(node.data, node.priority);
        head = head.next;
        size--;
        return pqe;
    }
 // Method to get all elements with their priorities for user to print
    public ArrayList<PQElement<T>> getAllElements() {
    	ArrayList<PQElement<T>> elements = new ArrayList<PQElement<T>>(length());
        PQNode<T> temp = head;

        // Traverse and add each element to the list
        while (temp != null) {
            elements.insert(new PQElement<T>(temp.data, temp.priority));
            temp = temp.next;
        }
        return elements;
    }
}
