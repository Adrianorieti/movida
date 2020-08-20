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
	
	
	
	
	
	
	
	
 }

