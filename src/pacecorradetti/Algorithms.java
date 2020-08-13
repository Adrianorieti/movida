package pacecorradetti;

import java.util.Comparator;
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


	
	public static <K extends Comparable<K>> void quickSort (K array[], Comparator<K> c) {
		quickSort(array, 0, array.length-1, c);
	}
	
	
	private static <K extends Comparable<K>> void quickSort (K array[], int low, int high, Comparator<K> c) {
		if (low >= high) return;
		int m = partition(array, low, high, c);
		quickSort(array, low, m-1, c);
		quickSort(array, m+1, high, c);
	}
	
	
	private static <K extends Comparable<K>> int partition(K array[], int low, int high, Comparator<K> c) {
		int i = low, j = high + 1;
		int m = ThreadLocalRandom.current().nextInt(i, j);
		K pivot = array[m];
		
		swap(array, low, m);
		
		while (i < j)
		{
			do 
			{
				i++;
			} while (c.compare(array[i], pivot) < 0 && i < high );
			do 
			{
				j--;
			} while (c.compare(array[j], pivot) > 0 && j > low);
			if (i < j)
			{
				swap(array, i, j);
			}
			
		}
		
		swap(array, low, j);
		
		return j;
	}
	
	
	
	
	public static  <K extends Comparable<K>> void InsertionSort(K A[])
	{
		for(int k=1;k <= A.length -1;k++)
		{
			int j=0;
			K x = A[k]; //salvo k in una variabile perchè dovò sovrascrivere k e lo perderei
			for( j = 0 ; j < k ;j++) 
				if(A[j].compareTo(x) > 0) break;  // devo mettere x perchè è un oggetto di tipo comparable(se j > A[k])
				if(j < k) {
				for(int t = k; t > j;t--)
					A[t] = A[t -1];
				A[j]=x;
				}
		}
	}

	
	public static  <K extends Comparable<K>> void InsertionSort(K A[], Comparator<K> c)
	{
		for(int k=1;k <= A.length -1;k++)
		{
			int j=0;
			K x = A[k]; //salvo k in una variabile perchè dovò sovrascrivere k e lo perderei
			for( j = 0 ; j < k ;j++) 
				if(c.compare(A[j],x) > 0) break;  // devo mettere x perchè è un oggetto di tipo comparable(se j > A[k])
				if(j < k) {
				for(int t = k; t > j;t--)
					A[t] = A[t -1];
				A[j]=x;
				}
		}
	}
	
	
	
	
	protected class titleComparator implements Comparator<Movie> {

		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
		
	}
	
	
	protected class votedComparator implements Comparator<Movie> {

		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getVotes() - o2.getVotes();
		}
		
	}
	
	
	protected class recentComparator implements Comparator<Movie> {
		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getYear().compareTo(o2.getYear());
		}
	}
	
	
	
	private static final <T> void  swap (T array[], int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}
}
