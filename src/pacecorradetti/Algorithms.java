package pacecorradetti;

import java.util.concurrent.ThreadLocalRandom;

public class Algorithms {
	public static <K extends Comparable<K>> void quickSort (K array[]) {
		quickSort(array, 0, array.length-1);
	}
	
	public static <K extends Comparable<K>> void quickSort (K array[], int low, int high) {
		if (low >= high) return;
		int m = partition(array, low, high);
		quickSort(array, low, m-1);
		quickSort(array, m+1, high);
	}
	
	private static <K extends Comparable<K>> int partition(K array[], int low, int high) {
		int i = low, j = high + 1;
		int m = ThreadLocalRandom.current().nextInt(i, j);
		K pivot = array[m];
		
		swap(array, low, m);
		
		while (i < j)
		{
			do 
			{
				i++;
			} while (array[i].compareTo(pivot) < 0 && i < high );
			do 
			{
				j--;
			} while (array[j].compareTo(pivot) > 0 && j > low);
			if (i < j)
			{
				swap(array, i, j);
			}
			
		}
		
		swap(array, low, j);
		
		return j;
	}

	private static final <T> void  swap (T array[], int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
