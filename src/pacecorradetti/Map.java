package pacecorradetti;

import java.util.Set;

abstract class Map<K extends Comparable<K>, V extends Object> {
	
	final class Entry {
		K key;
		V value;
		
		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
	
	
	abstract public void put(K key, V value);

	
	abstract void delete(K key) throws MovidaKeyException;
	
	
	abstract public V search(K key) throws MovidaKeyException;

	
	abstract public void clear();
	
	
	abstract public Set<Entry> entrySet();

	

}
