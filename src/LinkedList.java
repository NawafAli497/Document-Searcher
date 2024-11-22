public class LinkedList<T> implements List<T> {
	private Node<T> head;
	private Node<T> current;
	private int size;
	
	public LinkedList() {
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

	public T retrieve() {
		return current.data;
	}

	public void update(T e) {
		current.data = e;
	}

	public void insert(T e) {
		if (length() == 0) {
			current = head = new Node<T>(e);
		} else {
			Node<T> tmp = current.next;
			current.next = new Node<T>(e);
			current = current.next;
			current.next = tmp;
		}
		size++;
	}

	public void remove() {
		if (current == head) {
			head = head.next;
		} else {
			Node<T> tmp = head;
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
		  
		  for(int j = 0 ; j < i ; i++) {
			  current = current.next;
		  }
	  }
	  public int length() {
		  return size;
	   }
	  
	  public boolean findKey(T key) {
	        Node<T> tmp = head;
	        if(head==null)
	        	return false;
	        while (tmp.next != null) {
	            if (tmp.data.equals(key)) {
	                current = tmp;
	                return true;
	            }
	            tmp = tmp.next;
	        }
	        if (tmp.data.equals(key)) {
	        	current=tmp;
	        return true;
	        }
	        current=tmp;
	        return false;
	    }

}
