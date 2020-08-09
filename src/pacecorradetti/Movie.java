/* 
 * Copyright (C) 2020 - Angelo Di Iorio
 * 
 * Progetto Movida.
 * Corso di Algoritmi e Strutture Dati
 * Laurea in Informatica, UniBO, a.a. 2019/2020
 * 
*/
package pacecorradetti;

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
	
	
}
