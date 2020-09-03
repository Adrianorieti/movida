package pacecorradetti;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import movida.commons.MapImplementation;
import movida.commons.MovidaFileException;
import movida.commons.SortingAlgorithm;

public class ConfigManager {

	String Map = null;
	String Algorithm =null;
	String line = null;

	public ConfigManager(File f) throws FileNotFoundException {
		Scanner scan = new Scanner(f);
		line = scan.nextLine();
		Algorithm = formatLine(line);
		line = scan.nextLine();
		Map = formatLine(line);
		scan.close();
	}
	
	
	public MapImplementation getMap() throws MovidaFileException
	{
		if(Map.contains( "HashIndirizzamentoAperto"))
		{
			return MapImplementation.HashIndirizzamentoAperto;
		}
		else if(Map.contains("ArrayOrdinato"))
		{
			return MapImplementation.ArrayOrdinato;
		}
		else
			throw new MovidaFileException();
	}
	
	
	public SortingAlgorithm getAlgorithm()  throws MovidaFileException
	{
		if(Algorithm.contains("QuickSort") )
		{
			return SortingAlgorithm.QuickSort;
		}
		else if(Algorithm.contains("InsertionSort"))
		{
			return SortingAlgorithm.InsertionSort;
		}
		else
			throw new MovidaFileException();
	}
	
	
	public void overrideAlg(File file,String algo) throws IOException {
		Scanner scan = new Scanner(file);
			line = scan.nextLine();
			line = scan.nextLine();
			Map = formatLine(line);
		scan.close();
		FileWriter write = new FileWriter(file);
		write.append("sorting_algorithm=" + algo +"\n");
		write.append("map_implementation=" + Map);
		write.close();
	}
	
	
	public void overrideMap(File file,String map) throws IOException {
		Scanner scan = new Scanner(file);
		line = scan.nextLine();
		Algorithm = formatLine(line);
		scan.close();
		FileWriter write = new FileWriter(file);
		write.append("sorting_algorithm=" + Algorithm +"\n");
		write.append("map_implementation=" + map);
		write.close();
	}
	
	
	protected String formatLine(String line) {
		int index = line.indexOf('=');
		line = line.substring(index + 1, line.length());
		return line.trim();
	}
}
