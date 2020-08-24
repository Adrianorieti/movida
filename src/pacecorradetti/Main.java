package pacecorradetti;

import java.io.File;
import java.util.HashMap;

import movida.commons.*;

public class Main {

	public static void main(String[] args) throws Exception {
		LoadFromFile lff = new LoadFromFile();
		lff.load( new File("C:\\Users\\Andrea\\eclipse-workspace\\Algos\\src\\movida\\commons\\esempio-formato-dati.txt"));

		MovidaGraph g = new MovidaGraph(lff.getMovieMap(), lff.getPersonMap());
		
		for (Collaboration c : g.collaborationList)
		{
			System.out.println(c.getActorA() + " " + c.getActorA());
			System.out.println(c.getMovies().toString());
		}

	}
}
	
