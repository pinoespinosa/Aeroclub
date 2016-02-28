package extended;
import java.awt.Font;
import java.awt.Frame;
import java.util.Hashtable;
import java.util.List;

import javax.swing.JFrame;

import base_datos.SimpleFile;

public class MainController {

	private static final String 	
	UBICACION_MYSQL="UBICACION_MYSQL",
	USUARIO="USUARIO",
	PASSWORD="PASSWORD",
	ESQUEMA="ESQUEMA",
	CARPETA_FILES="CARPETA_FILES",
	IP_WEB_SERVICE="IP_WEB_SERVICE";
	
	private static Hashtable<String, String> properties;
	
	public static void loadProperties() {
		properties= new Hashtable<String, String>();
				
		List<String> prop = SimpleFile.readFile("","config.prop");
		for (String value : prop) {
			properties.put(value.split("=")[0],value.split("=")[1]);
		}
		
	}
	
	
	public static final int 
		GROUP_LAYOUT=0, 
		BUTTON=1;
	
	public static final int 
		ACTION_CONTINUE=0,
		ACTION_REACTIVAR_PADRE=1;
	
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
		return properties.get(UBICACION_MYSQL);
	}
	
	public static String getUsuario() {
		return properties.get(USUARIO);
	}

	public static String getPassword() {
		return properties.get(PASSWORD);
	}

	public static String getEsquema() {
		return properties.get(ESQUEMA);
	}

	public static boolean getLicenciaValida() {
		return licenciaValida;
	}

	public static void setLicenciaValida(boolean licenciaValida) {
		MainController.licenciaValida = licenciaValida;
	}

	public static Hashtable<String, String> getProperties() {
		return properties;
	}



	
}

