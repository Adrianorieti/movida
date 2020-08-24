package movida.commons;

import java.util.ArrayList;

public class Collaboration {

	Person actorA;
	Person actorB;
	ArrayList<Movie> movies;
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public Collaboration(Person actorA, Person actorB) {
		this.actorA = actorA;
		this.actorB = actorB;
		this.movies = new ArrayList<Movie>();
	}

	public Person getActorA() {
		return actorA;
	}

	public Person getActorB() {
		return actorB;
	}

	public Double getScore(){
		
		Double score = 0.0;
		
		for (Movie m : movies)
			score += m.getVotes();
		
		return score / movies.size();
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Collaboration [actorA=");
		builder.append(actorA);
		builder.append(", actorB=");
		builder.append(actorB);
		builder.append(", movies=");
		builder.append(movies);
		builder.append(", getScore()=");
		builder.append(getScore());
		builder.append("]");
		return builder.toString();
	}
	
}
