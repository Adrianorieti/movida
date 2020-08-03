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
	
	MapImplementation selectedMap = MapImplementation.ArrayOrdinato;
	SortingAlgorithm selectedAlg = SortingAlgorithm.QuickSort;
	

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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Movie getMovieByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
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
			selectedAlg = SortingAlgorithm.QuickSort;
			return true;
		}
		case InsertionSort: {
			selectedAlg = SortingAlgorithm.SelectionSort;
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
			return true;
		}
		case HashIndirizzamentoAperto: {
			selectedMap = MapImplementation.HashIndirizzamentoAperto;
			return true;
		}
		default:
			//throw new IllegalArgumentException("Unexpected value: " + m);
			return false;
		}
	}

}
