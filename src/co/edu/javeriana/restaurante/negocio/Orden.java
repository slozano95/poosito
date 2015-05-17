/**
 * 
 */
package co.edu.javeriana.restaurante.negocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import co.edu.javeriana.restaurante.presentacion.Utils;

/**
 * @author Santiago_Paula
 *
 */
public class Orden  implements Serializable{
	private static int CONSE=1;
	private int codigo;
	private long valor;
	private GregorianCalendar fecha;
	private List<OrdenPlato> LOrdenPlato;
	
	/**
	 * Constructor por defecto
	 */
	public Orden(){
		codigo=CONSE;
		CONSE++;
		fecha = new GregorianCalendar();
		valor=0;
		LOrdenPlato=new ArrayList<OrdenPlato>();
		
	}
	
	/**
	 * @return the cONSE
	 */
	public static int getCONSE() {
		return CONSE;
	}

	/**
	 * @param cONSE the cONSE to set
	 */
	public static void setCONSE(int cONSE) {
		CONSE = cONSE;
	}
	
	/**
	 * Este metodo permite agregar una OrdenPlato, ademas se asigna a la lista de OrdenePlato
	 * @param cod Codigo del Plato
	 * @param cant Cantidad e platos solicitados 
	 * @param plato Plato que se desea asignar
	 */
	public void agregarOrden(int cod, int cant, Plato plato){
		OrdenPlato op = new OrdenPlato(plato);
		//System.out.println("llora 11111111111111");
		if(this.buscarPlato(cod)==null){
			op.setCodigo(cod);
			op.setPOrden(plato);
			op.setCantidad(cant);
			LOrdenPlato.add(op);
		}else{
			//System.out.println("LLora 22222222222222");
			this.buscarPlato(cod).setCantidad(this.buscarPlato(cod).getCantidad()+cant);
		}
	
	}
	
	/**
	 * Este metodo permite buscar un OrdenPlato en la lista de ordenesplato
	 * @param cod Codigo de OrdenPlato 
	 * @return OrdenPlato Objeto OrdenPlato referente al codigo
	 */
	public OrdenPlato buscarPlato(int cod){
		for(OrdenPlato plat: LOrdenPlato){
			if(cod==plat.getCodigo()){
				return plat;
			}
		}
		return null;
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
	 * @return the valor
	 */
	public long getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(long valor) {
		this.valor = valor;
	}

	/**
	 * @return the fecha
	 */
	public GregorianCalendar getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	
	
	public void setFecha(GregorianCalendar fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return the lOrdenPlato
	 */
	public List<OrdenPlato> getLOrdenPlato() {
		return LOrdenPlato;
	}

	/**
	 * @param lOrdenPlato the lOrdenPlato to set
	 */
	public void setLOrdenPlato(List<OrdenPlato> lOrdenPlato) {
		LOrdenPlato = lOrdenPlato;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String sal=new String("");
		
		for(OrdenPlato op:LOrdenPlato){
			//sal+=String.format("|%-8s %s x %s %13s|\n","",op.getCantidad(),Restaurante.ObtenerNombrePlato(op.getCodigo()),"");
			sal+="\n\t\t"+op.getCantidad()+" x "+Restaurante.ObtenerNombrePlato(op.getCodigo())+"";
		}
		String ret = String.format("-------------------------------------\n|%-5s ORDEN #%s %-20s|\n","",codigo,"");
		ret += String.format("|%-5s FECHA %s %-13s|\n","",Utils.convertirFechaCadena(fecha),"");
		ret += String.format("-------------------------------------\n");
		ret += String.format("\tPLATOS:%s",sal);
		
		ret += String.format("\n-------------------------------------\n");
		ret += String.format("|%-5s VALOR TOTAL: $%s %-13s\n","",valor,"");
		ret += String.format("-------------------------------------\n\n\n");
//		return  "--------------------------------------------------------------"+
//				"|\tORDEN #"+codigo+
//				"|\n|\tFECHA "+Utils.convertirFechaCadena(fecha)+
//				"|\n|\tPlatos: "+sal+
//				"|\n|\n|\tValor Total: $"+valor
//				+"|\n"
//				;
		return ret;
	}

	
	
}
