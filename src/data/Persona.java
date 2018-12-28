package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.Utils;
import base_datos.managerDB;
import extended.MainController;

public class Persona {

	private int id, dni;
	private String name, apellido;
	private Long nacimiento;

	public Persona(int id, String name, String apellido, int dni, Long nacimiento) {
		super();
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
	}

	public Persona(int dni) {
		super();
		this.dni = dni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	@Override
	public String toString() {
		return getApellido() + ", " + getName() + " [dni:" + getDni() + "]";
	}

	public String toStringSimple() {
		return getApellido() + ", " + getName();
	}
	
	private static String getScriptDataBase() {
		return "SELECT DISTINCT pe.*" + " FROM " + MainController.getEsquema() + ".persona as pe";
	}

	private static String getScriptDataBaseID(String id) {
		return "SELECT DISTINCT pe.*" + " FROM " + MainController.getEsquema() + ".persona as pe WHERE pe.id = " + id;
	}

	private static List<String> getFieldScriptBase() {
		return Arrays.asList(new String[]{"id", "nombre", "apellido", "dni", "nacimiento"});
	}

	public static List<Persona> loadFromDB() {
		return loadFromDBPersona(getScriptDataBase());
	}

	public static Persona getPersonaById(String id) {
		
		List<List<String>> personasData = managerDB.executeScript_Query(getScriptDataBaseID(id), Persona.getFieldScriptBase());
		List<Persona> personas = new ArrayList<Persona>();

		for (List<String> list : personasData) {
				personas.add(loadFromList(list));
		}

		return personas.get(0);
	}

	private static Persona loadFromList(List<String> valores) {
		return new Persona(Integer.parseInt(valores.get(0)), valores.get(1), valores.get(2), Integer.parseInt(valores.get(3)), Long.parseLong(valores.get(4)));
	}

	private static List<Persona> loadFromDBPersona(String script) {
		List<List<String>> personasData = managerDB.executeScript_Query(script, Persona.getFieldScriptBase());
		List<Persona> personas = new ArrayList<Persona>();

		for (List<String> list : personasData) {
			if (Integer.parseInt(list.get(3)) > 0)
				personas.add(loadFromList(list));
		}

		return personas;
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Persona) {

			if (((Persona) arg0).getDni() == getDni())
				return true;

			if (((Persona) arg0).getId() == getId())
				return true;
			else
				return false;
		} else
			return false;

	}

}
