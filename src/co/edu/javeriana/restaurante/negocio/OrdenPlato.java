/**
 * 
 */
package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;
import java.util.List;

/**
 * @author Santiago
 *
 */
public class OrdenPlato  implements Serializable{
	public int cantidad;
	private int codigo;
	private Plato POrden;
	//private List<Orden> LOrdenes;
	
	
	/** Constructor por referencia
	 * @param pl Plato que se recibe para verificar su clase y clasificarlo correctamente
	 */
	public OrdenPlato(Plato pl){
		if(pl instanceof PlatoCarta)
			POrden = new PlatoCarta();
		else
			POrden = new PlatoDiario();
	}
	
	/**
	 * Constructor por referencia
	 * @param cod Codigo que se quiere asignar
	 * @param cant Cantidad que se queire asignar
	 * @param pl Plato que se asignara
	 */
	public OrdenPlato(int cod, int cant, Plato pl){
		codigo=cod;
		cantidad=cant;
		if(pl instanceof PlatoCarta)
			POrden = new PlatoCarta();
		else
			POrden = new PlatoDiario();
	}


	/**
	 * @return the POrden
	 */
	public Plato getPOrden() {
		return POrden;
	}

	/**
	 * @param POrden the POrden to set
	 */
	public void setPOrden(Plato POrden) {
		this.POrden = POrden;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrdenPlato [cantidad=" + cantidad + ", codigo=" + codigo
				+ "]";
	}

	
	
}
