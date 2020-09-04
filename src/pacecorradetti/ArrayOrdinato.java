package pacecorradetti;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* Usa ricerca binaria per trovare gli indici O(log n)
 * In caso di ridimensionamento si ha un dimezzamento o duplicazione dello spazio 
 * Spostare gli elementi richiede O(n)
 */


public class ArrayOrdinato<K extends Comparable<K>, V> extends pacecorradetti.Map<K, V>{
	
	private Entry[] array;
	private int lastIndex;
	

	
	@SuppressWarnings("unchecked")		
	/* L'array è creato con classe Entry ed il cast è sicuramento dello stesso tipo. I tipi dei membri di Entry sono stabilite 
	 * nel momento in cui ArrayOrdinato viene instanziato
	 */
	public ArrayOrdinato(int length) {
		array = (Entry[]) Array.newInstance(Entry.class , length);
		lastIndex = -1;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public ArrayOrdinato() {
		array = (Entry[]) Array.newInstance(Entry.class , 1);
		lastIndex = -1;
	}

		
	
	private int locationOf(K key) {
		return locationOf(key, 0, size());
	}
	
	private int locationOf(K key, int low, int up) {
		int pivot = (low + up) / 2;
		if (array[pivot].key.equals(key)) 
		{
			return pivot;	
		}
		else if (up - low <= 1) 
		{
			return (array[pivot].key.compareTo(key) < 0)? pivot + 1: pivot;			
		}
		else if (array[pivot].key.compareTo(key) > 0) 
		{
			return locationOf(key, low, pivot);			
		}
		else 
		{
			return locationOf(key, pivot, up);
		}
	}
	
	@Override
	public void put(K key, V value) {
		
		if (lastIndex == -1) 
		{
			array[0] = new Entry(key, value);
			lastIndex = 0;
			return;
		}
		
		int i = locationOf(key);
	
		if (i <= lastIndex && key.compareTo(array[i].key) == 0)
			array[i] = new Entry(key, value);
		else 
		{
			if (lastIndex + 1 < array.length)
			{
				for (int j = lastIndex; j >= i; j--)
					array[j+1] = array[j];
				
				array[i] = new Entry(key, value);
			}
			else
			{
				@SuppressWarnings("unchecked")
				Entry temp[] = (Entry[]) Array.newInstance(Entry.class , array.length * 2);
				
				for (int j = 0; j < i; j++ ) 
					temp[j] = array[j];
				temp[i] = new Entry(key, value);
				for (int j = i; j <= lastIndex; j++)
					temp[j+1] = array[j];
				
				array = temp;
			}
			lastIndex++;
		}
	}

	@Override
	public void delete(K key) throws MovidaKeyException  {
		
		int i = locationOf(key);
		if (!array[i].key.equals(key)) 
		{
			throw new MovidaKeyException();
		}
		
		if (i > lastIndex) return;					
		if (lastIndex - 1 < array.length / 2)
		{
			@SuppressWarnings("unchecked")
			Entry temp[] = (Entry[]) Array.newInstance(Entry.class , array.length / 2);
			for (int j = 0; j < i; j++) 
			{				
				temp[j] = array[j];
			}
			for (int j = i+1; j <= lastIndex; j++) 
			{
				temp[j-1] = array[j];				
			}
			array = temp;
		}
		else 
		{
			for (int j = i; j < lastIndex; j++)
			{
				array[j] = array[j+1];				
			}
			
			array[lastIndex] = null;
		}	
		lastIndex--;
	}
	
	@Override
	public V search(K key) throws MovidaKeyException {
		int i = locationOf(key);
		if (array[i].key.equals(key))
		{
			return array[i].value;			
		}
		else
		{
			throw new MovidaKeyException();					
		}
	}

	@Override
	public int size() {
		return lastIndex + 1;
	}
	
	public boolean isEmpty() {
		return (lastIndex == -1);
	}

	
		
	@Override
	public void clear() {
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}

	@Override
	public Set<Map<K, V>.Entry> entrySet() {
		Set<Map<K, V>.Entry> temp = new HashSet<Map<K,V>.Entry>();
		for (int i = 0; i < size(); i++)
		{
			temp.add(array[i]);
		}
		return temp;
	}
	
	
	public ArrayList<V> valueList() {
		ArrayList<V> temp = new ArrayList<V>();
		for (int i = 0; i < size(); i++)
		{
			temp.add(array[i].getValue());
		}
		return temp;
	}
 	
	
	@Override
	public void putIfAbsent(K key, V value) {
		if (lastIndex == -1) 
		{
			array[0] = new Entry(key, value);
			lastIndex = 0;
			return;
		}
		
		int i = locationOf(key);
		if (i <= lastIndex && array[i].getKey().equals(key))
		{
			return;
		}
		else
		{
			if (lastIndex + 1 < array.length)
			{
				for (int j = lastIndex; j >= i; j--)
					array[j+1] = array[j];
				
				array[i] = new Entry(key, value);
			}
			else
			{
				@SuppressWarnings("unchecked")
				Entry temp[] = (Entry[]) Array.newInstance(Entry.class , array.length * 2);
				
				for (int j = 0; j < i; j++ ) 
					temp[j] = array[j];
				temp[i] = new Entry(key, value);
				for (int j = i; j <= lastIndex; j++)
					temp[j+1] = array[j];
				
				array = temp;
			}
			lastIndex++;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArrayOrdinato [array=");
		builder.append(Arrays.toString(array));
		builder.append("]");
		return builder.toString();
	}

	
	
}	
