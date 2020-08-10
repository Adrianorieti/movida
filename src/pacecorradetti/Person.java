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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [getName()=");
		builder.append(getName());
		builder.append("]");
		return builder.toString();
	}	 
	
	
	
 }

