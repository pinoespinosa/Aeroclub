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
	private Long nacimiento_persona, fecha_psicofisico;
	private float precio;

	public Instructor(int id, String name, String apellido, Long nacimiento, Long fecha_psicofisico, int dni,
			float precio) {
		super();
		this.id = id;
		this.name_persona = name;
		this.apellido_persona = apellido;
		this.nacimiento_persona = nacimiento;
		this.setFecha_psicofisico(fecha_psicofisico);
		this.setDni(dni);
		this.setPrecio(precio);
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

	public int getDni() {
		return dni_persona;
	}

	public void setDni(int dni) {
		this.dni_persona = dni;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Long getFecha_psicofisico() {
		return fecha_psicofisico;
	}

	public void setFecha_psicofisico(Long fecha_psicofisico) {
		this.fecha_psicofisico = fecha_psicofisico;
	}

	public static List<Instructor> loadFromDB() {

		List<Instructor> all = loadFromDB(Instructor.getScriptDataBase());
		List<Instructor> vigentes = new ArrayList<Instructor>();

		for (Instructor instructor : all) {
			if (instructor.getDni() > 0)
				vigentes.add(instructor);
		}

		return vigentes;
	}

	private static List<Instructor> loadFromDB(String script) {
		List<List<String>> instructoresData = managerDB.executeScript_Query(script, Instructor.getFieldScriptBase());
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
		if (arg0 instanceof Instructor) {
			if (((Instructor) arg0).getId() == getId())
				return true;
			if (((Instructor) arg0).getDni() == getDni())
				return true;
			else
				return false;
		} else
			return false;
	}

	public static Instructor getInstructorById(String id) {
		if (id.equals("-1"))
			return new Instructor(-1, "Sin instructor", "", new Long(-1), new Long(-1), -1, new Float(0));
		String script = "SELECT DISTINCT pe.*, inst.* " + "FROM " + MainController.getEsquema() + ".instructor as inst "
				+ "inner join " + MainController.getEsquema() + ".persona as pe " + "on pe.id=inst.id and pe.id like '"
				+ id + "' " + "order by apellido;";
		return loadFromDB(script).get(0);
	}

	/*****************************************************
	 * Metodos privados
	 ***********************************************************************/

	private static String getScriptDataBase() {
		return "SELECT DISTINCT pe.*, inst.* " + "FROM " + MainController.getEsquema() + ".instructor as inst "
				+ "inner join " + MainController.getEsquema() + ".persona as pe " + "on pe.id=inst.id "
				+ "order by apellido;";
	}

	private static List<String> getFieldScriptBase() {
		return Arrays.asList(
				new String[] { "id", "nombre", "apellido", "nacimiento", "fecha_psicofisico", "dni", "precio" });
	}

	private static Instructor loadFromList(List<String> valores) {
		return new Instructor(Integer.parseInt(valores.get(0)), valores.get(1), valores.get(2),
				Long.parseLong(valores.get(3)), Long.parseLong(valores.get(4)), Integer.parseInt(valores.get(5)),
				Float.parseFloat(valores.get(6)));
	}

	public static int getCostoBriefing() {
		List<String> campo = Arrays.asList(new String[] { "costos" });
		String script = "SELECT costos FROM " + MainController.getEsquema() + ".costos WHERE elemento like 'briefing';";
		return Integer.parseInt(managerDB.executeScript_Query(script, campo).get(0).get(0));
	}

	public static int getCostoDeBriefing() {
		List<String> campo = Arrays.asList(new String[] { "costos" });
		String script = "SELECT costos FROM " + MainController.getEsquema()
				+ ".costos WHERE elemento like 'de-briefing';";
		return Integer.parseInt(managerDB.executeScript_Query(script, campo).get(0).get(0));
	}

	public static void updateCostoDeBriefing(int costo) {
		String script = "UPDATE " + MainController.getEsquema() +".costos SET `costos`='" + costo
				+ "' WHERE `elemento`='de-briefing'";
		managerDB.executeScript_Void(script);
	}

	public static void updateCostoBriefing(int costo) {
		String script = "UPDATE " + MainController.getEsquema() +".costos SET `costos`='" + costo
				+ "' WHERE `elemento`='briefing'";
		managerDB.executeScript_Void(script);
	}

}
