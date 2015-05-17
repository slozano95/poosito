package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Plato  implements Serializable{
	private int codigo;
	private String nombre;
	private enumEstado estado;
	private int precio;
	private List<IngredientePlato> LIngredientePlato;
	
	public enum enumEstado{
		ACTIVO, DESCONTINUADO	
	}
	
	/**
	 * Constructor por defecto
	 */
	public Plato(){
		estado = enumEstado.ACTIVO;
		LIngredientePlato = new ArrayList<IngredientePlato>();
	}


	public String getEstado() {
		if(this.estado==enumEstado.ACTIVO){
		return "ACTIVO";	
		}else{
		return "DESCONTINUADO";
		}
	}

	public void setEstado(String estadoNuevo) {
		if(estadoNuevo.equalsIgnoreCase("ACTIVO")){
			this.estado = enumEstado.ACTIVO;	
			}else{
				this.estado = enumEstado.DESCONTINUADO;
			}
		
	}

	/**
	 * Constructor por referencia
	 * @param codigo Codigo del plato
	 * @param nombre Nombre del plato
	 * @param precio precio del plato
	 * @param lIngredientePlato Lista de ingredienteplato
	 */
	public Plato(int codigo, String nombre, int precio, List<IngredientePlato> lIngredientePlato) {
		estado = enumEstado.ACTIVO;
		this.codigo = codigo;
		this.nombre = nombre;
		this.precio = precio;
		LIngredientePlato = lIngredientePlato;
	}
	
	/**
	 * Metodo a heredad que se utiliza para calcular el precio de un plato
	 */
	public abstract void calcularPrecio();
	
	/**
	 * Este metodo permite crear un IngredientePlato
	 * @param cant cantidad solicitada
	 * @param cod codigo del Ingrediente
	 * @param ing ingrediente a asignar
	 * @return IngredientePlato con el objeto recien creado
	 */
	public static IngredientePlato crearIngredientePlato(int cant, int cod, Ingrediente ing){
		IngredientePlato inp= new IngredientePlato(cant, cod, ing);
		return inp;
	}
	
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the precio
	 */
	public int getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(int precio) {
		this.precio = precio;
	}

	/**
	 * @return the lIngredientePlato
	 */
	public List<IngredientePlato> getLIngredientePlato() {
		return LIngredientePlato;
	}

	/**
	 * @param lIngredientePlato the lIngredientePlato to set
	 */
	public void setLIngredientePlato(List<IngredientePlato> lIngredientePlato) {
		LIngredientePlato = lIngredientePlato;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ingre=new String("");

		java.util.Iterator<IngredientePlato> iterador=LIngredientePlato.iterator();
		while(iterador.hasNext()){
			IngredientePlato obt=iterador.next();
			ingre+=obt.toString();
		}

		return "Codigo Plato: "+codigo+"\n\tNom: "+nombre+"\n\tPrecio: "+precio+"\n\tIngredientes: "+ingre;

	}

	/**
	 * Este metodo permite imprimir el plato de manera ordenada
	 * @return String con la informacion del plato
	 */
	public String impPlatosFormato(){
		String retorno=new String("");
		String diaimp=new String("");
		if(this instanceof PlatoCarta){
			System.out.println(((PlatoCarta)this).getDia());
			switch(((PlatoCarta)this).getDia()){
			case "LUNES":
				diaimp="Lunes";
				break;
			case "MARTES":
				diaimp="Martes";
				break;
			case "MIERCOLES":
				diaimp="Miercoles";
				break;
			case "JUEVES":
				diaimp="Jueves";
				break;
			case "VIERNES":
				diaimp="Viernes";
				break;
			case "SABADO":
				diaimp="Sabado";
				break;
			case "DOMINGO":
				diaimp="Domingo";
				break;
			default:
				diaimp="Dia No Disponible";
				break;
			}
			retorno = String.format("%-10s %-30s %-20s %-20s $%-20s\n",codigo,nombre,"Carta",diaimp,precio);
		}
		else{
			retorno = String.format("%-10s %-30s %-20s %-20s $%-20s\n",codigo,nombre,"Diario","",precio);
		}
		//return codigo+"\t"+nombre+"\t\t"+precio;
		return retorno;

	}
	
}