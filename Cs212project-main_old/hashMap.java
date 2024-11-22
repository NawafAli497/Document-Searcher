public class hashMap<K, V> {
	private final double loadFactor = 0.75;
	private hashMapPair<K, V>[] buckets;
	private int capacity;
	private int numberOfInserts;

	public hashMap(Integer capacity) {
		this.capacity = (int) (capacity/loadFactor);
		numberOfInserts = 0;
		buckets = new hashMapPair[this.capacity];
	}

	private Integer hash(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	public void put(K key, V value) {
		int hash = hash(key);
		hashMapPair<K, V> entry = new hashMapPair<K, V>(key, value);
		hashMapPair<K, V> temp = buckets[hash];

		if (temp == null) {
			entry.setLastInsertedValue(value);
			buckets[hash] = entry;
		} else {
			while (temp != null) {
				if (temp.key.equals(key)) {
					temp.value.insert(value);
					temp.setLastInsertedValue(value);
					return;
				}
				if (temp.next != null) {
					temp = temp.next;
				} else {
					temp.next = entry;
					entry.setLastInsertedValue(value);
					return;
				}
			}
		}
	}

	public LinkedList<V> get(K key) {
		int hash = hash(key);
		hashMapPair<K, V> temp = buckets[hash];
		while (temp != null) {
			if (temp.key.equals(key)) {
				return temp.value;
			}
			temp = temp.next;
		}
		return null;
	}

	public void remove(K key) {
		int hash = hash(key);
		hashMapPair<K, V> temp = buckets[hash];
		hashMapPair<K, V> prev = null;
		while (temp != null) {
			if (temp.key.equals(key)) {
				if (prev != null) {
					prev.next = temp.next;
				} else {
					prev = temp.next;
					buckets[hash] = prev;
				}
				return;
			}
			prev = temp;
			temp = temp.next;
		}
	}

	public V lastInsertedValue(K key) {
		int hash = hash(key);
		hashMapPair<K, V> temp = buckets[hash];
		while (temp != null) {
			if (temp.key.equals(key)) {
				return temp.getLastInsertedValue();
			}
			temp = temp.next;
		}
		return null;
	}

	public void display() {
		for (int i = 0; i < capacity; i++) {
			hashMapPair<K, V> temp = buckets[i];
			while (temp != null) {
				System.out.print(temp.key + " ");

				temp.value.findFirst();
				System.out.print("[");
				for (int j = 0; j < temp.value.length(); j++) {
					if (j > 0)
						System.out.print("," + temp.value.retrieve());
					else
						System.out.print(temp.value.retrieve());
					temp.value.findNext();
				}
				System.out.println("]");
				temp = temp.next;
			}
		}
	}

	public void printMapStructure() {
		for (int i = 0; i < capacity; i++) {
			if(buckets[i] == null)
				continue;
			hashMapPair<K, V> temp = buckets[i];
			while (temp != null) {
				System.out.print(temp.key + " ");
				temp = temp.next;
			}
			System.out.println();
		}
	}
}
