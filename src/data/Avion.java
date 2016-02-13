package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.managerDB;
import extended.MainController;

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
	
	public Avion(int id) {
		super();
		this.id = id;
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
				"	FROM "+MainController.getEsquema()+".avion;";
	}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","name", "precio"});
	}
	
	private static Avion loadFromList(List<String> valores){		
		return new Avion(
							Integer.parseInt(valores.get(0)),
							valores.get(1),
							Float.parseFloat(valores.get(2)));
	}
	
	private static List<Avion> loadFromDB(String script){
		List<List<String>> avionesData = managerDB.executeScript_Query(script, Avion.getFieldScriptBase());
		List<Avion> aviones = new ArrayList<Avion>();
		
		for (List<String> list : avionesData) {
			aviones.add(loadFromList(list));
		}
	return aviones;
	}
	
	public static List<Avion> loadFromDB(){
		return loadFromDB(Avion.getScriptDataBase());
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Avion)
			return ((Avion) arg0).getId() == getId();
		else
			return false;
	}
	
	public static Avion getAvionById(String id){

		String script = 	"SELECT * " +
							"FROM "+MainController.getEsquema()+".avion " +
							"WHERE id like '"+id +"' ;";
		
		return loadFromDB(script).get(0);
	}
	
	
	
}
