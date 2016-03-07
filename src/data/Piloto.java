package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.Utils;
import base_datos.managerDB;
import extended.MainController;

public class Piloto {

	private int id, dni;
	private String name, apellido;
	private Long nacimiento, fecha_licencia;

	public Piloto(int id, String name, String apellido, int dni, Long nacimiento, Long fecha_licencia) {
		super();
		this.id = id;
		this.name = name;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.fecha_licencia = fecha_licencia;
		this.dni = dni;
	}

	public Piloto(int id, String name, String apellido) {
		super();
		this.id = id;
		this.name = name;
		this.apellido = apellido;
	}

	public Piloto(int id) {
		super();
		this.id = id;
	}

	public Piloto() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Utils.toCamelCase(name);
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApellido() {
		return Utils.toCamelCase(apellido);
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Long getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Long nacimiento) {
		this.nacimiento = nacimiento;
	}
	public Long getFecha_licencia() {
		return fecha_licencia;
	}
	public void setFecha_licencia(Long fecha_licencia) {
		this.fecha_licencia = fecha_licencia;
	}
	public static List<Piloto> loadFromDB() {
		return loadFromDB(Piloto.getScriptDataBase());
	}
	@Override
	public String toString() {
		return getApellido() + ", " + getName();
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Piloto) {
			if (((Piloto) arg0).getId() == getId())
				return true;

			if (((Piloto) arg0).getDni() == getDni())
				return true;
			else
				return false;

		} else
			return false;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}
	
	public static Piloto getPilotoById(String id){

		String script = "SELECT DISTINCT pe.*, pi.fechaVencimientoLicencia " +
						"FROM "+MainController.getEsquema()+".piloto as pi " +
						"INNER JOIN "+MainController.getEsquema()+".persona as pe on pe.id = pi.id " +
						"WHERE pi.id =" + id + " " +
						"ORDER BY apellido;";
		return loadFromDB(script).get(0);
	}
	
	/***************************************************** Metodos privados ***********************************************************************/
		
	private static String getScriptDataBase(){
		return 	"SELECT DISTINCT pe.*, pi.fechaVencimientoLicencia " +
				"FROM "+MainController.getEsquema()+".piloto as pi " +
				"inner join "+MainController.getEsquema()+".persona as pe on pe.id = pi.id " +
				"order by apellido;";
		}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","nombre","apellido","dni", "nacimiento", "fechaVencimientoLicencia"});
	}
	
	private static Piloto loadFromList(List<String> valores){		
		return new Piloto(
							Integer.parseInt(valores.get(0)),
							valores.get(1),
							valores.get(2),
							Integer.parseInt(valores.get(3)),
							Long.parseLong(valores.get(4)),
							Long.parseLong(valores.get(5)));
	}
	
	private static List<Piloto> loadFromDB(String script){
		List<List<String>> pilotosData = managerDB.executeScript_Query(script, Piloto.getFieldScriptBase());
		List<Piloto> pilotos = new ArrayList<Piloto>();
		
		for (List<String> list : pilotosData) {
			pilotos.add(loadFromList(list));
		}
	return pilotos;
	}
		
	
	
}
