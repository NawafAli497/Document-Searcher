
public class ArrayList<T> implements List<T> {
	private int maxsize;
	private int size;
	private int current;
	private T[] nodes;

	public ArrayList() {
		size = 0;
		current = -1;
		nodes = (T[]) new Object[1];
	}
	public boolean last() {
		return current == size - 1;
	}

	public void findFirst() {
		current = 0;
	}

	public void findNext() {
		current++;
	}

	public T retrieve() {
		return nodes[current];
	}

	public void update(T val) {
		nodes[current] = val;
	}

	public void insert(T val) { //dynamic resizing array O(n), the resizing won't effect the efficiency that much (https://sassafras13.github.io/DynamicResizeLists/) for more info
		if (size == nodes.length) {
			T[] tmp = (T[]) new Object[2*nodes.length];
			System.arraycopy(nodes, 0, tmp, 0, size);
			nodes = tmp;
		}
			for (int i = size - 1; i > current; --i) {
				nodes[i + 1] = nodes[i];
			}
		current++;
		nodes[current] = val;
		size++;
	}

	public void remove() {
		for (int i = current + 1; i < size; i++) {
			nodes[i - 1] = nodes[i];
		}
		size--;
		if (size == 0)
			current = -1;
		else if (current == size)
			current = 0;
	}

	public void findIndex(int i) {
		current = i;
	}

	public int length() {
		return size;
	}
}
