/**
 * 
 */
package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;

/**
 * @author Santiago
 *
 */
public class IngredientePlato implements Serializable{
	private int cantidad;
	private int codigo;
	private Ingrediente ingrediente;
	
	public IngredientePlato(){
		ingrediente=new Ingrediente();
		
	}
	public IngredientePlato(int cant,int cod, Ingrediente in){
		cantidad= cant;
		codigo=cod;
		ingrediente=in;
	}
	
	/**
	 * @return the ingrediente
	 */
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	/**
	 * @param ingrediente the ingrediente to set
	 */
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}
	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\t\tCodigo: "+codigo+"\t\tCantidad: "+cantidad+"\t\tIngre: "+ingrediente;

	}
}
