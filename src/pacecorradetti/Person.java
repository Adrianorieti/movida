package pacecorradetti;

/**
 * La classe estende movida.commons.Person aggiungendo una lista di collaborazioni
 */




import java.util.ArrayList;

public class Person extends movida.commons.Person {
	
	ArrayList<Collaboration> collabs;
	
	public Person(String name) {
		super(name);	
		collabs = new ArrayList<Collaboration>();
	}	 
	
 }

