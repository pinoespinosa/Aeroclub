package data;

import base_datos.managerDB;
import extended.MainController;



public class Precios {
	
	public enum TYPE_PAGO { EFECTIVO, CUENTA_CORRIENTE, CHEQUE } // UNKNOWN
	public enum TYPE_PAGO_VUELO { EFECTIVO, CUENTA_CORRIENTE, CHEQUE, HORAS_ADELANTADAS }
	
	
	public static final String COMBUSTIBLE = "combustible";
	public static final String ACEITE = "aceite";
	
	public static float getPrecio(String elemento){
		return Float.parseFloat(managerDB.executeScript_Query("SELECT precio FROM "+MainController.getEsquema()+".precios WHERE id='"+elemento+"';", "precio").get(0));
	}
	
	public static void updatePrecio(String elemento, String precio){
		managerDB.executeScript_Void("UPDATE "+MainController.getEsquema()+".precios SET precio='"+precio+"' WHERE id='"+elemento+"';");
	}
	
	
}
