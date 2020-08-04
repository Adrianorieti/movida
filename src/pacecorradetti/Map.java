package pacecorradetti;

abstract class Map<K extends Comparable<K>, E extends Object> {
	abstract public void insert(K key, E elem);

	
	abstract void delete(K key) throws MovidaKeyException;
	
	
	abstract public E search(K key) throws MovidaKeyException;

	
	abstract public void clear();

}
