package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.Utils;
import base_datos.managerDB;

public class Instructor {

	private int id;
	private String name, apellido;
	private Long nacimiento, fecha_licencia;
	
	
	
	
	public Instructor(int id, String name, String apellido, Long nacimiento,
			Long fecha_licencia) {
		super();
		this.id = id;
		this.name = name;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.fecha_licencia = fecha_licencia;
	}
	
	public Instructor(int id, String name, String apellido) {
		super();
		this.id = id;
		this.name = name;
		this.apellido = apellido;
	}
	
	public Instructor(int id) {
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
		
	private static String getScriptDataBase(){
		return 	"SELECT DISTINCT pe.* " +
				"FROM aviones.instructor as inst " +
				"inner join aviones.persona as pe " +
				"on pe.id=inst.id;";
	}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","nombre","apellido"});
	}
	
	private static Instructor loadFromList(List<String> valores){		
		return new Instructor(
							Integer.parseInt(valores.get(0)),
							valores.get(1),
							valores.get(2));
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
		return getApellido() + ", " + getName();
	}

	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Instructor)
			return ((Instructor) arg0).getId() == getId();
		else
			return false;
	}
	
	
}
