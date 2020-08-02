package pacecorradetti;


/* Usa ricerca binaria per trovare gli indici O(log n)
 * In caso di ridimensionamento si ha un dimezzamento o duplicazione dello spazio 
 * Spostare gli elementi richiede O(n)
 */


public class ArrayOrdinato {
	
	Pair array[];
	int lastIndex;
	

	
	ArrayOrdinato(int length) {
		array = new Pair[length];
		lastIndex = -1;
	}
	
	ArrayOrdinato() {
		array = new Pair[1];
		lastIndex = -1;
	}

	protected class Pair {
		Object elem;
		Comparable key;
		
		Pair(Object elem, Comparable key) {
			this.elem = elem;
			this.key = key;
		}
	}
		
	
	private int locationOf(Comparable key) {
		return locationOf(key, 0, lastIndex);
	}
	
	private int locationOf(Comparable key, int low, int up) {
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
	
	public void insert(Object elem, Comparable key) {
		
		//TODO implement binary search
		/*
		 * int i = 0; while (i <= lastIndex && key.compareTo(array[i].key) > 0) i++;
		 */
		if (lastIndex == -1) 
		{
			array[0] = new Pair(elem, key);
			lastIndex = 0;
			return;
		}
		
		int i = locationOf(key);
	
		if (i <= lastIndex && key.compareTo(array[i].key) == 0)
			array[i] = new Pair(elem, key);
		else 
		{
			if (lastIndex + 1 < array.length)
			{
				for (int j = lastIndex; j >= i; j--)
					array[j+1] = array[j];
				
				array[i] = new Pair(elem, key);
			}
			else
			{
				Pair temp[] = new Pair[array.length * 2];
				
				for (int j = 0; j < i; j++ ) 
					temp[j] = array[j];
				temp[i] = new Pair(elem, key);
				for (int j = i; j <= lastIndex; j++)
					temp[j+1] = array[j];
				
				array = temp;
			}
			lastIndex++;
		}
	}

	
	public void delete(Comparable key) throws MovidaKeyException  {
		/*
		 * int i = 0;
		 * 
		 * //TODO implement binary search while (i <= lastIndex &&
		 * !key.equals(array[i].key)) i++;
		 */
		
		int i = locationOf(key);
		if (!array[i].key.equals(key)) throw new MovidaKeyException(); //TODO handle exception if key is not found
		
		if (i > lastIndex) return;					
		if (lastIndex - 1 < array.length / 2)
		{
			Pair temp[] = new Pair[array.length / 2];
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
	
	public Object search(Comparable key) throws MovidaKeyException {	//TODO handle exception if key is not found
		int i = locationOf(key);
		if (array[i].key.equals(key))
			return array[i].elem;
		else
			throw new MovidaKeyException();		
	}

	public int length() {
		return array.length;
	}
	

}	
