package PaceCorradetti;

import java.util.Scanner;



import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

import movida.commons.*;


public class loadFromFile {

	
	public void loadFromFile() throws MovidaFileException, FileNotFoundException {

		
		File myfile = new File("C:\\Users\\Adriano\\Desktop\\movida.commons\\esempio-formato-dati.txt");
			Scanner scan =  new Scanner(myfile);
			

			String line;
			String title = null;
			String director;
			int index;
			int count = 1;
			int year = 0 ;
			int votes = 0;
			List<Person> personList = new ArrayList<Person>();
			List<Person> cast = new ArrayList<Person>();
			List<Movie> movieList = new ArrayList<Movie>();
			Person newperson = null;
			
			while(scan.hasNextLine()) {
				
				line = scan.nextLine();
				index = line.indexOf(':');
				line = line.substring(index + 1, line.length());
				
				switch(count) {
				case 1:
					title = line.trim();
					break;
				case 2:
					year = Integer.parseInt(line.trim());
					break;
				case 3:
					director = line.trim();
					newperson = new Person(director);
					boolean found = false;
					for(int i = 0;i< personList.size();i++)
					{
						
						if(personList.get(i).getName().compareTo(newperson.getName()) == 0)
							found = true;
					}
					if(!found) personList.add(newperson);
				    break;
				    
				case 4:
					String[] names = line.split(",");
					 // aggiunge all'array Person[] tutti i nomi 
					for(int i = 0; i < names.length;i++ ) {
					//	cast = addPerson(cast,new Person(names[i].trim())); se fosse un array 
						cast.add(new Person(names[i].trim())); // se fosse un array list
					}
					// aggiunge tutte le persone alla lista di persone;
					boolean search = false;
					for(int j=0;j < cast.size();j++) {
						for(int i = 0;i < personList.size();i++) {
							if(cast.get(j).getName().compareTo(personList.get(i).getName()) == 0) {
								search = true;
							}
						}
						if(!search) personList.add(cast.get(j));
					}
					
					break;
					
				case 5:
					votes = Integer.parseInt(line.trim());
					break;
					
				}
				
			
			
				
				if(count == 5) {
					Person[] castarray = cast.toArray(new Person[0]);
					Movie newMovie = new Movie(title,year,votes,castarray,newperson);
					boolean search2 = false;
					for(int i = 0; i < movieList.size();i++) {
						if(newMovie.getTitle().compareTo(movieList.get(i).getTitle()) == 0 )
							search2 = true;
					}
					if(!search2) movieList.add(newMovie);
							}
				count ++;
				
				 if(count == 6) {
					 count = 0;
					 for(int i = 0;i < cast.size();i++){
						 cast.remove(i);
					 }
				
				 }
			}
				
				
			scan.close();
			
			

			for(int i=0; i < movieList.size();i++)
			{
				String name = movieList.get(i).getTitle();
				System.out.println(name);
			}
			


	
		}



private static Person[] addPerson(Person[] persons, Person newperson) {
    Person[] persons2 = new Person[persons.length + 1];
    System.arraycopy(persons, 0, persons2, 0, persons.length);
    persons2[persons2.length - 1] = newperson;

    return persons2;
}
}
    


