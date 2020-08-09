package pacecorradetti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;


public class MovidaGraph {
	
	List<Collaboration> collaborations;
	HashMap<String, Person> persons;
	
	public MovidaGraph(Map<String, Movie> movies, Map<String, Person> persons) {
		this.collaborations = new ArrayList<Collaboration>();
		populateCollaborations(movies, persons);
	}
	
	
	public void populateCollaborations(Map<String, Movie> movies, Map<String, Person> persons) {
		ArrayList<Person> castQueue = new ArrayList<Person>();
		Iterator<Person> it = castQueue.iterator();
		for (Map.Entry<String, Movie> entry : movies.entrySet())
		{
			Movie currentMovie = entry.getValue();
			for (Person p : currentMovie.getCast())
			{
				castQueue.add(p);
			}
			
			while (it.hasNext()) 
			{
				it.next();
				for (Person p1 : castQueue)
				{
					for (Person p2 : castQueue)
					{
						if (p1 == p2) 
						{
							continue; 
						}
						else
						{
							addCollaboration(p1, p2, currentMovie);
						}
						
						
						
					}
				}
				it.remove();
			}
			
			
		}
	}
	
	public void addCollaboration(Person p1, Person p2, Movie m) {
		boolean found = false;
		for (Collaboration collab : p1.collabs)
		{
			if (collab.getActorA() == p2 || collab.getActorB() == p2)
			{
				found = true;
				collab.movies.add(m);
				break;
			}
		}
		if (!found)
		{
			Collaboration collabTemp = new Collaboration(p1, p2);
			collabTemp.movies.add(m);
			p1.collabs.add(collabTemp);
			p2.collabs.add(collabTemp);
		}
	}
}
