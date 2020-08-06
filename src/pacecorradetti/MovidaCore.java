package pacecorradetti;

import java.io.File;

import movida.commons.IMovidaConfig;
import movida.commons.IMovidaDB;
import movida.commons.IMovidaSearch;
import movida.commons.MapImplementation;
import movida.commons.Movie;
import movida.commons.Person;
import movida.commons.SortingAlgorithm;

public class MovidaCore implements IMovidaConfig, IMovidaDB, IMovidaSearch {
	//TODO implement last selected
	MapImplementation selectedMap = MapImplementation.ArrayOrdinato;
	SortingAlgorithm selectedAlg = SortingAlgorithm.QuickSort;
	
	Map<String, Movie> map;
	

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
		// TODO Auto-generated method stub

	}

	@Override
	public void saveToFile(File f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public int countMovies() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int countPeople() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteMovieByTitle(String title) {
		try {
			map.delete(title);
			return true;
		} catch (MovidaKeyException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Movie getMovieByTitle(String title) {
		return map.search(title);
	}

	@Override
	public Person getPersonByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie[] getAllMovies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person[] getAllPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean setSort(SortingAlgorithm a) {
		switch (a) {
		case QuickSort: {
			//TODO set quicksort
			return true;
		}
		case InsertionSort: {
			//TODO set Insertion sort;
			return true;
		}
		default:
			//throw new IllegalArgumentException("Unexpected value: " + a);
			return false;
		}
	}

	@Override
	public boolean setMap(MapImplementation m) {
		switch (m) {
		case ArrayOrdinato: {
			selectedMap = MapImplementation.ArrayOrdinato;
			map = new ArrayOrdinato<String, Movie>();
			return true;
		}
		case HashIndirizzamentoAperto: {
			//TODO selectedMap = 
			return true;
		}
		default:
			//throw new IllegalArgumentException("Unexpected value: " + m);
			return false;
		}
	}

}
