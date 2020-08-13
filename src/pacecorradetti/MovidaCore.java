package pacecorradetti;

import java.io.File;
import java.io.FileNotFoundException;

import movida.commons.IMovidaConfig;
import movida.commons.IMovidaDB;
import movida.commons.IMovidaSearch;
import movida.commons.MapImplementation;
import movida.commons.MovidaFileException;
import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.SortingAlgorithm;

public class MovidaCore implements IMovidaConfig, IMovidaDB, IMovidaSearch {
	//TODO implement last selected
	LoadFromFile lff;
	MapImplementation selectedMap = MapImplementation.ArrayOrdinato;
	SortingAlgorithm selectedAlg = SortingAlgorithm.QuickSort;
	
	Map<String, Movie> movieMap;
	Map<String, Person> personMap;
	

	@Override
	public Movie[] searchMoviesByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie[] searchMoviesInYear(Integer year) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie[] searchMoviesDirectedBy(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie[] searchMoviesStarredBy(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie[] searchMostVotedMovies(Integer N) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie[] searchMostRecentMovies(Integer N) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person[] searchMostActiveActors(Integer N) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadFromFile(File f) {
		LoadFromFile temp = new LoadFromFile();
		try 
		{
			lff.load(f);			
		}
		catch (MovidaFileException fe)
		{
			fe.printStackTrace();
			return;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
			return;
		}
		lff = temp;

	}

	@Override
	public void saveToFile(File f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		movieMap.clear();
		personMap.clear();
	}

	@Override
	public int countMovies() {
		return movieMap.length();
	}

	@Override
	public int countPeople() {
		return personMap.length();
	}

	@Override
	public boolean deleteMovieByTitle(String title) {
		try {
			movieMap.delete(title);
			return true;
		} catch (MovidaKeyException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Movie getMovieByTitle(String title) {
		return movieMap.search(title.trim().toLowerCase());
	}

	@Override
	public Person getPersonByName(String name) {
		personMap.search(name.trim().toLowerCase());
		return null;
	}

	@Override
	public Movie[] getAllMovies() {
		Movie[] arr = new Movie[movieMap.length()];
		int i = 0;
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) {
			arr[i++] = e.getValue();
		}
		return arr;
	}

	@Override
	public Person[] getAllPeople() {
		Person[] arr = new Person[personMap.length()];
		int i = 0;
		for (Map<String, Person>.Entry e : personMap.entrySet()) {
			arr[i++] = e.getValue();
		}
		return arr;
	}

	@Override
	public boolean setSort(SortingAlgorithm a) {
		switch (a) {
		case QuickSort: {
			selectedAlg = SortingAlgorithm.QuickSort;
			return true;
		}
		case InsertionSort: {
			selectedAlg = SortingAlgorithm.SelectionSort;
			return true;
		}
		default:
			new IllegalArgumentException("Unexpected value: " + a + "; Algorithm unchanged").printStackTrace();
			return false;
		}
	}

	@Override
	public boolean setMap(MapImplementation m) {
		switch (m) {
		case ArrayOrdinato: {
			selectedMap = MapImplementation.ArrayOrdinato;
			movieMap = new ArrayOrdinato<String, Movie>();
			personMap = new ArrayOrdinato<String, Person>();
			return true;
		}
		case HashIndirizzamentoAperto: {
			/*
			 * selectedMap = MapImplementation.HashIndirizzamentoAperto; movieMap = new
			 * ArrayOrdinato<String, Movie>(); personMap = new ArrayOrdinato<String,
			 * Person>();
			 */
			return true;
		}
		default:
			new IllegalArgumentException("Unexpected value: " + "; Map unchanged");
			return false;
		}
	}

}
