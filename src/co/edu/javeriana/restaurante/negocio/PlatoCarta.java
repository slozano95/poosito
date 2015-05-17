/**
 * 
 */
package co.edu.javeriana.restaurante.negocio;

import java.util.List;

import co.edu.javeriana.restaurante.negocio.Plato.enumEstado;

/**
 * @author Santiago
 *
 */
public class PlatoCarta extends Plato{
	
	private EnumDia dia;
	public enum EnumDia{
		LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
		
	} 
	

	public String getDia() {
		if(this.dia==EnumDia.LUNES){
			return "LUNES";	
			}
		if(this.dia==EnumDia.MARTES){
			return "MARTES";	
			}
		if(this.dia==EnumDia.MIERCOLES){
			return "MIERCOLES";	
			}
		if(this.dia==EnumDia.JUEVES){
			return "JUEVES";	
			}
		if(this.dia==EnumDia.VIERNES){
			return "VIERNES";	
			}
		if(this.dia==EnumDia.SABADO){
			return "SABADO";	
			}
		if(this.dia==EnumDia.DOMINGO){
			return "DOMINGO";	
			}
		return null;	
	}
	/**
	 * @param dia the dia to set
	 */
	public void setDia(String diaNuevo) {
		if(diaNuevo.equalsIgnoreCase("LUNES")){
			this.dia = EnumDia.LUNES;
			}
		if(diaNuevo.equalsIgnoreCase("MARTES")){
			this.dia = EnumDia.MARTES;
			}
		if(diaNuevo.equalsIgnoreCase("MIERCOLES")){
			this.dia = EnumDia.MIERCOLES;
			}
		if(diaNuevo.equalsIgnoreCase("JUEVES")){
			this.dia = EnumDia.JUEVES;
			}
		if(diaNuevo.equalsIgnoreCase("VIERNES")){
			this.dia = EnumDia.VIERNES;
			}
		if(diaNuevo.equalsIgnoreCase("SABADO")){
			this.dia = EnumDia.SABADO;
			}
		if(diaNuevo.equalsIgnoreCase("DOMINGO")){
			this.dia = EnumDia.DOMINGO;
			}
	}
	/**
	 * 
	 */
	public PlatoCarta() {
		// TODO Auto-generated constructor stub
	}
	/** Constructor con parametros
	 * @param codigo codigo que se asignara
	 * @param nombre nombre del plato
	 * @param precio precio del plato
	 * @param lIngredientePlato lista de ingredientesplato del plato
	 * @param dia dia de la semana que se permite el plato
	 */
	public PlatoCarta(int codigo, String nombre, int precio,
			List<IngredientePlato> lIngredientePlato, String dia) {
		super(codigo, nombre, precio, lIngredientePlato);
		switch (dia) {
		case "lu":
			dia = "LUNES"; 
			break;
		case "ma":
			dia = "MARTES"; 
			break;
		case "mi":
			dia = "MIERCOLES"; 
			break;
		case "ju":
			dia = "JUEVES"; 
			break;
		case "vi":
			dia = "VIERNES"; 
			break;
		case "sa":
			dia = "SABADO"; 
			break;
		case "do":
			dia = "DOMINGO"; 
			break;
		default:
			break;
		}
		this.setDia(dia);
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see co.edu.javeriana.restaurante.negocio.Plato#calcularPrecio()
	 */
	public void calcularPrecio() {
		int precioP=0;
		for(IngredientePlato ip:super.getLIngredientePlato()){
			precioP+=ip.getCantidad()*ip.getIngrediente().getPrecioUnitario();
		}
		precioP+=(precioP)*0.16;
		precioP+=(precioP)*0.1;
		super.setPrecio(precioP);
	}	
}