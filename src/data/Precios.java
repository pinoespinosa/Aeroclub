package data;

import base_datos.managerDB;



public class Precios {

	public static final int EFECTIVO = 0, 
							CUENTA_CORRIENTE=1;

	
	
	public static final String PRECIO_COMBUSTIBLE_AEROCLUB = "precioCombustibleAeroclub";
	public static final String PRECIO_ACEITE_AEROCLUB = "precioAceiteAeroclub";
	
	
	public static float getPrecio(String elemento){
		return Float.parseFloat(managerDB.executeScript_Query("SELECT precio FROM aviones.precios WHERE id='"+elemento+"';", "precio").get(0));
	}
	
	
	
}
