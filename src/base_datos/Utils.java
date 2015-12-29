package base_datos;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Utils {

	public static String toCamelCase(String value) {
		if (value.length() > 1)
			return value.toUpperCase().substring(0, 1) + value.toLowerCase().substring(1, value.length());
		else
			return value;

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
			// TODO: handle exception
		}
		
		return null;
		
		
	}

}
