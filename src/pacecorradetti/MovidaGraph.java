package pacecorradetti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import jdk.internal.org.jline.terminal.impl.LineDisciplineTerminal;

import java.util.List;

import movida.commons.*;

public class MovidaGraph {
	
	List<Collaboration> collaborations;
	HashMap<String, PersonNode> persons;
	
	public MovidaGraph(Map<String, Movie> movies, Map<String, PersonNode> persons) {
		this.collaborations = new ArrayList<Collaboration>();
		populateCollaborations(movies, persons);
	}
	
	
	public void populateCollaborations(Map<String, Movie> movies, Map<String, PersonNode> persons) {
		LinkedList<Person> personList = new LinkedList<Person>();
		Iterator<Person> it = personList.iterator();
		for (Map.Entry<String, Movie> entry : movies.entrySet())
		{
			for (Person p : entry.getValue().getCast())
			{
				personList.add(p);
			}
			
			while (it.hasNext()) 
			{
				Person currPerson = it.next();
				for (Person p1 : personList)
				{
					for (Person p2 : personList)
					{
						Collaboration collabTemp = new Collaboration(p1, p2);
						coll
						persons.get(p1.getName()).collabs.add(e)
					}
				}
			}
			
			
		}
	}
	
	
}
