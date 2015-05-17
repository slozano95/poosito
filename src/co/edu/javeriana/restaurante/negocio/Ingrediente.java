/**
 * 
 */
package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;

/**
 * @author Santiago
 *
 */
public class Ingrediente  implements Serializable{
	
	private int codigo;
	private String nombre;
	private long precioUnitario;
	private String descripcionUnidad;
	private int inventario;
	private int minimo;
	
	/**
	 * Constructor por defecto
	 */
	public Ingrediente(){
	}
	
	/**
	 * Constructor por referencia
	 * @param cod Codigo del Ingrediente
	 * @param nom Nombre del Ingrediente
	 * @param pre Precio del Ingrediente
	 * @param desc Descripcion del Ingrediente
	 * @param inv Cantidad Disponible
	 * @param min Minimo para solicitar
	 */
	public Ingrediente(int cod,String nom,long pre,String desc,int inv,int min){
		codigo=cod;
		nombre=nom;
		precioUnitario=pre;
		descripcionUnidad=desc;
		inventario=inv;
		minimo=min;
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
	 * @return the precioUnitario
	 */
	public long getPrecioUnitario() {
		return precioUnitario;
	}

	/**
	 * @param precioUnitario the precioUnitario to set
	 */
	public void setPrecioUnitario(long precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	/**
	 * @return the descripcionUnidad
	 */
	public String getDescripcionUnidad() {
		return descripcionUnidad;
	}

	/**
	 * @param descripcionUnidad the descripcionUnidad to set
	 */
	public void setDescripcionUnidad(String descripcionUnidad) {
		this.descripcionUnidad = descripcionUnidad;
	}

	/**
	 * @return the inventario
	 */
	public int getInventario() {
		return inventario;
	}

	/**
	 * @param inventario the inventario to set
	 */
	public void setInventario(int inventario) {
		this.inventario = inventario;
	}

	/**
	 * @return the minimo
	 */
	public int getMinimo() {
		return minimo;
	}

	/**
	 * @param minimo the minimo to set
	 */
	public void setMinimo(int minimo) {
		this.minimo = minimo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Codigo: "+codigo+" Nom: "+nombre+" Precio: "+precioUnitario+" Desc: "+descripcionUnidad+" Invent: "+inventario+" Min: "+minimo;

//		return "Ingrediente [codigo=" + codigo + ", nombre=" + nombre
//				+ ", precioUnitario=" + precioUnitario + ", descripcionUnidad="
//				+ descripcionUnidad + ", inventario=" + inventario
//				+ ", minimo=" + minimo + "]";
	}
	
	
}
