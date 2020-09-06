package pacecorradetti;

import java.util.ArrayList;
import java.util.Set;


/**
 * Definisce una classe astratta utilizzata dalle strutture dati
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
	 * Cancella l'elemento corrispondete al
	 * @param key
	 */
	abstract void delete(K key) throws MovidaKeyException;
	
	/**
	 * Effettua una ricerca dell'elemento corrispondente al
	 * @param key
	 * @return <V> Object
	 */
	abstract public V search(K key) throws MovidaKeyException;

	/**
	 * Imposta a null ogni area di memoria della struttura dati
	 */
	abstract public void clear();
	
	/**
	 * Raccoglie in un insieme tutti gli Entry contenuti in una struttura dati
	 * @return Set di Entry
	 */
	
	abstract public Set<Entry> entrySet();
	
	/**
	 * Raccoglie in un ArrayList tutti gli elementi di una struttura dati
	 * @return ArrayList di <V> Object
	 */
	
	abstract public ArrayList<V> valueList();

	/**
	 * Inserisce un elemento nella struttura dati solamente se non già presente
	 * @param key la stringa per la ricerca nella struttura
	 * @param value l'elemento che và inserito
	 */

	public abstract void putIfAbsent(K key, V value);

	/**
	 * Ritorna il size della struttura dati
	 * @return int 
	 */
	
	abstract public int size();
	
}
