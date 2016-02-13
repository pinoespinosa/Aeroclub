package data;

import base_datos.managerDB;
import extended.MainController;



public class Precios {

	public static final int EFECTIVO = 0, 
							CUENTA_CORRIENTE=1;

	
	
	public static final String COMBUSTIBLE_PRECIO_AEROCLUB = "combustiblePrecioAeroclub";
	public static final String COMBUSTIBLE_PRECIO_SOCIO = "combustiblePrecioSocio";
	public static final String ACEITE_PRECIO_AEROCLUB = "aceitePrecioAeroclub";
	public static final String ACEITE_PRECIO_SOCIO = "aceitePrecioSocio";
	
	public static float getPrecio(String elemento){
		return Float.parseFloat(managerDB.executeScript_Query("SELECT precio FROM "+MainController.getEsquema()+".precios WHERE id='"+elemento+"';", "precio").get(0));
	}
	
	public static void updatePrecio(String elemento, String precio){
		managerDB.executeScript_Void("UPDATE "+MainController.getEsquema()+".precios SET precio='"+precio+"' WHERE id='"+elemento+"';");
	}
	
	
}
