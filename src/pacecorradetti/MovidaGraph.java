package pacecorradetti;

import java.util.ArrayList;
import java.util.List;

import movida.commons.Collaboration;
import movida.commons.Movie;
import movida.commons.Person;




public class MovidaGraph {
	
	List<Collaboration> collaborationList;
	//Map<String, Person> persons;
	
	public MovidaGraph(Map<String, Movie> movies, Map<String, Person> persons) {
		this.collaborationList = new ArrayList<Collaboration>();
		populateCollaborations(movies, persons);
	}
	
	
	public void populateCollaborations(Map<String, Movie> movies, Map<String, Person> persons) {
		for (Map<String, Movie>.Entry entry : movies.entrySet())
		{
			Movie currentMovie = entry.getValue();

			for (Person p1 : currentMovie.getCast())
			{
				for (Person p2 : currentMovie.getCast())
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
		}
	}
	
	
	public void addCollaboration(Person p1, Person p2, Movie m) {
		boolean found = false;
		for (Collaboration collab : p1.getCollabs())
		{
			if (collab.getActorA() == p2 || collab.getActorB() == p2)
			{
				found = true;
				if(!collab.getMovies().contains(m))
				{
					collab.getMovies().add(m);					
				}
				break;
			}
		}
		if (!found)
		{
			Collaboration collabTemp = new Collaboration(p1, p2);
			collabTemp.getMovies().add(m);
			p1.getCollabs().add(collabTemp);
			p2.getCollabs().add(collabTemp);
			collaborationList.add(collabTemp);
		}
	}



	public List<Collaboration> getCollaborationList() {
		return collaborationList;
	}

	
}
