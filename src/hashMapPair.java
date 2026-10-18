public class hashMapPair<K, V> {

    public K key;
    public V value;
    public hashMapPair<K, V> next;

    public hashMapPair(K key, V value) {
        this.key = key;
      
        this.value = value;
        this.next = null;
    }

}