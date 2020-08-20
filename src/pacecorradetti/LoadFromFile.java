package pacecorradetti;

import java.util.Scanner;

import movida.commons.MovidaFileException;


import java.util.List;
import java.util.ArrayList;
//import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import movida.commons.MapImplementation;


//TODO replace util.map with pacecorradetti.map


public class LoadFromFile {
//	private HashMap<String, Person> personMap;
//	private HashMap<String, Movie> movieMap;
	private MapImplementation selectedMap;
	private Map<String, Person> personMap;
	private Map<String, Movie> movieMap;
	public void load(File f) throws MovidaFileException, FileNotFoundException {

		Scanner scan = new Scanner(f);

		String line;
		String title = null;
		String directorName;
		Person director;
		int year = 0;
		int votes = 0;
		
		
		switch (selectedMap) 
		{
			case ArrayOrdinato: 
			{
				personMap = new ArrayOrdinato<String, Person>();
				movieMap = new ArrayOrdinato<String, Movie>();
				break;
			}
			case HashIndirizzamentoAperto : 
			{
				personMap = new HashIndirizzamentoAperto<String, Person>(313);
				movieMap = new HashIndirizzamentoAperto<String, Movie>(313);
				break;
			}
			default:
				new IllegalArgumentException("Unexpected value: " + selectedMap).printStackTrace();
		}
		
		
		List<Person> cast;
		Person personToAdd = null;

		while (scan.hasNextLine()) 
		{
			cast = new ArrayList<Person>();

			line = scan.nextLine();		
			title = formatLine(line);
			
			line = scan.nextLine();
			year = Integer.parseInt(formatLine(line));
			
			line = scan.nextLine();
			directorName = formatLine(line);	
			director = new Person(directorName, PersonRole.director);
			personMap.putIfAbsent(directorName, director);
			
			line = scan.nextLine();
			String[] names = formatLine(line).split(",");

			for (int i = 0; i < names.length; i++)  		
			{
				String name = names[i].trim().toLowerCase();
				personToAdd = new Person(name, PersonRole.actor);
				personMap.putIfAbsent(personToAdd.getName(), personToAdd);
				cast.add(personMap.search(name)); 	
			}

			line = scan.nextLine();
			votes = Integer.parseInt(formatLine(line));

			Person[] castArray = cast.toArray(new Person[0]);
			Movie movieToAdd = new Movie(title, year, votes, castArray, director);
			
			//popola lista di film per ogni attore
			for (Person p : movieToAdd.getCast())
			{
				p.movies.add(movieToAdd);
			}
			
			movieMap.putIfAbsent(movieToAdd.getTitle(), movieToAdd);
			
			if (scan.hasNextLine())
			{
				scan.nextLine();				
			}
		}
		scan.close();	
	}

	private String formatLine(String line) {
		int index = line.indexOf(':');
		line = line.substring(index + 1, line.length());
		return line.trim().toLowerCase();
	}

	public Map<String, Person> getPersonMap() {
		return personMap;
	}

	public Map<String, Movie> getMovieMap() {
		return movieMap;
	}

	public void setMap (MapImplementation m) {
		selectedMap = m;
	}
}
