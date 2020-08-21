package pacecorradetti;

/**
 * La classe estende movida.commons.Person aggiungendo una lista di collaborazioni
 */




import java.util.ArrayList;

public class Person extends movida.commons.Person {
	
	protected PersonRole role;
	
	ArrayList<Collaboration> collabs;
	ArrayList<Movie> movies;
	
	public Person(String name, PersonRole role) {
		super(name);	
		collabs = new ArrayList<Collaboration>();
		movies = new ArrayList<Movie>();
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [getName()=");
		builder.append(getName());
		builder.append("]");
		return builder.toString();
	}

	public PersonRole getRole() {
		return role;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}	 
	
	public int numberOfMovies() {
		return movies.size();
	}
	// ritorna l'array di ActorB con cui c'è stata una collaborazione
		public ArrayList<Collaboration> getCollabs() {
			/*
			 * Person[] temp = new Person[this.collabs.size()]; for(int i=0;i<
			 * this.collabs.size();i++) { temp[i] = this.collabs.get(i).actorB; } return
			 * temp;
			 */
			return collabs;
		}
	
	
	
	
	
 }

