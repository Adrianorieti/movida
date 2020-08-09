package pacecorradetti;

import java.util.ArrayList;

import movida.commons.Collaboration;
import movida.commons.Person;

public class PersonNode extends Person {
	
	ArrayList<Collaboration> collabs;
	
	public PersonNode(String name) {
		super(name);	
		new ArrayList<Collaboration>();
	}	 
	
 }

