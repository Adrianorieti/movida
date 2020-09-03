package pacecorradetti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import movida.commons.*;

public class MovidaCore implements IMovidaConfig, IMovidaDB, IMovidaSearch, IMovidaCollaborations {
	LoadFromFile lff;
	MapImplementation selectedMap = MapImplementation.ArrayOrdinato;
	SortingAlgorithm selectedAlg = SortingAlgorithm.QuickSort;
	Map<String, Movie> movieMap;
	Map<String, Person> personMap;
	MovidaGraph graph;

	File configFile ;
	ConfigManager confingMan ;

	public MovidaCore() {
		configFile = new File("config.ini");
		try 
		{
			confingMan = new ConfigManager(configFile);
		} catch (MovidaFileException | FileNotFoundException e) {
			e.printStackTrace();
		}
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
		ArrayList<Movie> toReturn = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) 
		{
			if (e.getValue().getYear().equals(year)) 
			{
				toReturn.add(e.getValue());
			}
		}
		return toReturn.toArray(new Movie[toReturn.size()]);
	}

	@Override
	public Movie[] searchMoviesDirectedBy(String name) {
		ArrayList<Movie> toReturn = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) 
		{
			if (e.getValue().getDirector().getName().equals(name.trim().toLowerCase())) 
			{
				toReturn.add(e.getValue());
			}
		}
		return toReturn.toArray(new Movie[toReturn.size()]);
//		return getPersonByName(name).getMovies().toArray(Movie[]::new );
	}

	@Override
	public Movie[] searchMoviesStarredBy(String name) {
		ArrayList<Movie> toReturn = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) 
		{
			Movie m = e.getValue();
			for (Person p : m.getCast()) 
			{
				if (p.getName().equals(name.trim().toLowerCase())) 
				{
					toReturn.add(e.getValue());
					break;
				}
			}
		}
		return toReturn.toArray(new Movie[toReturn.size()]);	
//		return getPersonByName(name).getMovies().toArray(Movie[]::new );
	}

	@Override
	public Movie[] searchMostVotedMovies(Integer N) {
		ArrayList<Movie> movieList = movieMap.valueList();
		Movie[] movieArr = movieList.toArray(new Movie[movieList.size()]);
		switch (selectedAlg) 
		{
			case QuickSort: 
			{
				Algorithms.quickSort(movieArr, Algorithms.VOTES.reversed());
				break;
			}
			case InsertionSort:
			{
				Algorithms.InsertionSort(movieArr, Algorithms.VOTES.reversed());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + selectedAlg);
		}
		Movie[] toReturn = new Movie[(N <= movieArr.length)? N : movieArr.length];
		for (int i = 0; i < N && i < movieArr.length; i++) 
		{
			toReturn[i] = movieArr[i];
		}
		return toReturn;
	}

	@Override
	public Movie[] searchMostRecentMovies(Integer N) {
		ArrayList<Movie> movieList = movieMap.valueList();
		Movie[] movieArr = movieList.toArray(new Movie[movieList.size()]);
		switch (selectedAlg) 
		{
			case QuickSort: 
			{
				Algorithms.quickSort(movieArr, Algorithms.RECENT.reversed());
				break;
			}
			case InsertionSort: 
			{
				Algorithms.InsertionSort(movieArr, Algorithms.RECENT.reversed());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + selectedAlg);
		}
		Movie[] toReturn = new Movie[(N <= movieArr.length)? N : movieArr.length];
		for (int i = 0; i < N && i < movieArr.length; i++) 
		{
			toReturn[i] = movieArr[i];
		}
		return toReturn;
	}

	@Override
	public Person[] searchMostActiveActors(Integer N) {
		Stream<Person> actors = personMap.valueList().stream().filter(p -> p.getRole() == PersonRole.actor);
		Person[] personArr = actors.toArray(Person[]::new);
		switch (selectedAlg) 
		{
			case QuickSort: 
			{
				Algorithms.quickSort(personArr, Algorithms.N_MOVIES.reversed());
				break;
			}
			case InsertionSort:
			{
				Algorithms.InsertionSort(personArr, Algorithms.N_MOVIES.reversed());
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + selectedAlg);
		}
		Person[] toReturn = new Person[(N <= personArr.length)? N : personArr.length];
		for (int i = 0; i < N && i < personArr.length; i++) 
		{
			toReturn[i] = personArr[i];
		}
		return toReturn;
	}

	@Override
	public void loadFromFile(File f) throws MovidaFileException {
		
		LoadFromFile temp = new LoadFromFile();
		temp.setMap(selectedMap);
		try 
		{
			temp.load(f);
		} 
		catch (FileNotFoundException e) 
		{
			throw new MovidaFileException();
		}
		catch (IllegalArgumentException e)
		{
			e.printStackTrace();
			return;
		}
		lff = temp;
		movieMap = lff.getMovieMap();
		personMap = lff.getPersonMap();
		graph = new MovidaGraph(movieMap, personMap);
		return;
	}

	@Override
	public void saveToFile(File f) throws MovidaFileException {
		try 
		{
			FileWriter save = new FileWriter(f);
			for (Map<String, Movie>.Entry e : movieMap.entrySet()) 
			{
				save.append("Title:" + "\t" + e.value.getTitle() + "\n");
				save.append("Year:" + "\t" + e.value.getYear() + "\n");
				save.append("Director:" + "\t" + e.value.getDirector().getName() + "\n");
				Person cast[] = e.value.getCast();
				save.append("Cast:" + "\t");
				for (int i = 0; i < cast.length; i++) 
				{
					if (i == cast.length - 1)
						save.append(cast[i].getName() + "\n");
					else
						save.append(cast[i].getName() + "," + "\t");
				}
				save.append("Votes" + "\t" + e.value.getVotes() + "\n");
				save.append("\n");
			}
			save.close();
		} 
		catch (IOException e) 
		{
			throw new MovidaFileException();
		}
	}

	@Override
	public void clear() {
		movieMap.clear();
		personMap.clear();
		graph = null;
	}

	@Override
	public int countMovies() {
		return movieMap.size();
	}

	@Override
	public int countPeople() {
		return personMap.size();
	}

	@Override
	public boolean deleteMovieByTitle(String title) {
		try 
		{
			//TODO testare attentamente
			Movie m = getMovieByTitle(title);
			graph.removeMovieFromCollabs(m);
			m.getDirector().getMovies().remove(m);
			for (Person p : m.getCast())
			{
				p.getMovies().remove(m);
			}
			movieMap.delete(title);
			return true;
		} 
		catch (MovidaKeyException e) 
		{
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
		Movie[] arr = new Movie[movieMap.size()];
		int i = 0;
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) 
		{
			arr[i++] = e.getValue();
		}
		return arr;
	}

	@Override
	public Person[] getAllPeople() {
		Person[] arr = new Person[personMap.size()];
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
				try 
				{
					confingMan.overrideAlg(configFile, "QuickSort");
				} 
				catch (IOException e) 
				{
					throw new MovidaFileException();
				}
				return true;
			}
			case InsertionSort: 
			{
				selectedAlg = SortingAlgorithm.InsertionSort;
				try 
				{
					confingMan.overrideAlg(configFile, "InsertionSort");
				} 
				catch (IOException e) 
				{
					throw new MovidaFileException();
				}
				return true;
			}
			default:
				new IllegalArgumentException("Unexpected value: " + a + "; Algorithm unchanged").printStackTrace();
				return false;
		}
	}

	@Override
	public boolean setMap(MapImplementation m) {
		switch (m) 
		{
			case ArrayOrdinato: 
			{
				selectedMap = MapImplementation.ArrayOrdinato;
				lff.setMap(MapImplementation.ArrayOrdinato);
				try 
				{
					confingMan.overrideMap(configFile, "ArrayOrdinato");
				} 
				catch (IOException e) 
				{
					throw new MovidaFileException();
				}
				return true;
			}
			case HashIndirizzamentoAperto: 
			{
				
				selectedMap = MapImplementation.HashIndirizzamentoAperto;
				lff.setMap(MapImplementation.HashIndirizzamentoAperto);
				try 
				{
					confingMan.overrideMap(configFile, "HashIndirizzamentoAperto");
				} 
				catch (IOException e) 
				{
					throw new MovidaFileException();
				}
				return true;
			}
			default:
				new IllegalArgumentException("Unexpected value: " + "; Map unchanged");
				return false;
		}
	}

	@Override
	public Person[] getDirectCollaboratorsOf(Person actor) {
		ArrayList<Collaboration> collabList = actor.getCollabs();
		ArrayList<Person> toReturn = new ArrayList<Person>();
		for (Collaboration c : collabList) 
		{
			toReturn.add(actor.collaborator(c));
		}
		return toReturn.toArray(Person[]::new);
	}

	@Override
	public Person[] getTeamOf(Person actor) {
		ArrayList<Person> toReturn = new ArrayList<Person>();
		HashMap<Person, Boolean> marked = new HashMap<Person, Boolean>();
		Queue<Person> fringe = new ArrayDeque<Person>();
		fringe.add(actor);
		marked.put(actor, true);

		while (!fringe.isEmpty()) 
		{
			Person p = fringe.remove();
			for (Collaboration c : p.getCollabs()) 
			{
				Person toAdd;
				toAdd = (!c.getActorA().equals(p)) ? c.getActorA() : c.getActorB();

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
	public Collaboration[] maximizeCollaborationsInTheTeamOf(Person actor) {
//		HashMap<Person, Double> score = new HashMap<Person, Double>();
//		HashMap<Person, Collaboration> bestCollab = new HashMap<Person, Collaboration>();
//		PriorityQueue<Person> toProcess = new PriorityQueue<Person>((a, b) -> score.get(a).compareTo(score.get(b)));
//		HashMap<Collaboration, Boolean> checked = new HashMap<Collaboration, Boolean>(); 
//		
//		score.put(actor, 0D);
//		toProcess.add(actor);
//
//		while (!toProcess.isEmpty()) 
//		{
//			Person p1 = toProcess.remove();
//			for (Collaboration c : p1.getCollabs()) 
//			{
//				if (!checked.containsKey(c))
//				{
//					Person p2 = p1.collaborator(c);
//					if (!score.containsKey(p2)) 
//					{
//						score.put(p2, score.get(p1) + c.getScore());
//						toProcess.add(p2);
//						bestCollab.put(p2, c);	
//						checked.put(c, true);
//					}
//					else if(score.get(p2) < score.get(p1) + c.getScore())
//					{
//						score.put(p2, score.get(p1) + c.getScore());
//						bestCollab.put(p2, c);
//						checked.put(c, true);
//					}					
//				}
//			}
//		}
//
//		return bestCollab.values().toArray(Collaboration[]::new);
		Person [] team = getTeamOf(actor);
		HashMap<Person, LinkedList<Person>> rep = new HashMap<>();		//associa ogni elemento alla lista che lo contiene
		ArrayList<Collaboration> toReturn = new ArrayList<Collaboration>();
		List<Collaboration> collabs = new ArrayList<Collaboration>();
		rep.put(actor, new LinkedList<Person>());
		rep.get(actor).add(actor);
		for (Person p : team) 
		{
			rep.putIfAbsent(p, new LinkedList<Person>());
			rep.get(p).add(p);
			collabs.addAll(p.getCollabs());
		}
		collabs = collabs.stream().distinct()
				.sorted((a, b) -> b.getScore().compareTo(a.getScore()))
				.collect(Collectors.toList());
				
		for (Collaboration c : collabs)
		{
			Person a = c.getActorA();
			Person b = c.getActorB();
			if(rep.get(a) != rep.get(b))				//se la lista associata è diversa gli insiemi sono disgiunti
			{
				toReturn.add(c);
				if (rep.get(a).size() <= rep.get(b).size())
				{
					rep.get(b).addAll(rep.get(a));
					rep.replace(a, rep.get(b));					
				}
				else
				{
					rep.get(a).addAll(rep.get(b));
					rep.replace(b, rep.get(a));	
				}
			}
		}
		
		return toReturn.toArray(Collaboration[]::new);
	}

}