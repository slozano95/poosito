package co.edu.javeriana.restaurante.negocio;

import java.util.List;

public class PlatoDiario extends Plato{

	public PlatoDiario() {
		// TODO Auto-generated constructor stub
	}

	/** Constructor con parametros
	 * @param codigo codigo que se asignara
	 * @param nombre nombre del plato
	 * @param precio precio del plato
	 * @param lIngredientePlato lista de ingredientesplato del plato
	 */ 
	public PlatoDiario(int codigo, String nombre, int precio,
			List<IngredientePlato> lIngredientePlato) {
		super(codigo, nombre, precio, lIngredientePlato);
		// TODO Auto-generated constructor stub
	}
	/* (non-Javadoc)
	 * @see co.edu.javeriana.restaurante.negocio.Plato#calcularPrecio()
	 */
	@Override
	public void calcularPrecio() {
		int precioP=0;
		for(IngredientePlato ip:super.getLIngredientePlato()){
			precioP+=ip.getCantidad()*ip.getIngrediente().getPrecioUnitario();
		}
		precioP+=(precioP)*0.16;
		super.setPrecio(precioP);
	}
}
