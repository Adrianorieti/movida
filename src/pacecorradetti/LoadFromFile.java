package pacecorradetti;

import java.util.Scanner;

import movida.commons.MovidaFileException;
import movida.commons.Movie;
import movida.commons.Person;

import java.util.List;
import java.util.ArrayList;
//import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import movida.commons.MapImplementation;


//TODO replace util.map with pacecorradetti.map


public class LoadFromFile {
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
			{
				scan.close();
				throw new IllegalArgumentException("Unexpected value: " + selectedMap + ". Loading failed");
			}	
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
			director = personMap.search(directorName);
			
			line = scan.nextLine();
			String[] names = formatLine(line).split(",");

			for (int i = 0; i < names.length; i++)  		
			{
				String name = names[i].trim().toLowerCase();
				personToAdd = new Person(name, PersonRole.actor);
				personMap.putIfAbsent(name, personToAdd);
				cast.add(personMap.search(name)); 	
			}

			line = scan.nextLine();
			votes = Integer.parseInt(formatLine(line));

			Person[] castArray = cast.toArray(new Person[cast.size()]);
			Movie movieToAdd = new Movie(title, year, votes, castArray, director);
			
			director.getMovies().add(movieToAdd);		//popola lista di film per attori e registi
			for (Person p : movieToAdd.getCast())
			{
				p.getMovies().add(movieToAdd);
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
