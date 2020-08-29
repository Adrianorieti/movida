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
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import movida.commons.*;

public class MovidaCore implements IMovidaConfig, IMovidaDB, IMovidaSearch, IMovidaCollaborations {
	// TODO implement last selected
	LoadFromFile lff;
	MapImplementation selectedMap = MapImplementation.ArrayOrdinato;
	SortingAlgorithm selectedAlg = SortingAlgorithm.QuickSort;
	Map<String, Movie> movieMap;
	Map<String, Person> personMap;
	MovidaGraph graph;

	File file ;
	loadConfig lc ;

	public MovidaCore() {
		file = new File("config.ini");
		try {
			lc = new loadConfig(file);
		} catch (MovidaFileException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Movie[] searchMoviesByTitle(String title) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) {
			if (e.key.contains(title)) {
				l.add(e.getValue());
			}
		}
		return l.toArray(new Movie[l.size()]);
	}

	@Override
	public Movie[] searchMoviesInYear(Integer year) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) {
			if (e.getValue().getYear().equals(year)) {
				l.add(e.getValue());
			}
		}
		return l.toArray(new Movie[l.size()]);
	}

	@Override
	public Movie[] searchMoviesDirectedBy(String name) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) {
			if (e.getValue().getDirector().getName().equals(name.trim().toLowerCase())) {
				l.add(e.getValue());
			}
		}
		return l.toArray(new Movie[l.size()]);
	}

	@Override
	public Movie[] searchMoviesStarredBy(String name) {
		ArrayList<Movie> l = new ArrayList<Movie>();
		for (Map<String, Movie>.Entry e : movieMap.entrySet()) {
			Movie m = e.getValue();
			for (Person p : m.getCast()) {
				if (p.getName().equals(name.trim().toLowerCase())) {
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
		try {
			temp.load(f);
		} catch (MovidaFileException fe) {
			fe.printStackTrace();
			return;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
		// TODO rivedere qui
		lff = temp;
		movieMap = lff.getMovieMap();
		personMap = lff.getPersonMap();
		graph = new MovidaGraph(movieMap, personMap);
		return;
	}

	@Override
	public void saveToFile(File f) {

		try {
			FileWriter save = new FileWriter(f);
			for (Map<String, Movie>.Entry e : movieMap.entrySet()) {
				save.append("Title:" + "\t" + e.value.getTitle() + "\n");

				save.append("Year:" + "\t" + e.value.getYear() + "\n");

				save.append("Director:" + "\t" + e.value.getDirector().getName() + "\n");

				Person cast[] = e.value.getCast();
				save.append("Cast:" + "\t");
				for (int i = 0; i < cast.length; i++) {
					if (i == cast.length - 1)
						save.append(cast[i].getName() + "\n"); // l'ultimo elemento non ha la virgola
					else
						save.append(cast[i].getName() + "," + "\t");
				}

				save.append("Votes" + "\t" + e.value.getVotes() + "\n");
				save.append("\n");

			}

			save.close();
		} catch (IOException e) {
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
		switch (a) 
		{
			case QuickSort: 
			{
				selectedAlg = SortingAlgorithm.QuickSort;
				try 
				{
					lc.overrideAlg(file, "QuickSort");
				} catch (IOException e) 
				{
					System.out.print("File not found");
					e.printStackTrace();
				}
				return true;
			}
			case InsertionSort: 
			{
				selectedAlg = SortingAlgorithm.SelectionSort;
				try 
				{
					lc.overrideAlg(file, "InsertionSort");
				} catch (IOException e) 
				{
					System.out.print("File not found");
					e.printStackTrace();
				}
				return true;
			}
			default:
				new IllegalArgumentException("Unexpected value: " + a + "; Algorithm unchanged").printStackTrace();
				return false;
		}
	}

	// TODO fixare new
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
					lc.overrideMap(file, "ArrayOrdinato");
				} catch (IOException e) 
				{
					System.out.print("File not found");
					e.printStackTrace();
				}
				return true;
			}
			case HashIndirizzamentoAperto: 
			{
				
				selectedMap = MapImplementation.HashIndirizzamentoAperto;
				lff.setMap(MapImplementation.HashIndirizzamentoAperto);
				try 
				{
					lc.overrideMap(file, "HashIndirizzamentoAperto");
				} catch (IOException e) 
				{
					System.out.print("File not found");
					e.printStackTrace();
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
		ArrayList<Collaboration> collabList = personMap.search(actor.getName()).getCollabs();
		ArrayList<Person> toReturn = new ArrayList<Person>();
		for (Collaboration c : collabList) {
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

		while (!fringe.isEmpty()) {
			Person p = fringe.remove();
			for (Collaboration c : p.getCollabs()) {
				Person toAdd;
				toAdd = (!c.getActorA().equals(p)) ? c.getActorA() : c.getActorB();

				if (!marked.containsKey(toAdd) || marked.get(toAdd) == false) {
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
		HashMap<Person, LinkedList<Person>> head = new HashMap<>();
		ArrayList<Collaboration> toReturn = new ArrayList<Collaboration>();
		List<Collaboration> collabs = new ArrayList<Collaboration>();
		head.put(actor, new LinkedList<Person>());
		head.get(actor).add(actor);
		for (Person p : team) 
		{
			head.putIfAbsent(p, new LinkedList<Person>());
			head.get(p).add(p);
			collabs.addAll(p.getCollabs());
		}
		collabs = collabs.stream().distinct()
				.sorted((a, b) -> b.getScore().compareTo(a.getScore()))
				.collect(Collectors.toList());
				
		for (Collaboration c : collabs)
		{
			Person a = c.getActorA();
			Person b = c.getActorB();
			if(head.get(a) != head.get(b))
			{
				toReturn.add(c);
				head.get(a).addAll(head.get(b));
				head.replace(b, head.get(a));
				
			}
		}
		
		return toReturn.toArray(Collaboration[]::new);
		
	
	}

	public void loadConfig(File f) throws MovidaFileException, FileNotFoundException {
		Scanner scan = new Scanner(f);
		while (scan.hasNextLine()) {
			// selectedMap = scan.nextLine();
			// selectedAlg = scan.nextLine();
		}
		scan.close();
	}
}