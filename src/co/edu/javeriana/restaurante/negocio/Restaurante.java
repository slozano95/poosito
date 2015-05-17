/**
 * 
 */
package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import co.edu.javeriana.restaurante.presentacion.Utils;

/**
 * @author Santiago_Paula
 *
 */
public class Restaurante implements Serializable, IRestaurante{
	private static List<Ingrediente> LIngredientes;
	private static Map<Integer,Plato> LPlatos;
	private static List<Orden> LOrdenes;

	/**
	 * Constructor de la clase Restaurante por defecto
	 */
	public Restaurante(){

		LIngredientes = new ArrayList<Ingrediente>();
		LPlatos = new HashMap<Integer,Plato>();
		LOrdenes = new ArrayList<Orden>();

	}
	/** metodo de la sustentacion 1
	 * @param nom nombre del plato
	 * @param fec fecha que se busca
	 * @return texto a mostrar al usuario
	 */
	public String sus(String nom, String fec)
	{
		Ingrediente instatico = null;
		IngredientePlato iplato = null;
		String salida=String.format("--REPORTE DE PLATOS E INGREDIENTES\nFecha: %s\nIngrediente: %s",fec,nom);
		salida+="\n\n#PlatoCodigo---PlatoNombre---PlatoPrecio---IngredientePrecio---OrdenCodigo---CantidadIngrediente---PorcentajePrecioIngrediente\n";
		List<Integer> cods=new ArrayList<Integer>();
		List<Orden> ords= new ArrayList<Orden>();
		for(Orden o:LOrdenes){
			if(o.getFecha().compareTo(Utils.convertirCadenaFecha(fec))>0){
				//System.out.println("w");
				for(OrdenPlato orpl:o.getLOrdenPlato()){
					Plato auxplato=orpl.getPOrden();

					for(IngredientePlato ingreplato:auxplato.getLIngredientePlato()){
						Ingrediente ingre=ingreplato.getIngrediente();
						iplato=ingreplato;
						//System.out.println(ingre);
						if(ingre.getNombre().equalsIgnoreCase(nom)){
							instatico=ingre;
							//float val=((float)ingre.getPrecioUnitario()*100)/auxplato.getPrecio();
							//salida+=String.format("%s %-8s %-20s %-15s %-15s %-20s %-20s %-20.2f \n",auxplato.getCodigo(),"",auxplato.getNombre(),auxplato.getPrecio(),ingre.getPrecioUnitario(),o.getCodigo(),ingreplato.getCantidad(),val);
							//System.out.println(auxplato.getNombre());
							cods.add(auxplato.getCodigo());
							ords.add(o);
						}
					}

				}
			}
		}
		
		for(Integer i: cods){
			Plato p=buscarPlato(i);
			float val=((float)instatico.getPrecioUnitario()*100)/p.getPrecio();
			salida+=String.format("%s %-8s %-20s %-15s %-15s %-20s %-20s %-20.2f \n",p.getCodigo(),"",p.getNombre(),p.getPrecio(),instatico.getPrecioUnitario(),buscarOrdenCod(p.getNombre()),iplato.getCantidad(),val);
			
			//System.out.println(auxplato.getNombre());
		}
		
		return salida;
	}
	public String sus2(String ingrebuscado,String dia){
		List<PlatoDiario> laux= new ArrayList<PlatoDiario>();
		String s="\n";
		String retorno="--Reporte PlatoDiario"+s;
		retorno+="Ingrediente: "+ingrebuscado+s;
		retorno+="Dia: "+dia+s;
		int quedan=0;
		for(Ingrediente i:LIngredientes){
			if(i.getNombre().equalsIgnoreCase(ingrebuscado)){
				quedan=i.getInventario();
				retorno+="Inventario: "+quedan+s;
				break;
			}
		}
		
		retorno+="\n";
		retorno+="#PlatoOrdenado"+s;
			
		for(Orden ord:LOrdenes){
			for(OrdenPlato op: ord.getLOrdenPlato()){
				Plato plat = op.getPOrden();
				if(plat instanceof PlatoDiario){
					for(IngredientePlato ip:plat.getLIngredientePlato()){
						Ingrediente ingre = ip.getIngrediente();
						if(ingre.getNombre().equalsIgnoreCase(ingrebuscado)){
							laux.add((PlatoDiario) plat);
							retorno+=plat.getNombre()+s;
						}
					}
				}
			}
		}
		if(laux.size()==0){
			retorno="--Reporte PlatoDiario"+s;
			retorno+="Ingrediente: "+ingrebuscado+s;
			retorno+="Dia: "+dia+s;
			retorno+="No hay coincidencia para los datos"+s;
			return retorno;
		}
		int necesita=0;
		retorno+=s+"#Plato-----------------------------#CantidadPreparar"+s;
		for(PlatoDiario p:laux){
			for(IngredientePlato ip:p.getLIngredientePlato()){
				Ingrediente ingre = ip.getIngrediente();
				if(ingre.getNombre().equalsIgnoreCase(ingrebuscado)){
					necesita=ip.getCantidad();
				}
			}
			retorno+=p.getNombre()+"\t\t"+(int)(quedan/necesita)+s;
		}
		return retorno;
	}
	
	public String sus3(String ingrebuscado,String dia){
		List<PlatoCarta> laux= new ArrayList<PlatoCarta>();
		String s="\n";
		String retorno="--Reporte PlatoCarta"+s;
		retorno+="Ingrediente: "+ingrebuscado+s;
		retorno+="Dia: "+dia+s;
		int quedan=0;
		for(Ingrediente i:LIngredientes){
			if(i.getNombre().equalsIgnoreCase(ingrebuscado)){
				quedan=i.getInventario();
				retorno+="Inventario: "+quedan+s;
				break;
			}
		}
		
		retorno+="\n";
		retorno+="#PlatoOrdenado"+s;
			
		for(Orden ord:LOrdenes){
			for(OrdenPlato op: ord.getLOrdenPlato()){
				Plato plat = op.getPOrden();
				if(plat instanceof PlatoCarta){
					if(((PlatoCarta) plat).getDia().equalsIgnoreCase(dia)){
					
					for(IngredientePlato ip:plat.getLIngredientePlato()){
						Ingrediente ingre = ip.getIngrediente();
						if(ingre.getNombre().equalsIgnoreCase(ingrebuscado)){
							laux.add((PlatoCarta)plat);
							retorno+=plat.getNombre()+s;
						}
					}
				}
				}
				}
				
		}
		if(laux.size()==0){
			retorno="--Reporte PlatoCarta"+s;
			retorno+="Ingrediente: "+ingrebuscado+s;
			retorno+="Dia: "+dia+s;
			retorno+="No hay coincidencia para los datos"+s;
			return retorno;
		}
		int necesita=0;
		retorno+=s+"#Plato-----------------------------#CantidadPreparar"+s;
		for(PlatoCarta p:laux){
			for(IngredientePlato ip:p.getLIngredientePlato()){
				Ingrediente ingre = ip.getIngrediente();
				if(ingre.getNombre().equalsIgnoreCase(ingrebuscado)){
					necesita=ip.getCantidad();
				}
			}
			retorno+=p.getNombre()+"\t\t"+(int)(quedan/necesita)+s;
		}
		return retorno;
	}
	/** Permite buscar un plato en una orden 
	 * @param nomP nombre del plato
	 * @return codigo del plato
	 */
	public int buscarOrdenCod(String nomP){
		Iterator it = LOrdenes.iterator();
		while(it.hasNext()){
			Orden o= (Orden)it.next(); 
			for(OrdenPlato op:o.getLOrdenPlato()){
				if(op.getPOrden().getNombre().equalsIgnoreCase(nomP)){
					return o.getCodigo();
				}
			}
		}
		return -1;
	}
	
	/**

	 * Este metodo permite obtener el precio de un Ingrediente deacuerdo al precio unitario y la cantidad 
	 * @param codigoIngre  codigo con el cual esta registrado el ingrediente
	 * @param cantidad cantidad que se necesita del ingredient
	 * @return precio de tipo long
	 */
	public static long ObtenerPrecioIngrediente(int codigoIngre,int cantidad){
		long precio=0;

		java.util.Iterator<Ingrediente> iterador=LIngredientes.iterator();
		while(iterador.hasNext()){
			Ingrediente ingre=iterador.next();
			//System.out.println("Comparando "+ingre.getCodigo()+" con "+codigoIngre);
			if(ingre.getCodigo()==codigoIngre){
				precio = (cantidad * ingre.getPrecioUnitario());
			}
		}
		return precio;
	}
	/**
	 * Este metodo permite crear un Ingrediente, se usa asignacion de responsabilidades por lo tanto se llama al constructor de Ingrediente
	 * @param cod codigo del Ingrediente
	 * @param nom nombre del Ingrediente
	 * @param pre precio del Ingrediente
	 * @param desc descripcion del Ingrediente
	 * @param inv inventario del Ingrediente
	 * @param min minimo del Ingrediente
	 * @return nuevo Ingrediente
	 */
	public static Ingrediente crearIngrediente(int cod, String nom, long pre, String desc, int inv, int min ){
		Ingrediente ingre = new Ingrediente(cod, nom, pre, desc, inv, min);
		return ingre;
	}
	
	/*/**
	 * Este metodo permite crear un Plato, se usa asignacion de responsabilidades por lo tanto se llama al constructor de Plato
	 * @param cod codigo del Plato
	 * @param nom nombre del Plato
	 * @param pre precio del Plato
	 * @param lIngredientePlato lista de ingredientes que necesita el Plato
	 * @return nuevo Plato
	 */
//	public static Plato crearPlato(int cod, String nom, int pre, List<IngredientePlato> lIngredientePlato ){
//		Plato pl =new Plato(cod, nom, pre, lIngredientePlato);
//		return pl;
//	}
	/**Metodo que permite crear un PlatoCarta nuevo
	 * @param cod codsigo del plato
	 * @param nom nombre del plato
	 * @param pre precio del plato
	 * @param lIngredientePlato lista de ingredienteplato del plato
	 * @param tipo tipo de plato
	 * @param dia dia que se ofrece el plato
	 * @return retorna el PlatoCarta creado
	 */
	public static PlatoCarta crearPlatoCar(int cod, String nom, int pre, List<IngredientePlato> lIngredientePlato, String  tipo, String dia ){
		PlatoCarta pl = new PlatoCarta(cod, nom, pre, lIngredientePlato, dia);
		return pl;
	}
	/**Metodo que permite crear un PlatoDiario nuevo
	 * @param cod codigo del plato
	 * @param nom nombre del plato
	 * @param pre precio del plato
	 * @param lIngredientePlato lista de ingredienteplato del plato
	 * @param tipo tipo de plato
	 * @return retorna el PlatoDiario creado
	 */
	public static PlatoDiario crearPlatoDia(int cod, String nom, int pre, List<IngredientePlato> lIngredientePlato, String  tipo ){
		PlatoDiario pl = new PlatoDiario(cod, nom, pre, lIngredientePlato);
		return pl;
	}
	/**
	 * Este metodo permite crear un IngredientePlato, se usa asignacion de responsabilidades por lo tanto se llama al constructor de IngredientePlato
	 * Ademas, se usa el metodo de clase buscarIngrediente que retorna el ingrediente referente al codigo
	 * @param cod codigo del Ingrediente
	 * @param cant cantidad del Ingrediente 
	 * @return nuevo IngredientePlato 
	 */
	public static IngredientePlato crearIngredientePlato( int cod, int cant){
		
		Ingrediente in= Restaurante.buscarIngrediente(cod);
		IngredientePlato inp= Plato.crearIngredientePlato(cant, cod, in);
		return inp;
	}
	/**
	 * Este metodo permite obtener el precio de un plato por medio del codigo de este
	 * @param codigoPlato Codigo del Plato  
	 * @return retorna un long que corresponde a Plato
	 */
	public long ObtenerPrecioPlato(int codigoPlato){
		long precio=0;
		//java.util.Iterator<Plato> iterador=LPlatos.iterator();
		for(Plato platico:LPlatos.values()){
			//System.out.println(platico);
			if(platico.getCodigo()==codigoPlato){
				precio = platico.getPrecio();
				break;
			}
		}
		return precio;
	}

	/**
	 * Este metodo permite obtene el nombre de un Plato, por medio de el codigo
	 * @param codigoPlato codigo del plato a asociar
	 * @return String referente al nombre del Plato
	 */
	public static String ObtenerNombrePlato(int codigoPlato){
		String retorno=new String("");

		//java.util.Iterator<Plato> iterador=LPlatos.iterator();
		for(Plato plato:LPlatos.values()){
			if(plato.getCodigo()==codigoPlato){
				retorno = plato.getNombre();
				break;
			}
		}

		return retorno;
	}

	/**
	 * Este metodo permite agregar una orden a la Lista de Ordenes, se usa asignacion de responsabilidades 
	 * por lo tanto se llama al metodo agregarOrden de la clase Orden y finalmente se agrega a la lista
	 * @param cod codigo del Plato
	 * @param cant cantidad de platos solicitados
	 * @param POrden Plato que se quiere agregar 
	 * @param val valor de la orden
	 * @return int que corrresponde al codigo de la orden 
	 */
	public int agregarOrden( int cod, int cant, Plato POrden, long val){
		Orden or= new Orden();
		//ingresar cantidad y orden a Orden plato
		or.agregarOrden(cod, cant, POrden);
		or.setValor(val);
		LOrdenes.add(or);
		return or.getCodigo();
	}
	/**
	 * Este metodo permite agregar una OrdenPlato a una Orden ya establecida, se usa asignacion de responsabilidades
	 * por lo tanto se invoca al metodo agregarOrden de la clase Orden
	 * @param codO codigo de la Orden 
	 * @param codP codigo del Plato
	 * @param cant cantidad de los platos solicitados
	 * @param POrden Plato que se quiere agregar
	 * @param val valor de de la orden
	 */
	public void agregarOPlato( int codO, int codP, int cant,Plato POrden, long val){
		Orden o = buscarOrden(codO);
		o.agregarOrden(codP, cant, POrden);
		o.setValor(o.getValor() + val);
	}
	/**
	 * Este metodo permite buscar una Orden en la Lista de ordenes
	 * @param cod codigo de la Orden
	 * @return Orden correspondiente al codigo
	 */
	public Orden buscarOrden(int cod){
		Iterator it = LOrdenes.iterator();
		while(it.hasNext()){
			Orden o= (Orden)it.next(); 
			if(o.getCodigo()==cod){
				return o;
			}
		}
		return null;
	}

	/**
	 * @return the lIngredientes
	 */
	public List<Ingrediente> getLIngredientes() {
		return LIngredientes;
	}

	/**
	 * @param lIngredientes the lIngredientes to set
	 */
	public void setLIngredientes(List<Ingrediente> lIngredientes) {
		LIngredientes = lIngredientes;
	}

	/**
	 * @return the lPlatos
	 */
	public Map<Integer,Plato> getLPlatos() {
		return LPlatos;
	}

	/**
	 * @param lPlatos the lPlatos to set
	 */
	public void setLPlatos(HashMap<Integer,Plato> lPlatos) {
		LPlatos = lPlatos;
	}

	/**
	 * @return the lOrdenes
	 */
	public List<Orden> getLOrdenes() {
		return LOrdenes;
	}

	/**
	 * @param lOrdenes the lOrdenes to set
	 */
	public void setLOrdenes(List<Orden> lOrdenes) {
		LOrdenes = lOrdenes;
	}
	/**
	 * Este metodo permite imprimir la lista de ingredientes del restaurante
	 * @return String que contiene los ingredientes
	 */
	public String impLIngrediente(){
		String retorno=new String("");
		java.util.Iterator<Ingrediente> iterador=this.LIngredientes.iterator();
		while(iterador.hasNext()){
			retorno += iterador.next().toString()+"\n";
		}
		return retorno;
	}

	/**
	 * Este metodo permite imprimir la lista de platos del restaurante
	 * @return String que contiene los platos
	 */
	public String impLPlatos(){
		String retorno=new String("");
		for(Plato plato:LPlatos.values()){
			retorno += plato.toString()+"\n";
		}
		return retorno;
	}
	/**
	 * Este metodo permite imprimir el menu del restaurante de manera ordena 
	 * @return String que contiene el menu
	 */
	public String impPlatos(){
		String retorno=new String("");
		for(Plato plato:LPlatos.values()){
			retorno += plato.impPlatosFormato();
		}
		return retorno;
	}

	/**
	 * Este metodo permite buscar un Plato en la lista de platos
	 * @param cod codigo del plato
	 * @return Plato rederente al codigo
	 */
	public static Plato buscarPlato(int cod){
		for(Plato plat: LPlatos.values()){
			if(cod==plat.getCodigo()){
				return plat;
			}
		}
		return null;
	}

	/**
	 * Este metodo descuenta los ingrendientes utilizados en cada orden
	 * ademas genera un reporte cuando llegue a su minimo
	 * Tambien llenara el inventario cuando se acaben los productos
	 * @param cod del ingrediente
	 * @param cant que se ha solicitado
	 * @return boolean
	 */
	public static boolean descontar(int cod, int cant){
		int temp;
		boolean tempi= true;
		
		for(Plato plat: LPlatos.values()){
			if(cod==plat.getCodigo()){
				for(IngredientePlato inp: plat.getLIngredientePlato()){
					Ingrediente in= inp.getIngrediente();
					temp=in.getInventario();
					temp= temp - inp.getCantidad()*cant;
					//System.out.println("minimo"+ in.getMinimo()+" decontar "+ cant+" hay: "+ in.getInventario());
					if(temp < in.getMinimo()) {
						//System.out.println("--------------------ALERTA--------------------");
						//System.out.println("se acabo "+ in.getNombre());
						//System.out.println("---------------Llenando despensa---------------");
						//in.setInventario(in.getMinimo()*10);
						return false;
						
					}else{
						in.setInventario(temp);
					}

				}
			}
		}
		return true;
	}
	public static boolean verificarIng(int cod, int cant, Map <Integer, Integer> map){
		int temp;
		boolean tempi= true;
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			  Integer key = (Integer) it.next();
			  //System.out.println("Clave: " + key + " -> Valor: " + map.get(key));
			  Plato plat=buscarPlato(key);
			if(cod==plat.getCodigo()){
				for(IngredientePlato inp: plat.getLIngredientePlato()){
					Ingrediente in= inp.getIngrediente();
					//System.out.println(inp);
					temp=in.getInventario();
					//System.out.println(temp);
					temp= temp - cant;
					//System.out.println("Verificando___"+in.getNombre()+"Hay"+ in.getInventario()+" descontar "+ cant+" MINIMO:"+in.getMinimo());
					if(temp < in.getMinimo()) {
						System.out.println("----------------------------------------------");
						System.out.println("|     No queda minimo de "+in.getNombre()+"    ");
						System.out.println("----------------------------------------------");
						//System.out.println("--------------------ALERTA--------------------");
						//System.out.println("se acabo "+ in.getNombre());
						//System.out.println("---------------Llenando despensa---------------");
						return false;
					}
				}
			}
		}
		return true;
	}
	public static boolean verificarIn(int cod, int cant){
		int temp;
		boolean tempi= true;
		
		for(Plato plat: LPlatos.values()){
			if(cod==plat.getCodigo()){
				for(IngredientePlato inp: plat.getLIngredientePlato()){
					Ingrediente in= inp.getIngrediente();
					temp=in.getInventario();
					temp= temp - inp.getCantidad()*cant;
					System.out.println("minimo"+ in.getMinimo()+" descontar "+ cant+" hay: "+ in.getInventario());
					if(temp < in.getMinimo()) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Este metodo de clase permite buscar un Ingrediente en la lista de ingredientes dependiendo el codigo
	 * @param cod codigo del Ingrediente
	 * @return Ingrediente referente al codigo
	 */
	public static Ingrediente buscarIngrediente(int cod){
		for(Ingrediente ing :LIngredientes){
			if(cod==ing.getCodigo()){
				return ing;
			}
		}
		return null;
	}

	/**
	 * Este metodo permite cargar el consecutivo correcto de las ordenes al deserializar
	 * @param i numero que se desea asignar
	 */
	public void cambiarConse(int i) {
		Orden.setCONSE(i);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Restaurante ["+LIngredientes.size()+","+LPlatos.size()+","+LOrdenes.size()+"]";
	}


}
