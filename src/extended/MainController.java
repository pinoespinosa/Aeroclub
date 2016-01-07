package extended;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JFrame;

public class MainController {

	public static final String 	
	UBICACION_MYSQL="I:\\Program Files (x86)\\MySQL\\MySQL Server 5.5\\bin\\",
	USUARIO="root",
	PASSWORD="36442114",
	ESQUEMA="aviones",
	CARPETA_FILES="carpetaFiles",
	IP_WEB_SERVICE="ipWebService";
	
	public static final int 
		GROUP_LAYOUT=0, 
		BUTTON=1;
	
	public static final int 
		ACTION_CONTINUE=0,
		ACTION_EXIT=1;
	
	private static boolean licenciaValida;

	/**
	 * Configura el tamaño de la ventana a Maximizado
	 * @param view
	 */
	public static void setViewConfig(JFrame view){
		view.setExtendedState(Frame.MAXIMIZED_BOTH);
	}
	
	public static Font getDefaultFont(int element ){
		switch (element) {

			case GROUP_LAYOUT:	return new Font("Tahoma", Font.BOLD | Font.ITALIC, 12);
			case BUTTON:		return new Font("Tahoma", Font.BOLD | Font.ITALIC, 14);

			default: 			return new Font("Tahoma", Font.BOLD | Font.ITALIC, 12);
		}
 	}

	
	public static String getUbicacionMysql() {
		return UBICACION_MYSQL;
	}

	public static String getUsuario() {
		return USUARIO;
	}

	public static String getPassword() {
		return PASSWORD;
	}

	public static String getEsquema() {
		return ESQUEMA;
	}

	public static boolean getLicenciaValida() {
		return licenciaValida;
	}

	public static void setLicenciaValida(boolean licenciaValida) {
		MainController.licenciaValida = licenciaValida;
	}



	
}

