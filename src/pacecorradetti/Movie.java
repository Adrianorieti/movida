/* 
 * Copyright (C) 2020 - Angelo Di Iorio
 * 
 * Progetto Movida.
 * Corso di Algoritmi e Strutture Dati
 * Laurea in Informatica, UniBO, a.a. 2019/2020
 * 
*/
package pacecorradetti;

import java.util.Arrays;

/**
 * La classe estesa cambia il tipo degli attori utilizzando pacecorradetti.Person invece
 * che movida.commons.Person
 */
public class Movie extends movida.commons.Movie{
	
	private pacecorradetti.Person[] cast;
	private pacecorradetti.Person director;
	
	public Movie(String title, Integer year, Integer votes,
			Person[] cast, Person director) {
		super (title, year, votes, null, null);
		this.cast = cast;
		this.director = director;
	}

	
	public Person[] getCast() {
		return cast;
	}

	public Person getDirector() {
		return director;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("getTitle()=");
		builder.append(getTitle());
		builder.append(", getDirector()=");
		builder.append(getDirector());
		builder.append(", getYear()=");
		builder.append(getYear());
		builder.append(", getVotes()=");
		builder.append(getVotes());
		builder.append(", Movie [cast=");
		builder.append(Arrays.toString(cast));
		builder.append(", getCast()=");
		builder.append(Arrays.toString(getCast()));
		builder.append("]");
		return builder.toString();
	}
	
	/*
	 * public String toString() { //TODO write to string body StringBuilder sb = new
	 * StringBuilder(); sb.append("Title: " + title); };
	 */
	
	
}
