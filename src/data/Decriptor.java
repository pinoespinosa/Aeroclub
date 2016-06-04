package data;

import java.util.List;

import base_datos.SimpleFile;
import base_datos.Utils;

public class Decriptor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> aa = SimpleFile.readFile("", "sentencias.txt");
		for (String string : aa) {
			System.out.println(Utils.decript(string));	
		}
	}

}
