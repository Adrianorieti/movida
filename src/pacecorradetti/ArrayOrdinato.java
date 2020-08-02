package pacecorradetti;


/* Usa ricerca binaria per trovare gli indici O(log n)
 * In caso di ridimensionamento si ha un dimezzamento o duplicazione dello spazio 
 * Spostare gli elementi richiede O(n)
 */


public class ArrayOrdinato<K extends Comparable<K>, E extends Object> {
	
	private Pair array[];
	int lastIndex;
	
	protected class Pair {
		K key;
		E elem;
		
		Pair(K key, E elem) {
			this.key = key;
			this.elem = elem;
		}
	}

	
	public ArrayOrdinato(int length) {
		array =  (Pair[]) new Object[length];
		lastIndex = -1;
	}
	
	public ArrayOrdinato() {
		array = (Pair[]) new Object[1];
		lastIndex = -1;
	}

		
	
	private int locationOf(K key) {
		return locationOf(key, 0, lastIndex);
	}
	
	private int locationOf(K key, int low, int up) {
		int pivot = (low + up) / 2;
		if (array[pivot].key.equals(key)) 
			return pivot;
		else if (up - low <= 1)
			return (array[pivot].key.compareTo(key) < 0)? pivot + 1: pivot;
		else if (array[pivot].key.compareTo(key) > 0)
			return locationOf(key, low, pivot);
		else 
			return locationOf(key, pivot, up);
	}
	
	public void insert(K key, E elem) {
		
		if (lastIndex == -1) 
		{
			array[0] = new Pair(key, elem);
			lastIndex = 0;
			return;
		}
		
		int i = locationOf(key);
	
		if (i <= lastIndex && key.compareTo(array[i].key) == 0)
			array[i] = new Pair(key, elem);
		else 
		{
			if (lastIndex + 1 < array.length)
			{
				for (int j = lastIndex; j >= i; j--)
					array[j+1] = array[j];
				
				array[i] = new Pair(key, elem);
			}
			else
			{
				Pair temp[] = (Pair[]) new Object[array.length * 2];
				
				for (int j = 0; j < i; j++ ) 
					temp[j] = array[j];
				temp[i] = new Pair(key, elem);
				for (int j = i; j <= lastIndex; j++)
					temp[j+1] = array[j];
				
				array = temp;
			}
			lastIndex++;
		}
	}

	public void delete(K key) throws MovidaKeyException  {
		
		int i = locationOf(key);
		if (!array[i].key.equals(key)) throw new MovidaKeyException();
		
		if (i > lastIndex) return;					
		if (lastIndex - 1 < array.length / 2)
		{
			Pair temp[] = (Pair[]) new Object[array.length / 2];
			for (int j = 0; j < i; j++) 
				temp[j] = array[j];
			for (int j = i+1; j <= lastIndex; j++) 
				temp[j-1] = array[j];
			
			array = temp;

		}
		else 
		{
			for (int j = i; j < lastIndex; j++)
				array[j] = array[j+1];
			
			array[lastIndex] = null;
		}
		
		lastIndex--;
	}
	
	public E search(K key) throws MovidaKeyException {
		int i = locationOf(key);
		if (array[i].key.equals(key))
			return array[i].elem;
		else
			throw new MovidaKeyException();		
	}

	public int length() {
		return array.length;
	}
	
	public boolean isEmpty() {
		return (lastIndex == -1);
	}
}	
