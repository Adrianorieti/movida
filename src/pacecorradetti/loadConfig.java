package pacecorradetti;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import movida.commons.MapImplementation;
import movida.commons.MovidaFileException;
import movida.commons.SortingAlgorithm;

public class loadConfig {

	private String Map = null;
	private String Algorithm = null;

	public loadConfig(File f)throws MovidaFileException, FileNotFoundException {
		
		Scanner scan = new Scanner(f);
	
		
		
		while (scan.hasNextLine()) {
			
			Map = scan.nextLine();
			Algorithm = scan.nextLine();
			
		}
		scan.close();
	}
	
	public MapImplementation getMap() throws MovidaFileException
	{
		if(Map == "HashIndirizzamentoAperto")
		{
			return MapImplementation.HashIndirizzamentoAperto;
		}
		else
			throw new MovidaFileException();
	}
	public SortingAlgorithm getAlgorithm()  throws MovidaFileException
	{
		if(Algorithm == "QuickSort")
		{
			return SortingAlgorithm.QuickSort;
		}
		else
			throw new MovidaFileException();
	}
	
 
}
