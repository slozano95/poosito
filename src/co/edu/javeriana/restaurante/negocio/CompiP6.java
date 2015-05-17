package co.edu.javeriana.restaurante.negocio;

import java.util.Comparator;
import java.util.Scanner;

public class CompiP6 implements Comparator{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	
	public int compare(Object o1, Object o2) {
		
		String a1=(String)o1;
		String a2=(String)o2;
		
		Scanner del1=new Scanner(a1).useDelimiter(",");
		int codigo1=Integer.parseInt(new String(del1.next()).trim());
		int cantidad1=Integer.parseInt(new String(del1.next()).trim());
		int precio1=Integer.parseInt(new String(del1.next()).trim());
		
		Scanner del2=new Scanner(a2).useDelimiter(",");
		int codigo2=Integer.parseInt(new String(del2.next()).trim());
		int cantidad2=Integer.parseInt(new String(del2.next()).trim());
		int precio2=Integer.parseInt(new String(del2.next()).trim());
		
		return precio1-precio2;
	}
	

}
