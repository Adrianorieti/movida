package pacecorradetti;

import java.util.Set;

abstract class Map<K extends Comparable<K>, V extends Object> {
	
	final class Entry  {
		protected K key;
		protected V value;

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}
		
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


	public abstract void putIfAbsent(K key, V value);

	
	abstract public int length();
	
}
