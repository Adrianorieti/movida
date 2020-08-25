package pacecorradetti;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import movida.commons.MapImplementation;
import movida.commons.MovidaFileException;
import movida.commons.SortingAlgorithm;

public class loadConfig {

	String Map = null;
	String Algorithm =null;
	String line = null;

	public loadConfig(File f) throws MovidaFileException, FileNotFoundException {
		
		

		Scanner scan = new Scanner(f);
		while (scan.hasNextLine()) 
		{
			
			line = scan.nextLine();
			Algorithm = formatLine(line);
			line = scan.nextLine();
			Map = formatLine(line);
			
		}
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
	
	protected String formatLine(String line) {
		int index = line.indexOf('=');
		line = line.substring(index + 1, line.length());
		return line.trim();
	}
}
