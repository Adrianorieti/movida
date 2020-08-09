package pacecorradetti;

import java.util.ArrayList;

public class Collaboration extends movida.commons.Collaboration {

	Person actorA;
	Person actorB;
	ArrayList<Movie> movies;
	
	public Collaboration(Person actorA, Person actorB) {
		super (actorA, actorB);
	}

	public Person getActorA() {
		return actorA;
	}

	public Person getActorB() {
		return actorB;
	}

	
}
