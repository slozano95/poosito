package co.edu.javeriana.restaurante.presentacion;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;


public class Utils {

	/**Metodo que permite convertir un String a GregorianCalenar
	 * @param cad String que se desea convertir
	 * @return Fecha en formato GregorianCalendar 
	 */
	public static GregorianCalendar convertirCadenaFecha(String cad){
		GregorianCalendar fecha;
		int dia,mes,agno;
		Scanner s = new Scanner(cad).useDelimiter("-");
		agno=s.nextInt();
		mes=s.nextInt();
		dia=s.nextInt();
		
		fecha=new GregorianCalendar(agno,mes-1,dia);
		
		return fecha;
	}

	/**
	 * Permite convertir un GregorianCalendar en String
	 * @param f GregorianCalendar del cual se desea calcular los anios
	 * @return Cadena con la fecha en formato String
	 */
	public static String convertirFechaCadena(GregorianCalendar f){
		
		return ""+f.get(Calendar.YEAR) + "-" + (f.get(Calendar.MONTH)+1) + "-" + f.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * Metodo que permite calcular los anios entre una fecha y otra enviadas por parametro
	 * @param f1 GregorianCalendar del cual se desea calcular los anios
	 * @param f2 GregorianCalendar que se comparara con el otro
	 * @return Numero de anios entre las fechas
	 */
	public static int calcularAgnos(GregorianCalendar f1, GregorianCalendar f2){
		long mf1=f1.getTimeInMillis();
		long mf2=f2.getTimeInMillis();
		long dif;
		
		if (mf1>mf2){
			dif=mf1-mf2;
		}
		else{
			dif=mf2-mf1;
		}
		
		return (int)((((dif/1000)/60)/60)/24)/365;
		
	}
	
//	public static int compararFechas(GregorianCalendar cal1, GregorianCalendar cal2){
//		if(cal1.get(Calendar.YEAR)<cal2.get(Calendar.YEAR)){
//			//CAL1 ANTIGUO
//			return -1;
//		}
//		else{
//			if(cal1.get(Calendar.MONTH)<cal2.get(Calendar.MONTH)){
//				return -1;
//			}
//			else{
//				if(cal1.get(Calendar.DAY_OF_MONTH)<cal2.get(Calendar.DAY_OF_MONTH)){
//					return -1;
//				}
//				else{
//					return 1;
//				}
//			}
//		}
//		
//		""+f.get(Calendar.YEAR) + "-" + (f.get(Calendar.MONTH)+1) + "-" + f.get(Calendar.DAY_OF_MONTH);
//	}
	
	/**
	 * Este metodo permite calcular los anios entre una fecha enviada por parametro y el dia de hoy
	 * @param f1 GregorianCalendar del cual se desea calcular los anios
	 * @return Numero de anios entre las fechas
	 */
	
	public static int calcularAgnos(GregorianCalendar f1){
		long mf1=f1.getTimeInMillis();
		GregorianCalendar f2=new GregorianCalendar();
		long mf2=f2.getTimeInMillis();
		long dif;
		
		if (mf1>mf2){
			dif=mf1-mf2;
		}
		else{
			dif=mf2-mf1;
		}
		
		return (int)((((dif/1000)/60)/60)/24)/365;
		
	}
	
}
