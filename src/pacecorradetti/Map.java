package pacecorradetti;

import java.util.ArrayList;
import java.util.Set;


/**
 * Classe ereditata dalle classi dizionario
 */
abstract class Map<K extends Comparable<K>, V extends Object> {
	
	/**Definisce una classe con due valori : @key la chiave assegnata all'elemento per la ricerca  @value l'elemento stesso 
	 * 
	 * @author pacecorradetti
	 *
	 */
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
	
	/**
	 * Inserisce un dato nella struttura dati sovrascrivendolo se già presente
	 * @param key la stringa con la quale effettuare la ricerca
	 * @param value l'oggetto che verrà inserito
	 */
	abstract public void put(K key, V value);

	/**
	 * Cancella l'elemento associato alla chiave <code>K</code>
	 * @param key
	 */
	abstract void delete(K key) throws MovidaKeyException;
	
	/**
	 * Effettua una ricerca dell'elemento associato alla chiave <code>K</code>
	 * @param key
	 * @return <V> Object
	 */
	abstract public V search(K key) throws MovidaKeyException;

	/**
	 * Svuota la struttura dati
	 */
	abstract public void clear();
	
	/**
	 * Ritorna un insieme delle coppie Key-Value
	 * @return Set di Entry
	 */
	
	abstract public Set<Entry> entrySet();
	
	/**
	 * Ritorna una lista contenente i valori memorizzati
	 * @return ArrayList di <V> Object
	 */
	
	abstract public ArrayList<V> valueList();

	/**
	 * Inserisce un elemento nella struttura dati se non già presente
	 * @param key la stringa per la ricerca nella struttura
	 * @param value l'elemento che và inserito
	 */

	public abstract void putIfAbsent(K key, V value);

	/**
	 * Ritorna il numero di elementi nella struttura dati
	 * @return int 
	 */
	
	abstract public int size();
	
}
