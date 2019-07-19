package base_datos;

import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Utils {

	/*
	public static void main(final String[] args) {
				
		System.out.println(Long.parseLong(decript("Y6ZtyCy3/vaBMxzZsx+ieQ==")));
		System.out.println(Long.parseLong(decript("4zAuuIzaiS6iW0f57fwxVg==")));
	
		System.out.println(encript("1572615229000"));
		
	}
	
	*/
	public static String toCamelCase(String value) {
		if (value.length() > 1)
			return value.toUpperCase().substring(0, 1) + value.toLowerCase().substring(1, value.length());
		else
			return value;

	}

	public static String tolowerCamelCase(String value) {

		String[] palabras = value.split(" ");

		for (int i = 0; i < palabras.length; i++) {
			palabras[i] = toCamelCase(palabras[i]);
		}

		palabras[0] = palabras[0].toLowerCase();

		String valor = "";

		for (String string : palabras) {
			valor += string;
		}
		return valor;
	}
	
	public static String encript(String text) {

		try {
			String key = "Bar12345Bar12345"; // 128 bit key
			// Create key and cipher
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// encrypt the text
			cipher.init(Cipher.ENCRYPT_MODE, aesKey);
			byte[] encrypted = cipher.doFinal(text.getBytes());
			String str = Base64.getEncoder().encodeToString(encrypted);
			return str;

		} catch (Exception e) {

		}
		return null;
	}

	public static String decript(String text) {

		try {
			byte[] encrypted = Base64.getDecoder().decode(text);

			// decrypt the text
			String key = "Bar12345Bar12345";
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, aesKey);
			String decrypted = new String(cipher.doFinal(encrypted));
			return decrypted;
		} catch (Exception e) {
		}

		return null;

	}

	public static int minutesBetweenDates(Date inicio, Date fin) {

		double resultado = 0.0;

		try {

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm");

			Date fechaInicioSimple = format.parse(format.format(inicio));
			Date fechaFinalSimple = format.parse(format.format(fin));

			resultado = fechaFinalSimple.getTime() - fechaInicioSimple.getTime();
			resultado = resultado / (60 * 1000);

		} catch (Exception e) {
		}

		return (int) resultado;
	}
	
	public static boolean alredyOcurred(Date date){
		return 	(Utils.minutesBetweenDates(new Date(System.currentTimeMillis()), date) < 0);

		
	}
}
