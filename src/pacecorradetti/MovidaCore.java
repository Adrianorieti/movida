package pacecorradetti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.stream.Stream;

import movida.commons.IMovidaCollaborations;
import movida.commons.IMovidaConfig;
import movida.commons.IMovidaDB;
import movida.commons.IMovidaSearch;
import movida.commons.MapImplementation;
import movida.commons.MovidaFileException;
import movida.commons.SortingAlgorithm;

public class MovidaCore implements IMovidaConfig, IMovidaDB, IMovidaSearch, IMovidaCollaborations {
	//TODO implement last selected
	LoadFromFile lff;
	MapImplementation selectedMap = MapImplementation.ArrayOrdinato;
	SortingAlgorithm selectedAlg = SortingAlgorithm.QuickSort;
	Map<String, Movie> movieMap;
	Map<String, Person> personMap;
	MovidaGraph graph;
	
	public MovidaCore() {
		//TODO fix first graph generation
		//graph = new MovidaGraph(movieMap, personMap);
	}

	@Override
	public Movie[] searchMoviesByTitle(String title) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet())
		{
			if (e.key.contains(title))
			{
				l.add(e.getValue());
			}
		}
		return l.toArray(new Movie[l.size()]);
	}

	@Override
	public Movie[] searchMoviesInYear(Integer year) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet())
		{
			if (e.getValue().getYear().equals(year) )
			{
				l.add(e.getValue());
			}
		}
		return l.toArray(new Movie[l.size()]);
	}

	@Override
	public Movie[] searchMoviesDirectedBy(String name) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet())
		{
			if (e.getValue().getDirector().getName().equals(name.trim().toLowerCase()) )
			{
				l.add(e.getValue());
			}
		}
		return l.toArray(new Movie[l.size()]);
	}

	@Override
	public Movie[] searchMoviesStarredBy(String name) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet())
		{
			Movie m = e.getValue();
			for (Person p : m.getCast())
			{
				if (p.getName().equals(name.trim().toLowerCase()))
				{
					l.add(e.getValue());
					break;
				}
			}
		}
		return l.toArray(new Movie[l.size()]);
	}


	@Override
	public Movie[] searchMostVotedMovies(Integer N) {
		ArrayList<Movie> movieList = movieMap.valueList();
		Movie[] movieArr = movieList.toArray(new Movie[movieList.size()]);
		Algorithms.quickSort(movieArr, Algorithms.VOTES.reversed());
		Movie[] toReturn = new Movie[N];
		for (int i = 0; i < N; i++) {
			toReturn[i] = movieArr[i];
		}
		return toReturn;
	}

	@Override
	public Movie[] searchMostRecentMovies(Integer N) {
		ArrayList<Movie> movieList = movieMap.valueList();
		Movie[] movieArr = movieList.toArray(new Movie[movieList.size()]);
		Algorithms.quickSort(movieArr, Algorithms.RECENT.reversed());
		Movie[] toReturn = new Movie[N];
		for (int i = 0; i < N; i++) {
			toReturn[i] = movieArr[i];
		}
		return toReturn;
	}

	@Override
	public Person[] searchMostActiveActors(Integer N) {
		Stream<Person> actors = personMap.valueList().stream().filter(p -> p.getRole() == PersonRole.actor);
		Person[] personArr = actors.toArray(Person[]::new);
		Algorithms.quickSort(personArr, Algorithms.N_MOVIES.reversed());
		Person[] toReturn = new Person[N];
		for (int i = 0; i < N; i++) {
			toReturn[i] = personArr[i];
		}
		return toReturn;
	}

	@Override
	public void loadFromFile(File f) {
		LoadFromFile temp = new LoadFromFile();
		temp.setMap(selectedMap);
		try 
		{
			temp.load(f);			
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
		//TODO rivedere qui
		lff = temp;
		movieMap = lff.getMovieMap();
		personMap = lff.getPersonMap();
		graph = new MovidaGraph(movieMap, personMap);
		return;
	}

	@Override
	public void saveToFile(File f) {
		
		 try 
		 {
			FileWriter save = new FileWriter(f);
			for (Map<String, Movie>.Entry e : movieMap.entrySet())
			{
				save.append("Title:" + "\t" + e.value.getTitle() + "\n");
				
				save.append("Year:" +"\t" + e.value.getYear() + "\n");
				
				save.append("Director:" + "\t" + e.value.getDirector().getName() + "\n");
				
				Person cast[] =  e.value.getCast();
				save.append("Cast:" + "\t");
				for(int i=0;i < cast.length;i++)
				  {
					if(i == cast.length-1) save.append(cast[i].getName() +"\n"); //l'ultimo elemento non ha la virgola 
					else save.append(cast[i].getName() +"," +"\t");
				  }
					
				save.append("Votes" + "\t" + e.value.getVotes() + "\n");
				save.append("\n");
			
			}
			
			save.close();
		 } 
		 catch (IOException e) 
		 {
			System.out.println("Impossibile aprire il file.");
			e.printStackTrace();
		 }
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
		return personMap.search(name.trim().toLowerCase());
		 
	}

	@Override
	public Movie[] getAllMovies() {
		Movie[] arr = new Movie[movieMap.length()];
		int i = 0;
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) 
		{
			arr[i++] = e.getValue();
		}
		return arr;
	}

	@Override
	public Person[] getAllPeople() {
		Person[] arr = new Person[personMap.length()];
		int i = 0;
		for (Map<String, Person>.Entry e : personMap.entrySet()) 
		{
			arr[i++] = e.getValue();
		}
		return arr;
	}

	@Override
	public boolean setSort(SortingAlgorithm a) {
		switch (a) 
		{
			case QuickSort: 
			{
				selectedAlg = SortingAlgorithm.QuickSort;
				return true;
			}
			case InsertionSort: 
			{
				selectedAlg = SortingAlgorithm.SelectionSort;
				return true;
			}
			default:
				new IllegalArgumentException("Unexpected value: " + a + "; Algorithm unchanged").printStackTrace();
				return false;
		}
	}

	//TODO fixare new 
	@Override
	public boolean setMap(MapImplementation m) {
		switch (m) 
		{
			case ArrayOrdinato: 
			{
				selectedMap = MapImplementation.ArrayOrdinato;
				//movieMap = new ArrayOrdinato<String, Movie>();
				//personMap = new ArrayOrdinato<String, Person>();
				lff.setMap(MapImplementation.ArrayOrdinato);
				return true;
			}
			case HashIndirizzamentoAperto: 
			{
				
				selectedMap = MapImplementation.HashIndirizzamentoAperto;
				//movieMap = new HashIndirizzamentoAperto<String, Movie>(313);
				//personMap = new HashIndirizzamentoAperto<String, Person>(313);
				lff.setMap(MapImplementation.HashIndirizzamentoAperto);
				return true;
			}
			default:
				new IllegalArgumentException("Unexpected value: " + "; Map unchanged");
				return false;
		}
	}
	
	@Override
	public Person[] getDirectCollaboratorsOf(movida.commons.Person actor){
		ArrayList<Collaboration> collabList = personMap.search(actor.getName()).getCollabs();
		ArrayList<Person> toReturn = new ArrayList<Person>(); 
		for (Collaboration c : collabList)
		{
			if (c.getActorA().getName().equals(actor.getName()))
			{
				toReturn.add(c.getActorB());
			}
			else 
			{
				toReturn.add(c.getActorA());
			}
		}
		return toReturn.toArray(Person[]::new);
	}

	@Override
	public Person[] getTeamOf(movida.commons.Person actor) {
		ArrayList<Person> toReturn = new ArrayList<Person>();
		Person p = personMap.search(actor.getName());
		HashMap<Person, Boolean> marked = new HashMap<Person, Boolean>();
		Queue<Person> fringe = new ArrayDeque<Person>();
		fringe.add(p);
		marked.put(p, true);
		
		while (!fringe.isEmpty())
		{
			p = fringe.remove();
			for (Collaboration c : p.getCollabs())
			{
				Person toAdd;
				toAdd = (!c.getActorA().equals(p))? c.getActorA() : c.getActorB();

				if (!marked.containsKey(toAdd) || marked.get(toAdd) == false) 
				{
					fringe.add(toAdd);
					marked.put(toAdd, true);
					toReturn.add(toAdd);
				}
			}
		}
		return toReturn.toArray(Person[]::new);
	}

	@Override
	public movida.commons.Collaboration[] maximizeCollaborationsInTheTeamOf(movida.commons.Person actor) {
		// TODO Auto-generated method stub
		return null;
	}

}