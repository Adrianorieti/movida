package pacecorradetti;

import java.util.Scanner;

import movida.commons.MovidaFileException;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import pacecorradetti.Map;


//TODO replace util.map with pacecorradetti.map


public class LoadFromFile {
	HashMap<String, Person> personMap;
	HashMap<String, Movie> movieMap;
	//HashIndirizzamentoAperto<String,Movie> movieMap2;
	HashIndirizzamentoAperto<String, Person> personMap2;
	
	public void load(File f) throws MovidaFileException, FileNotFoundException {

		Scanner scan = new Scanner(f);

		String line;
		String title = null;
		String directorName;
		Person director;
		int year = 0;
		int votes = 0;
		/* HashMap<String, Person> */personMap = new HashMap<String, Person>();
		/* HashMap<String, Movie> */movieMap = new HashMap<String, Movie>();
		//movieMap2 = new HashIndirizzamentoAperto<String, Movie>(53);
		personMap2 = new  HashIndirizzamentoAperto<String, Person>(53);
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
			director = new Person(directorName);
			personMap.putIfAbsent(directorName, director);
			
			line = scan.nextLine();
			String[] names = formatLine(line).split(",");

			for (int i = 0; i < names.length; i++)  		// aggiunge all'array Person[] tutte le nuove persone, riempie il cast
			{
				personToAdd = new Person(names[i].trim());
				cast.add(personToAdd); 
				//personMap.putIfAbsent(personToAdd.getName(), personToAdd);
				personMap2.putIfAbsent(personToAdd.getName(), personToAdd);
				personMap2.printHash();
				System.out.print("\n");
				
			}

			line = scan.nextLine();
			votes = Integer.parseInt(formatLine(line));

			Person[] castArray = cast.toArray(new Person[0]);
			Movie movieToAdd = new Movie(title, year, votes, castArray, director);
			movieMap.putIfAbsent(movieToAdd.getTitle(), movieToAdd);
			//movieMap2.putIfAbsent(movieToAdd.getTitle(), movieToAdd);
			//movieMap2.printHash();
			
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
		return line.trim();
	}



	/*
	 * private static Person[] addPerson(Person[] persons, Person newperson) {
	 * Person[] persons2 = new Person[persons.length + 1]; System.arraycopy(persons,
	 * 0, persons2, 0, persons.length); persons2[persons2.length - 1] = newperson;
	 * 
	 * return persons2; }
	 */
}