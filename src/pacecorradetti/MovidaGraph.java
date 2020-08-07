package pacecorradetti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import movida.commons.*;

public class MovidaGraph {
	
	protected class PersonNode extends Person {
		
		ArrayList<Collaboration> collabs;
		
		public PersonNode(String name) {
			super(name);	
			new ArrayList<Collaboration>();
		}	 
	 }
	
	
}
