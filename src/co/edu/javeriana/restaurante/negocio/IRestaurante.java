package co.edu.javeriana.restaurante.negocio;

import java.util.List;
import java.util.Map;

public interface IRestaurante {
//	
//		public String sus(String nom, String fec);
//		public int buscarOrdenCod(String nomP);
//		/**
//
//		 * Este metodo permite obtener el precio de un Ingrediente deacuerdo al precio unitario y la cantidad 
//		 * @param codigoIngre  codigo con el cual esta registrado el ingrediente
//		 * @param cantidad cantidad que se necesita del ingredient
//		 * @return precio de tipo long
//		 */
//		public static long ObtenerPrecioIngrediente(int codigoIngre,int cantidad);
//		/**
//		 * Este metodo permite crear un Ingrediente, se usa asignacion de responsabilidades por lo tanto se llama al constructor de Ingrediente
//		 * @param cod codigo del Ingrediente
//		 * @param nom nombre del Ingrediente
//		 * @param pre precio del Ingrediente
//		 * @param desc descripcion del Ingrediente
//		 * @param inv inventario del Ingrediente
//		 * @param min minimo del Ingrediente
//		 * @return nuevo Ingrediente
//		 */
//		public static Ingrediente crearIngrediente(int cod, String nom, long pre, String desc, int inv, int min ){};
//		/*/**
//		 * Este metodo permite crear un Plato, se usa asignacion de responsabilidades por lo tanto se llama al constructor de Plato
//		 * @param cod codigo del Plato
//		 * @param nom nombre del Plato
//		 * @param pre precio del Plato
//		 * @param lIngredientePlato lista de ingredientes que necesita el Plato
//		 * @return nuevo Plato
//		 */
////		public static Plato crearPlato(int cod, String nom, int pre, List<IngredientePlato> lIngredientePlato ){
////			Plato pl =new Plato(cod, nom, pre, lIngredientePlato){};
////			return pl{};
////		}
//		/**Metodo que permite crear un PlatoCarta nuevo
//		 * @param cod codsigo del plato
//		 * @param nom nombre del plato
//		 * @param pre precio del plato
//		 * @param lIngredientePlato lista de ingredienteplato del plato
//		 * @param tipo tipo de plato
//		 * @param dia dia que se ofrece el plato
//		 * @return retorna el PlatoCarta creado
//		 */
//		public static PlatoCarta crearPlatoCar(int cod, String nom, int pre, List<IngredientePlato> lIngredientePlato, String  tipo, String dia ){};
//		/**Metodo que permite crear un PlatoDiario nuevo
//		 * @param cod codigo del plato
//		 * @param nom nombre del plato
//		 * @param pre precio del plato
//		 * @param lIngredientePlato lista de ingredienteplato del plato
//		 * @param tipo tipo de plato
//		 * @return retorna el PlatoDiario creado
//		 */
//		public static PlatoDiario crearPlatoDia(int cod, String nom, int pre, List<IngredientePlato> lIngredientePlato, String  tipo ){};
//		/**
//		 * Este metodo permite crear un IngredientePlato, se usa asignacion de responsabilidades por lo tanto se llama al constructor de IngredientePlato
//		 * Ademas, se usa el metodo de clase buscarIngrediente que retorna el ingrediente referente al codigo
//		 * @param cod codigo del Ingrediente
//		 * @param cant cantidad del Ingrediente 
//		 * @return nuevo IngredientePlato 
//		 */
//		public static IngredientePlato crearIngredientePlato( int cod, int cant){};
//		/**
//		 * Este metodo permite obtener el precio de un plato por medio del codigo de este
//		 * @param codigoPlato Codigo del Plato  
//		 * @return retorna un long que corresponde a Plato
//		 */
//		public long ObtenerPrecioPlato(int codigoPlato){};
//
//		/**
//		 * Este metodo permite obtene el nombre de un Plato, por medio de el codigo
//		 * @param codigoPlato codigo del plato a asociar
//		 * @return String referente al nombre del Plato
//		 */
//		public static String ObtenerNombrePlato(int codigoPlato){};
//		/**
//		 * Este metodo permite agregar una orden a la Lista de Ordenes, se usa asignacion de responsabilidades 
//		 * por lo tanto se llama al metodo agregarOrden de la clase Orden y finalmente se agrega a la lista
//		 * @param cod codigo del Plato
//		 * @param cant cantidad de platos solicitados
//		 * @param POrden Plato que se quiere agregar 
//		 * @param val valor de la orden
//		 * @return int que corrresponde al codigo de la orden 
//		 */
//		public int agregarOrden( int cod, int cant, Plato POrden, long val){};
//		/**
//		 * Este metodo permite agregar una OrdenPlato a una Orden ya establecida, se usa asignacion de responsabilidades
//		 * por lo tanto se invoca al metodo agregarOrden de la clase Orden
//		 * @param codO codigo de la Orden 
//		 * @param codP codigo del Plato
//		 * @param cant cantidad de los platos solicitados
//		 * @param POrden Plato que se quiere agregar
//		 * @param val valor de de la orden
//		 */
//		public void agregarOPlato( int codO, int codP, int cant,Plato POrden, long val){};
//		/**
//		 * Este metodo permite buscar una Orden en la Lista de ordenes
//		 * @param cod codigo de la Orden
//		 * @return Orden correspondiente al codigo
//		 */
//		public Orden buscarOrden(int cod){};
//		/**
//		 * Este metodo permite imprimir la lista de ingredientes del restaurante
//		 * @return String que contiene los ingredientes
//		 */
//		public String impLIngrediente(){};
//
//		/**
//		 * Este metodo permite imprimir la lista de platos del restaurante
//		 * @return String que contiene los platos
//		 */
//		public String impLPlatos(){
//			String retorno=new String(""){};
//			for(Plato plato:LPlatos.values()){
//				retorno += plato.toString()+"\n"{};
//			}
//			return retorno{};
//		}
//		/**
//		 * Este metodo permite imprimir el menu del restaurante de manera ordena 
//		 * @return String que contiene el menu
//		 */
//		public String impPlatos(){};
//
//		/**
//		 * Este metodo permite buscar un Plato en la lista de platos
//		 * @param cod codigo del plato
//		 * @return Plato rederente al codigo
//		 */
//		public static Plato buscarPlato(int cod){};
//
//		/**
//		 * Este metodo descuenta los ingrendientes utilizados en cada orden
//		 * ademas genera un reporte cuando llegue a su minimo
//		 * Tambien llenara el inventario cuando se acaben los productos
//		 * @param cod del ingrediente
//		 * @param cant que se ha solicitado
//		 * @return boolean
//		 */
//		public static boolean descontar(int cod, int cant){};
//		/**Permite verificar la existencia de un ingrediente
//		 * @param cod codigo del ingrediente
//		 * @param cant cantidad del ingrediente
//		 * @param map mapa de ingredientes
//		 * @return verdadero si existe
//		 */
//		public static boolean verificarIng(int cod, int cant, Map <Integer, Integer> map){};
//		/**Permite verificar la existencia de un ingrediente
//		 * @param cod codigo del ingrediente
//		 * @param cant cantidad del ingrediente
//		 * @return verdadero si exist
//		 */
//		public static boolean verificarIn(int cod, int cant){};
//
//		/**
//		 * Este metodo de clase permite buscar un Ingrediente en la lista de ingredientes dependiendo el codigo
//		 * @param cod codigo del Ingrediente
//		 * @return Ingrediente referente al codigo
//		 */
//		public static Ingrediente buscarIngrediente(int cod){};
//
//		/**
//		 * Este metodo permite cargar el consecutivo correcto de las ordenes al deserializar
//		 * @param i numero que se desea asignar
//		 */
//		public void cambiarConse(int i) {};
//
//		/* (non-Javadoc)
//		 * @see java.lang.Object#toString()
//		 */
//		@Override
//		public String toString() {
//			return "Restaurante ["+LIngredientes.size()+","+LPlatos.size()+","+LOrdenes.size()+"]"{};
//		}


	

}
