package co.edu.javeriana.restaurante.persistencia;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import co.edu.javeriana.restaurante.negocio.IRestaurante;
import co.edu.javeriana.restaurante.negocio.Ingrediente;
import co.edu.javeriana.restaurante.negocio.IngredientePlato;
import co.edu.javeriana.restaurante.negocio.Plato;
import co.edu.javeriana.restaurante.negocio.PlatoCarta;
import co.edu.javeriana.restaurante.negocio.PlatoDiario;
import co.edu.javeriana.restaurante.negocio.Restaurante;


/**
 * @author Moreno - Lozano
 *
 */
public class ManejadorArchivos implements IRestaurante {

	/**Metodo que permita crear y escribir una cadena String en un archivo con nombre enviado
	 * por parametro
	 * @param texto El String que se desea guardar
	 * @param nomarch String que se usa para generar un archivo con este parametro como nombre
	 */
	public static void EscribirAArchivoTextoString(String texto, String nomarch){

		try{
			// Se crea el flujo de salida al archivo de nombre nomarch
			OutputStreamWriter out= new OutputStreamWriter(new FileOutputStream(nomarch));
			//Escribe una cadena en el flujo        	
			out.write(texto);
			out.close();
		}
		catch(Exception e){
			System.out.println("Ocurrio un error escribiendo a un archivo el reporte: " + e); 	
		}

	}
	/**
	 * Metodo que permita crear y escribir un Inmueble en un archivo con nombre enviado
	 * por parametro
	 * Se utiliza el metodo trim() de la api String que permite borrar espacios al inicio y al final de
	 * los Strings sobre los cuales el metodo sea llamado
	 * @param inm Inmueble que se desea guardar en el archivo
	 * @param nomarch String que se usa para generar un archivo con este parametro como nombre
	 */	
	public static void EscribirAArchivoTexto(Restaurante inm, String nomarch){

		try{
			// Se crea el flujo de salida al archivo de nombre nomarch
			OutputStreamWriter out= new OutputStreamWriter(new FileOutputStream(nomarch));
			//Escribe una cadena en el flujo        	
			out.write(inm.toString());
			out.close();
		}
		catch(Exception e){
			System.out.println("Ocurrio un error escribiendo a un archivo el reporte: " + e); 	
		}

	}
	/**
	 * Este metodo permite leer los ingredientes de un archivo, ademas crea los ingredientes con su respectiva informacion
	 * @param nomarchivo nombre del archivo
	 * @return lista de ingredientes
	 */
	public static List<Ingrediente> LeerIngredientes(String nomarchivo){

		List<Ingrediente> retorno = new ArrayList<Ingrediente>();
		int inmI=0;

		try{
			InputStreamReader input=new InputStreamReader(new FileInputStream(nomarchivo));

			BufferedReader fa=new BufferedReader(input);	
			String cad=fa.readLine();

			cad=fa.readLine();
			while (!cad.equalsIgnoreCase("0")){	
				//System.out.println(cad);

				Scanner del=new Scanner(cad).useDelimiter("\\*");
				//Se lee un token de tipo cadena
				int codigo=Integer.parseInt(new String(del.next()).trim());
				String nombre=new String(del.next()).trim();
				long precio=Long.parseLong(new String(del.next()).trim());
				String descripcion=new String(del.next()).trim();
				int inventario=Integer.parseInt(new String(del.next()).trim());
				int minimo=Integer.parseInt(new String(del.next()).trim());
				cad=fa.readLine(); 
				// Se usan los metodos de Manejador de Profesores. En este caso, se le adiciona una materia a un profesor
				//inm.adicionarMateriaAProfesor(nombre,materia,numEstudiantes,nota);
				
				Ingrediente ingre = Restaurante.crearIngrediente(codigo,nombre,precio,descripcion,inventario,minimo);
				retorno.add(ingre);
			}// Fin del while
		}
		catch (Exception e){
			System.out.println("Ocurrio un problema al cargar el archivo de texto " + e);
		}
		//Inmobiliaria in= new Inmobiliaria(inms,props);
		return retorno;
	}
	/**
	 * Este metodo permite leer los platos que se encuentran en un archivo, ademas crea los platos con su respectiva informacion
	 * ademas los guarda en una lista de plato
	 * @param nomarchivo nombre del archivo
	 * @return lista de platos
	 */
	public static Map<Integer,Plato> LeerPlatos(String nomarchivo){

		//List<Plato> retorno = new ArrayList<Plato>();
		Map<Integer ,Plato> retorno = new HashMap<Integer,Plato>();
		int inmI=0;

		try{
			InputStreamReader input=new InputStreamReader(new FileInputStream(nomarchivo));

			BufferedReader fa=new BufferedReader(input);	
			String cad=fa.readLine();

			while (!cad.equalsIgnoreCase("#FIN")){	
				//System.out.println(cad);
				if(cad.equalsIgnoreCase("#Plato")){
					List<IngredientePlato> LIngredientePlato = new ArrayList<IngredientePlato>();
					cad=fa.readLine(); 
					
					cad=fa.readLine();
					
					Scanner del=new Scanner(cad).useDelimiter("\\*");
					//Se lee un token de tipo cadena
					int codigo=Integer.parseInt(new String(del.next()).trim());
					String nombre=new String(del.next()).trim();
					//System.out.println(dir);
					String tipo=new String(del.next()).trim();
					String dia=null;
					if(tipo.equalsIgnoreCase("carta")){
						dia=new String(del.next()).trim();
					}
					cad=fa.readLine(); 
					long precioPlato = 0;
					if(cad.equalsIgnoreCase("#Ingredientes")){
						cad=fa.readLine();
						while(!cad.equalsIgnoreCase("0")){
							//System.out.println("Cadena: "+cad);
							Scanner del2=new Scanner(cad).useDelimiter("\\s+");
							int codigoIngre=Integer.parseInt(new String(del2.next()).trim());
							
							int cantIngre=Integer.parseInt(new String(del2.next()).trim());
							//System.out.println("Codigo: "+codigoIngre+" - Cantidad: "+cantIngre);
							precioPlato+=Restaurante.ObtenerPrecioIngrediente(codigoIngre, cantIngre);
							//System.out.println(Restaurante.ObtenerPrecioIngrediente(codigoIngre, cantIngre));
							
							IngredientePlato IngPla = Restaurante.crearIngredientePlato(codigoIngre, cantIngre);
							//System.out.println(IngPla);
							LIngredientePlato.add(IngPla);
							cad=fa.readLine();
						}
						//System.out.println(4);
					}
					//System.out.println(cad);
					precioPlato+=(precioPlato)*0.16;
					//------
					if(tipo.equalsIgnoreCase("carta")){
						precioPlato+=(precioPlato)*0.1;
						Plato pla = Restaurante.crearPlatoCar(codigo, nombre, (int)precioPlato, LIngredientePlato, tipo, dia);
						retorno.put(codigo, pla);
						}else{
							Plato pla = Restaurante.crearPlatoDia(codigo, nombre, (int)precioPlato, LIngredientePlato, tipo);
							retorno.put(codigo, pla);
						}
					//-----
					//Plato plato = Restaurante.crearPlato(codigo, nombre, (int)precioPlato, LIngredientePlato);
					//retorno.add(plato);
					cad=fa.readLine();
					
				}
			}// Fin del while
		}
		catch (Exception e){
			System.out.println("Ocurrio un problema al cargar el archivo de texto " + e);
		}
		//Inmobiliaria in= new Inmobiliaria(inms,props);
		return retorno;
	}



}
