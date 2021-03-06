package pacecorradetti;

import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import movida.commons.Movie;
import movida.commons.Person;

public class Algorithms {

	public static <K extends Comparable<K>> void quickSort(K array[]) {
		quickSort(array, 0, array.length - 1);
	}

	public static <K extends Comparable<K>> void quickSort(K array[], int low, int high) {
		if (low >= high)
			return;
		int m = partition(array, low, high);
		quickSort(array, low, m - 1);
		quickSort(array, m + 1, high);
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
			} while (array[i].compareTo(pivot) < 0 && i < high);
			do 
			{
				j--;
			} while (array[j].compareTo(pivot) > 0 && j > low);
			if (i < j) {
				swap(array, i, j);
			}
		}
		swap(array, low, j);
		return j;
	}

	public static <K> void quickSort(K array[], Comparator<K> c) {
		quickSort(array, 0, array.length - 1, c);
	}

	private static <K> void quickSort(K array[], int low, int high, Comparator<K> c) {
		if (low >= high)
			return;
		int m = partition(array, low, high, c);
		quickSort(array, low, m - 1, c);
		quickSort(array, m + 1, high, c);
	}

	private static <K> int partition(K array[], int low, int high, Comparator<K> c) {
		int i = low, j = high + 1;
		int m = ThreadLocalRandom.current().nextInt(i, j);
		K pivot = array[m];
		swap(array, low, m);

		while (i < j) 
		{
			do 
			{
				i++;
			} while (c.compare(array[i], pivot) < 0 && i < high);
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

	
	public static <K extends Comparable<K>> void InsertionSort(K A[]) {
		for (int i = 1; i <= A.length - 1; i++) 
		{
			int j = 0;
			K x = A[i]; 
			for (j = 0; j < i; j++)
				if (A[j].compareTo(x) > 0)
					break;
			if (j < i) 
			{
				for (int t = i; t > j; t--) 
				{
					A[t] = A[t - 1];					
				}
				A[j] = x;
			}
		}
//		for (int i = 0; i < A.length; i ++)
//		{
//			int j = i;
//			while (j > 0 && A[j-1].compareTo(A[j]) > 0 )
//			{
//				swap(A, j, j-1);
//				j = j - 1;
//			}
//		}
	}
	
	
	public static <K> void InsertionSort(K A[], Comparator<K> c) {
		for (int i = 1; i <= A.length - 1; i++) 
		{
			int j = 0;
			K x = A[i];
			for (j = 0; j < i; j++)
				if (c.compare(A[j], x) > 0)
					break;
			if (j < i) {
				for (int t = i; t > j; t--)
					A[t] = A[t - 1];
				A[j] = x;
			}
		}
//		for (int i = 0; i < A.length; i ++)
//		{
//			int j = i;
//			while (j > 0 && c.compare(A[j-1], A[j]) > 0 )
//			{
//				swap(A, j, j-1);
//				j = j - 1;
//			}
//		}
	}
	 

	private static final <T> void swap(T array[], int i, int j) {
		T temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static Comparator<Movie> TITLE = new Comparator<Movie>() {
		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	};

	public static Comparator<Movie> RECENT = new Comparator<Movie>() {
		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getYear().compareTo(o2.getYear());
		}
	};

	public static Comparator<Movie> VOTES = new Comparator<Movie>() {
		@Override
		public int compare(Movie o1, Movie o2) {
			return o1.getVotes().compareTo(o2.getVotes());
		}
	};
	
	public static Comparator<Person> N_MOVIES = new Comparator<Person>() {
		@Override
		public int compare(Person o1, Person o2) {
			return o1.numberOfMovies() - o2.numberOfMovies();
		}
	};	
	
	
}


