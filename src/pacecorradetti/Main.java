package pacecorradetti;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import jdk.internal.reflect.ReflectionFactory.GetReflectionFactoryAction;
import movida.commons.*;

public class Main {

	public static void main(String[] args) throws Exception {
		LoadFromFile lff = new LoadFromFile();
		lff.load( new File("C:\\Users\\Andrea\\eclipse-workspace\\Algos\\src\\movida\\commons\\esempio-formato-dati.txt"));

		for(HashMap.Entry<String, Movie> entry : lff.movieMap.entrySet()) {
		    String key = entry.getKey();
		    Movie value = entry.getValue();
		    
		    System.out.println(
		    		key + "\n" +
		    		value.getCast().toString() + "\n" +
		    		value.getYear().toString() + "\n" +
		    		value.getDirector().getName());

		    // do what you have to do here
		    // In your case, another loop.
		}
	}
	
}
