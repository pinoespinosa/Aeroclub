package base_datos;

public class Utils {

	public static String toCamelCase( String value){
		if (value.length()>1)
			return value.toUpperCase().substring(0, 1) + value.toLowerCase().substring(1, value.length());
		else
			return value;
					
	} 
	
}
