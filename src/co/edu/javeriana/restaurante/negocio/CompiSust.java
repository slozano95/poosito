package co.edu.javeriana.restaurante.negocio;

import java.util.Comparator;
import java.util.Scanner;

public class CompiSust implements Comparator{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	
	public int compare(Object o1, Object o2) {
		
		Integer a1=(Integer)o1;
		Integer a2=(Integer)o2;
		
	
		
		return a1-a2;
	}
	

}
