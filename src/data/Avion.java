package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.managerDB;

public class Avion {

	private int id;
	private String name;
	private float precio;
	
	public Avion(int id, String name, float precio) {
		super();
		this.id = id;
		this.name = name;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	private static String getScriptDataBase(){
		return 	"	SELECT *" +
				"	FROM aviones.avion;";
	}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","name"});
	}
	
	private static Avion loadFromList(List<String> valores){		
		return new Avion(
							Integer.parseInt(valores.get(0)),
							valores.get(1),
							Float.parseFloat("1.0"));
	}
	
	public static List<Avion> loadFromDB(){
		List<List<String>> avionesData = managerDB.executeScript_Query(Avion.getScriptDataBase(), Avion.getFieldScriptBase());
		List<Avion> aviones = new ArrayList<Avion>();
		
		for (List<String> list : avionesData) {
			aviones.add(loadFromList(list));
		}
	return aviones;
	}

	@Override
	public String toString() {
		return "Avion " + id + " " + name;
	}
	
	
	
}
