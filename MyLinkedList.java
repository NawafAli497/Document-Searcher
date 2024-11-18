public class MyLinkedList<T, V>  {
    private MyNode<T, V> head;
    private MyNode<T, V> current;
    private int size;

    public MyLinkedList() {
        head = current = null;
        size = 0;
    }

    public boolean last() {
        return current.next == null;
    }

    public void findFirst() {
        current = head;
    }

    public void findNext() {
        current = current.next;
    }

    public T retrieveFirst() {
        return current.data1;
    }

    public V retrieveSecond() {
        return current.data2;
    }

    public void updateFirst(T e) {
        current.data1 = e;
    }

    public void updateSecond(V e) {
        current.data2 = e;
    }

    public void insert(T e, V b) {
        if (length() == 0) {
            current = head = new MyNode<T, V>(e, b);
        } 
        
        else {
            MyNode<T, V> tmp = current.next;
            current.next = new MyNode<T, V>(e, b);
            current = current.next;
            current.next = tmp;
        }
        size++;
    }
    public void insert(T e ) {
        if (length() == 0) {
            current = head = new MyNode<T, V>(e);
        } else {
            MyNode<T, V> tmp = current.next;
            current.next = new MyNode<T, V>(e);
            current = current.next;
            current.next = tmp;
        }
        size++;
    }

    public void remove() {
        if (current == head) {
            head = head.next;
        } else {
            MyNode<T, V> tmp = head;
            while (tmp.next != current) {
                tmp = tmp.next;
            }
            tmp.next = current.next;
        }
        if (current.next == null) {
            current = head;
        } else {
            current = current.next;
        }
        size--;
    }

    public void findIndex(int i) {
        current = head;

        for (int j = 0; j < i; j++) {
            current = current.next;
        }
    }
    
    public boolean findKey(T key) {
        MyNode<T, V> tmp = head;
        if(head==null)
        	return false;
        while (tmp.next != null) {
            if (tmp.data1.equals(key)) {
                current = tmp;
                return true;
            }
            tmp = tmp.next;
        }
        if (tmp.data1.equals(key)) {
        	current=tmp;
        return true;
        }
        current=tmp;
        return false;
    }

    

    public int length() {
        return size;
    }
}
