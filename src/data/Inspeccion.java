package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.managerDB;
import extended.MainController;

public class Inspeccion {

	private int id, idAvion;
	private double fechaInspeccion;
	private String tipoInspeccion;
	
	

	
	public Inspeccion() {
		super();
	}
	public Inspeccion(int id, int idAvion, double fechaInspeccion, String tipoInspeccion) {
		super();
		this.id = id;
		this.idAvion = idAvion;
		this.fechaInspeccion = fechaInspeccion;
		this.tipoInspeccion = tipoInspeccion;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdAvion() {
		return idAvion;
	}
	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}
	public double getFechaInspeccion() {
		return fechaInspeccion;
	}
	public void setFechaInspeccion(double fechaInspeccion) {
		this.fechaInspeccion = fechaInspeccion;
	}
	public String getTipoInspeccion() {
		return tipoInspeccion;
	}
	public void setTipoInspeccion(String tipoInspeccion) {
		this.tipoInspeccion = tipoInspeccion;
	}
	
	
	public static List<Inspeccion> loadFromDB() {
		return loadFromDB(Inspeccion.getScriptDataBase());
	}
	
	private static String getScriptDataBase(){
		return 	"SELECT *" +
				"FROM "+MainController.getEsquema()+".inspeccion ";
			
		}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","idAvion","fechaInspeccion","tipoInspeccion"});
	}
	
	private static Inspeccion loadFromList(List<String> valores){		
		return new Inspeccion(
							Integer.parseInt(valores.get(0)),
							Integer.parseInt(valores.get(1)),
							Double.parseDouble(valores.get(2)),
							valores.get(3));
	}
	
	private static List<Inspeccion> loadFromDB(String script){
		List<List<String>> inspeccionData = managerDB.executeScript_Query(script, Inspeccion.getFieldScriptBase());
		List<Inspeccion> inspecciones = new ArrayList<Inspeccion>();
		
		for (List<String> list : inspeccionData) {
			inspecciones.add(loadFromList(list));
		}
	return inspecciones;
	}
	
}
