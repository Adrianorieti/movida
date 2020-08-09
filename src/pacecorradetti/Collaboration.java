package pacecorradetti;

import java.util.ArrayList;

/**
 * La classe estesa cambia il tipo degli attori utilizzando pacecorradetti.Person invece
 * che movida.commons.Person
 */

public class Collaboration extends movida.commons.Collaboration {

	Person actorA;
	Person actorB;
	ArrayList<Movie> movies;
	
	public Collaboration(Person actorA, Person actorB) {
		super (null, null);
		this.actorA = actorA;
		this.actorB = actorB;
		this.movies = new ArrayList<Movie>();
	}

	public Person getActorA() {
		return this.actorA;
	}

	public Person getActorB() {
		return this.actorB;
	}

	
}
