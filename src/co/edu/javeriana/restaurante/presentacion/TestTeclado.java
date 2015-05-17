/**
 * 
 */
package co.edu.javeriana.restaurante.presentacion;



import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import co.edu.javeriana.restaurante.negocio.CompiP5;
import co.edu.javeriana.restaurante.negocio.CompiP6;
import co.edu.javeriana.restaurante.negocio.IRestaurante;
import co.edu.javeriana.restaurante.negocio.Ingrediente;
import co.edu.javeriana.restaurante.negocio.Orden;
import co.edu.javeriana.restaurante.negocio.OrdenPlato;
import co.edu.javeriana.restaurante.negocio.Plato;
import co.edu.javeriana.restaurante.negocio.PlatoCarta;
import co.edu.javeriana.restaurante.negocio.PlatoDiario;
import co.edu.javeriana.restaurante.negocio.Restaurante;
import co.edu.javeriana.restaurante.persistencia.ManejadorArchivos;

/**
 * @author Santiago_Paula
 *
 */
public class TestTeclado implements IRestaurante{
	public static IRestaurante rest = (IRestaurante) new Restaurante();
	public static void main(String[] args) {
		
		List<Ingrediente> LIngredientes = ((Restaurante) rest).getLIngredientes();
		Map<Integer,Plato> LPlatos = ((Restaurante) rest).getLPlatos();
		List<Orden> LOrdenes = ((Restaurante) rest).getLOrdenes();
		Map <Integer, String> dias= new HashMap<Integer, String>();
		dias.put(1, "do");
		dias.put(2, "lu");
		dias.put(3, "ma");
		dias.put(4, "mi");
		dias.put(5, "ju");
		dias.put(6, "vi");
		dias.put(7, "sa");
		
		//List<OrdenPlato> temp = new ArrayList<OrdenPlato>();
		List<String> cuenta = new ArrayList<String>();
		
		List<Integer> laux=new ArrayList<Integer>();
		
		// TODO Auto-generated method stub
		String op = null;
		//int opcion;
		
		String entradaTeclado = null;
		int opcionMenu = 0;
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		do{
			System.out.println("\n\n"
					+"---------------------------------------------------------\n"
					+"|                                                       |\n"
					+"|                    RESTAURANTE LOMO                   |\n"
					+"|                                                       |\n"
					+"---------------------------------------------------------\n"
					+"|                                                       |\n"
					+"|  Menu Principal:                                      |\n"
					+"|     1). Agregar un ingrediente nuevo al sistema.      |\n"
					+"|     2). Agregar un plato nuevo al sistema.            |\n"
					+"|     3). Mostrar el menu del restaurante.              |\n"
					+"|     4). Agregar una orden al sistema.                 |\n"
					+"|     5). Mostrar los 3 platos mas solicitados.         |\n"
					+"|     6). Mostrar los 3 platos mas rentables.           |\n"
					+"|     7). Totalizar las ordenes de un dia.              |\n"
					+"|     8). Guardar el sistema.                           |\n"
					+"|     9). Cargar el sistema.                            |\n"
					+"|     10). Sustentacion 1.                              |\n"
					+"|     11). Salir                                        |\n"
					+"|                                                       |\n"
					+"---------------------------------------------------------\n"
					+"\nDigite el numero de opcion: "
					);
			try {
				entradaTeclado = bf.readLine();
				opcionMenu = Integer.parseInt(entradaTeclado);
				switch(opcionMenu){
				case 1:
					System.out.println("Ingrese Nombre Archivo Lectura: ");
					entradaTeclado=bf.readLine();
					System.out.println();
					((Restaurante) rest).setLIngredientes(ManejadorArchivos.LeerIngredientes("./"+entradaTeclado));
					
					//System.out.println(((Restaurante) rest).impLIngrediente());
					System.out.println("----------------------------------------------");
					System.out.printf ("|        Se han cargado %s ingredientes      |\n",((Restaurante) rest).getLIngredientes().size());
					System.out.println("----------------------------------------------");
					break;
				case 2:
					System.out.println("Ingrese Nombre Archivo Lectura: ");
					entradaTeclado=bf.readLine();
					System.out.println();
					((Restaurante) rest).setLPlatos((HashMap<Integer, Plato>) ManejadorArchivos.LeerPlatos("./"+entradaTeclado));
					
					//System.out.println(((Restaurante) rest).impLPlatos());
					System.out.println("----------------------------------------------");
					System.out.printf ("|           Se han cargado %s platos         |\n",((Restaurante) rest).getLPlatos().size());
					System.out.println("----------------------------------------------");
					break;
					
				case 3:
					System.out.println("\n--MENU DEL RESTAURANTE:\n--PLATOS OFRECIDOS\n");
					System.out.printf("%-10s %-30s %-20s  %-20s %-20s\n------------------------------------------------------------------------------------------------\n","codigo","nombre","tipo","dia","precio");
					System.out.println(((Restaurante) rest).impPlatos());
					break;

				case 4:
					Map <Integer, Integer> ordensita= new HashMap<Integer, Integer>();
					System.out.println("Numero de Platos: ");
					int numplatos=Integer.parseInt(bf.readLine());
					int cont=0;
					long val = 0;
					int numOrd=0;
					boolean estado;
					boolean fecha= false;
					//List<String>pedido =new ArrayList<String>();
					System.out.println("Ingrese sus platos (codigo cantidad(de unidades))");
					//Orden or = ((Restaurante) rest).crearOrden();

					do{
						cont++;
						entradaTeclado=bf.readLine();
						Scanner del=new Scanner(entradaTeclado).useDelimiter("\\ ");
						String cod=new String(del.next()).trim();
						String cant=new String(del.next()).trim();
						int codi= Integer.parseInt(cod);
						int canti= Integer.parseInt(cant);
						Plato valor=(Plato) ((Restaurante)rest).getLPlatos().get(codi);
						Plato pl= ((Restaurante) rest).buscarPlato(codi);
						//EN CONSTRUCCION
						if(!ordensita.containsKey(codi)){
						ordensita.put(codi, canti);
						}else{
							ordensita.put(codi, ordensita.get(codi)+canti);
						}
						estado= ((Restaurante) rest).verificarIng(codi, canti, ordensita);
						//System.out.println(estado);
						if(estado== false){
							break;
						}
						if(valor != null){
							if(pl instanceof PlatoCarta){
								fecha=false;
								Calendar c1 = GregorianCalendar.getInstance();
								//System.out.println("Fecha actual: "+c1.getTime().toLocaleString());
								GregorianCalendar cal = new GregorianCalendar();
								cal.setTime(c1.getTime());
								//System.out.println("dia: "+cal.get(Calendar.DAY_OF_WEEK));
								if(((PlatoCarta )pl).getDia().equalsIgnoreCase(dias.get(cal.get(Calendar.DAY_OF_WEEK)))){
									//System.out.println("se puede comer hoy");
									fecha=true;
								}else{
									fecha=false;
									System.out.println("----------------------------------------------");
									System.out.println("|       Este plato no esta disponible hoy    |");
									System.out.println("----------------------------------------------");
									//System.out.println("Este plato no esta disponible hoy");
									break;
								}
							}else{
								fecha= true;
							}
						}
					}while(cont<numplatos);

					cont=0;
					if(estado == true && fecha == true){
						Iterator it = ordensita.keySet().iterator();
						while(it.hasNext()){
							cont++;
							Integer key = (Integer) it.next();
							//System.out.println("Clave: " + key + " -> Valor: " + ordensita.get(key));
							Integer canti = ordensita.get(key);
							estado= ((Restaurante) rest).descontar(key, canti);
							Plato pl= ((Restaurante) rest).buscarPlato(key);
							LPlatos= ((Restaurante)rest).getLPlatos();
							int precio = (int) ((Restaurante) rest).ObtenerPrecioPlato(key);
							cuenta=existe(key,cuenta,canti,precio);
							val = ((Restaurante) rest).ObtenerPrecioPlato(key) *canti;
							if(cont==1){
								numOrd= ((Restaurante) rest).agregarOrden(key, canti, pl, val);
							}else{
								((Restaurante) rest).agregarOPlato(numOrd, key, canti, pl, val);
							}
						}
						System.out.println("----------------------------------------------");
						System.out.println("|           La Orden ha sido generada        |");
						System.out.println("----------------------------------------------");
					}else{
						//System.out.println("No se puede completar orden")
						System.out.println("----------------------------------------------");
						System.out.println("|         No se puede completar orden        |");
						System.out.println("----------------------------------------------");
					}

					//						if(valor != null){
					//							if(pl instanceof PlatoCarta){
					//								Calendar c1 = GregorianCalendar.getInstance();
					//								System.out.println("Fecha actual: "+c1.getTime().toLocaleString());
					//								GregorianCalendar cal = new GregorianCalendar();
					//								cal.setTime(c1.getTime());
					//								System.out.println("dia: "+cal.get(Calendar.DAY_OF_WEEK));
					//
					//								if(((PlatoCarta )pl).getDia().equalsIgnoreCase(dias.get(cal.get(Calendar.DAY_OF_WEEK)))){
					//									System.out.println("se puede comer hoy");
					//									System.out.println(pl);
					//									System.out.println(" ");
					//									fecha=true;
					//								}
					//							}else{
					//								fecha= true;
					//							}
					//							estado= ((Restaurante) rest).descontar(codi, canti);
					//							if(estado == true && fecha == true){
					//								int precio = (int) ((Restaurante) rest).ObtenerPrecioPlato(codi);
					//								cuenta=existe(codi,cuenta,canti,precio);
					//								//or.crearOrdenPlato(codi, canti, pl);
					//								val = ((Restaurante) rest).ObtenerPrecioPlato(codi) *canti;
					//								
					//								if(cont==1){
					//									//System.out.println("1");
					//									numOrd= ((Restaurante) rest).agregarOrden(codi, canti, pl, val);
					//								}
					//								else{
					//									//System.out.println("2");
					//									((Restaurante) rest).agregarOPlato(numOrd, codi, canti, pl, val);
					//								}
					//								
					//							}else{
					//								System.out.println("No se puede completar orden, escasos recursos");
					//							}
					//						}else{
					//							System.out.println("No existe el plato");
					//						}


					/**/

					break;

				case 5:
					System.out.println("---PLATOS MAS SOLICITADOS---");
					//List<OrdenPlato> temp = new ArrayList<OrdenPlato>();	
					Collections.sort(cuenta, new CompiP5());
					Collections.reverse(cuenta);
					int contador=0;
					Iterator it=cuenta.iterator();
					System.out.printf("%-10s %-20s %-20s %-20s\n","codigo","nombre","tipo","solicitudes");
					while(it.hasNext()){
						String pedido= (String) it.next();
						Scanner del=new Scanner(pedido).useDelimiter(",");
						int codigo=Integer.parseInt(new String(del.next()).trim());
						int cantidad=Integer.parseInt(new String(del.next()).trim());
						Plato plat = ((Restaurante) rest).buscarPlato(codigo);
						String nom =plat.getNombre();
						if(contador<3){
							if(plat instanceof PlatoCarta){
								System.out.printf("%-10s %-30s %-20s %-20s\n",codigo,nom,"Carta",cantidad);
							}
							else{
								System.out.printf("%-10s %-20s %-20s %-20s\n",codigo,nom,"Diario",cantidad);
							}
							
						//	System.out.println(codigo + " "+ nom+ " "+ cantidad);
							contador++;
						}
					}
					break;
				case 6:
					System.out.println("---PLATOS MAS RENTABLES---");
					//List<OrdenPlato> temp = new ArrayList<OrdenPlato>();
					Collections.sort(cuenta, new CompiP6());
					Collections.reverse(cuenta);
					int conta=0;
					long precio=0;	
					Iterator ite=cuenta.iterator();
					System.out.printf("%-10s %-20s %-20s %-20s\n","codigo","nombre","dia","total de ventas");
					while(ite.hasNext()){
						String pedido= (String) ite.next();
						Scanner del=new Scanner(pedido).useDelimiter(",");
						int codigo=Integer.parseInt(new String(del.next()).trim());
						int cantidad=Integer.parseInt(new String(del.next()).trim());
						int prec=Integer.parseInt(new String(del.next()).trim());
						Plato plat = ((Restaurante) rest).buscarPlato(codigo);
						String nom =plat.getNombre();
						precio = ((Restaurante) rest).ObtenerPrecioPlato(codigo);
						if(conta<3){
							//System.out.printf("%-10s %-20s %-20s\n",codigo,nom,prec);
							String diaimp=new String("");
							if(plat instanceof PlatoCarta){
								switch(((PlatoCarta)plat).getDia()){
								case "lu":
									diaimp="Lunes";
									break;
								case "ma":
									diaimp="Martes";
									break;
								case "mi":
									diaimp="Miercoles";
									break;
								case "ju":
									diaimp="Jueves";
									break;
								case "vi":
									diaimp="Viernes";
									break;
								case "sa":
									diaimp="Sabado";
									break;
								case "do":
									diaimp="Doming";
									break;
								default:
									diaimp="Dia No Disponible";
									break;
								}
								System.out.printf("%-10s %-30s %-20s %-20s\n",codigo,nom,diaimp,cantidad);
							}
							else{
								System.out.printf("%-10s %-20s %-20s %-20s\n",codigo,nom,"",cantidad);
							}
						//System.out.println(codigo + " "+ nom+ " "+ p2);
						conta++;
						}
					}
					break;
				case 7:
					String cadena;
					System.out.println("-- TOTAL DE VENTAS DE UN DIA --");
					System.out.println("Ingrese la fecha a consultar (AAAA-MM-DD): ");
					cadena = bf.readLine();
					long vfinal=0;
					System.out.println("Codigo\t\tValor");
					Iterator<Orden> ita=LOrdenes.iterator();
					while(ita.hasNext()){
						Orden or= (Orden) ita.next();
						
						GregorianCalendar gc1=or.getFecha();
						GregorianCalendar gc2=Utils.convertirCadenaFecha(cadena);
						
						if((gc1.get(Calendar.YEAR)==gc2.get(Calendar.YEAR)) &&
								(gc1.get(Calendar.MONTH)==gc2.get(Calendar.MONTH))
								&& (gc1.get(Calendar.DAY_OF_MONTH)==gc2.get(Calendar.DAY_OF_MONTH))){
							vfinal+=or.getValor();
							System.out.println(or.getCodigo()+"\t\t"+or.getValor());
							
//						}
//						System.out.println(gc1);
//						System.out.println(gc2);
//						if(or.getFecha().compareTo(Utils.convertirCadenaFecha(cadena))==0){
							//System.out.println("syso sirvio santiago me debe un milo");
							
							
						}
						
					}
					System.out.println("Valor Total: "+vfinal);
					break;
				case 8:
					System.out.println("Ingrese Nombre Archivo Salida: ");
					op=bf.readLine();
					FileOutputStream out = new FileOutputStream("./"+op);
					ObjectOutputStream sale = new ObjectOutputStream(out);
					//sale.writeObject(rest);
//					System.out.println(rest);
//					System.out.println(((Restaurante) rest).getLIngredientes().size());
//					System.out.println(((Restaurante) rest).getLPlatos().size());
//					System.out.println(LOrdenes.size());
					
					sale.writeObject(((Restaurante) rest).getLIngredientes());
					sale.writeObject(((Restaurante) rest).getLPlatos());
					sale.writeObject(LOrdenes);
//					sale.writeObject(temp);
					sale.writeObject(cuenta);
					out.close();
					
					System.out.println("----------------------------------------------");
					System.out.printf ("|           Guardando %s Ingredientes        |\n",((Restaurante) rest).getLIngredientes().size());
					System.out.printf ("|              Guardando %s Platos           |\n",((Restaurante) rest).getLPlatos().size());
					System.out.printf ("|             Guardando %s Ordenes           |\n",LOrdenes.size());
					System.out.println("----------------------------------------------\n\n");
					
					System.out.println("----------------------------------------------");
					System.out.printf ("|        Archivo Guardado Correctamente      |\n");
					System.out.println("----------------------------------------------");
					break;
				case 9:		
					System.out.println("Ingrese Nombre Archivo Lectura: ");
					op=bf.readLine();
					FileInputStream in = new FileInputStream("./"+op);
					ObjectInputStream inl = new ObjectInputStream(in);
					try {
						//rest=(Restaurante)inl.readObject();�
						List<Ingrediente>l1=(List<Ingrediente>)inl.readObject();
						Map<Integer, Plato>l2= (Map<Integer, Plato>) inl.readObject();
						List<Orden>l3=(List<Orden>)inl.readObject();
						
						((Restaurante) rest).setLIngredientes(l1);
						((Restaurante) rest).setLPlatos((HashMap<Integer, Plato>) l2);
						((Restaurante) rest).setLOrdenes(l3);
						LOrdenes=l3;
						((Restaurante) rest).cambiarConse(l3.size()+1);
						//System.out.println(rest);
//						
////						LIngredientes=
////						LPlatos=(List<Plato>)inl.readObject();
////						LOrdenes=(List<Orden>)inl.readObject();
//						temp=(List<OrdenPlato>)inl.readObject();						
						cuenta=(List<String>)inl.readObject();

						System.out.println("----------------------------------------------");
						System.out.printf ("|            Cargando %s Ingredientes        |\n",l1.size());
						System.out.printf ("|               Cargando %s Platos           |\n",l2.size());
						System.out.printf ("|              Cargando %s Ordenes           |\n",l3.size());
						System.out.println("----------------------------------------------\n\n");
						
						System.out.println("----------------------------------------------");
						System.out.printf ("|        Archivo Cargado Correctamente      |\n");
						System.out.println("----------------------------------------------");
						
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						System.out.println(e);
					}
					in.close();
					break;
				case 10:
					System.out.println("Ingrese Nombre Ingrediente: ");
					String nomi=bf.readLine();
					System.out.println("Ingrese Fecha (AAAA-MM-DD): ");
					String fec=bf.readLine();
					String sal=((Restaurante) rest).sus(nomi,fec);
					System.out.println(sal);
					
					ManejadorArchivos.EscribirAArchivoTextoString(sal, "./reportePlatoIngrediente.txt");
					break;
				case 11:
					System.out.println("Ingrese Nombre Ingrediente: ");
					String nomIng=bf.readLine();
					System.out.println("Ingrese Dia (lu,ma,mi,ju,vi,sa,do) ");
					String diasem=bf.readLine();
					String sal1=(((Restaurante) rest).sus2(nomIng,diasem));
					String sal2=(((Restaurante) rest).sus3(nomIng,diasem));
					ManejadorArchivos.EscribirAArchivoTextoString(sal1, "./reportePlatoDiario.txt");
					ManejadorArchivos.EscribirAArchivoTextoString(sal2, "./reportePlatoCarta.txt");
					System.out.println(sal1);
					System.out.println(sal2);
					break;
				case 12:
					System.out.println("\n\n----------------------------------------------");
					System.out.printf ("|                                            |\n");
					System.out.printf ("|      PONTIFICIA UNIVERSIDAD JAVERIANA      |\n");
					System.out.printf ("|      PROGRAMACION ORIENTADA A OBJETOS      |\n");
					System.out.printf ("|         PRIMER PROYECTO DE SEMESTRE        |\n");
					System.out.printf ("|                                            |\n");
					System.out.printf ("|         MARIA PAULA MORENO GUTIERREZ       |\n");
					System.out.printf ("|           SANTIAGO LOZANO RODRIGUEZ        |\n");
					System.out.printf ("|                MARZO 23 DE 2015            |\n");
					System.out.printf ("|                                            |\n");
					System.out.println("----------------------------------------------\n\n");
					break;
				case 99:
					System.out.println("Lista de Ordenes:");
					for(Orden o:LOrdenes){
						System.out.println(o);
					}
					break;
				case 100:
					for(Ingrediente i:((Restaurante) rest).getLIngredientes()){
						System.out.println(i+" INV: "+i.getInventario());
					}
				default:
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//pausar();	
		}while(!entradaTeclado.equals("12"));
	}
	/**
	 * Metodo que permite buscar un codigo de plato para llevar un control sobre su cantidad y costo
	 * @param cod Codigo del plato buscado
	 * @param lista Lista de String donde se llevan los registros
	 * @param cant Cantidad de platos a ingresar
	 * @param prec Precio a definir para el plato
	 * @return Lista de String con los datos
	 */
	public static List<String> existe(int cod,List<String> lista,int cant,int prec){

		int cont=0;
		boolean enc=false;
		for(String an: lista){
			Scanner del=new Scanner(an).useDelimiter(",");
			int codigo=Integer.parseInt(new String(del.next()).trim());
			int cantidad=Integer.parseInt(new String(del.next()).trim());
			int precio=Integer.parseInt(new String(del.next()).trim());
			if(codigo==cod){
				int n=cant+cantidad;
				String nuevo =new String(codigo+","+n+","+precio*n);
				lista.remove(an);
				an=codigo+","+n;
				lista.add(nuevo);
				//System.out.println(lista);
				enc=true;
				break;
			}
			cont++;
		}
		if(!enc){
			String nuevo =new String(cod+","+cant+","+prec*cant);
			lista.add(nuevo);
		}
		return lista;
	}
	/**
	 * Metodo que permite implementar una pausa para que el usuario pueda leer los mensajes en pantalla e ingresa
	 * varios saltos de linea para dar el efecto visual de limpieza de pantalla
	 */
	public static void pausar(){
		System.out.println("\nPresione ENTER para continuar.");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		try {
			bf.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		}

}
