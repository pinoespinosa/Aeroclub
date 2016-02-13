package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.Utils;
import base_datos.managerDB;
import extended.MainController;

public class Instructor {

	private int id, dni_persona;
	private String name_persona, apellido_persona;
	private Long nacimiento_persona, fecha_licencia;
	private float precio;
	
	
	
	public Instructor(int id, String name, String apellido, Long nacimiento,
			Long fecha_licencia, int dni, float precio) {
		super();
		this.id = id;
		this.name_persona = name;
		this.apellido_persona = apellido;
		this.nacimiento_persona = nacimiento;
		this.fecha_licencia = fecha_licencia;
		this.setDni(dni);
		this.precio=precio;
	}
	
	public Instructor(int id, String name, String apellido) {
		super();
		this.id = id;
		this.name_persona = name;
		this.apellido_persona = apellido;
	}
	
	public Instructor(int id) {
		super();
		this.id = id;
	}
	
	public Instructor() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Utils.toCamelCase(name_persona);
	}
	public void setName(String name) {
		this.name_persona = name;
	}
	public String getApellido() {
		return Utils.toCamelCase(apellido_persona);
	}
	public void setApellido(String apellido) {
		this.apellido_persona = apellido;
	}
	public Long getNacimiento() {
		return nacimiento_persona;
	}
	public void setNacimiento(Long nacimiento) {
		this.nacimiento_persona = nacimiento;
	}
	public Long getFecha_licencia() {
		return fecha_licencia;
	}
	public void setFecha_licencia(Long fecha_licencia) {
		this.fecha_licencia = fecha_licencia;
	}
		
	private static String getScriptDataBase(){
		return 	"SELECT DISTINCT pe.*, inst.* " +
				"FROM "+MainController.getEsquema()+".instructor as inst " +
				"inner join "+MainController.getEsquema()+".persona as pe " +
				"on pe.id=inst.id " +
				"order by apellido;";
	}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","nombre","apellido","nacimiento", "fecha_psicofisico", "dni", "precio"});
	}
	
	private static Instructor loadFromList(List<String> valores){		
		return new Instructor(
							Integer.parseInt(valores.get(0)),
							valores.get(1),
							valores.get(2),
							Long.parseLong(valores.get(3)),
							Long.parseLong(valores.get(4)),
							Integer.parseInt(valores.get(5)),
							Float.parseFloat(valores.get(6)));
	}
	
	public static List<Instructor> loadFromDB(){
		List<List<String>> instructoresData = managerDB.executeScript_Query(Instructor.getScriptDataBase(), Instructor.getFieldScriptBase());
		List<Instructor> instructores = new ArrayList<Instructor>();
		
		for (List<String> list : instructoresData) {
			instructores.add(loadFromList(list));
		}
	return instructores;
	}
	
	
	@Override
	public String toString() {
		if (getName().equals("Sin instructor"))
			return getName();
		else
		return getApellido() + ", " + getName();
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Instructor){
			if (((Instructor) arg0).getId() == getId())
				return true;
			if (((Instructor) arg0).getDni() == getDni())
				return true;
			else
				return false;
		}
		else
			return false;
	}

	public int getDni() {
		return dni_persona;
	}

	public void setDni(int dni) {
		this.dni_persona = dni;
	}
	
	
}
