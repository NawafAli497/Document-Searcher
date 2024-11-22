public class hashMapPair<K, V> {

    public K key;
    public LinkedList<V> value;
    public hashMapPair<K, V> next;
    private V lastInsertedValue;

    public hashMapPair(K key, V value) {
        this.key = key;
        this.value = new LinkedList<>();
        this.value.insert(value);
        this.next = null;
    }
    
    public void setLastInsertedValue(V value) {
    	lastInsertedValue = value;
    }
    public V getLastInsertedValue() {
    	return lastInsertedValue;
    }

}