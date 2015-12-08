package data;

import base_datos.Utils;


public class Persona {

	private int id, dni;
	private String name, apellido;
	private Long nacimiento, fecha_licencia;

	public Persona(int id, int dni, String name, String apellido,
			Long nacimiento, Long fecha_licencia) {
		super();
		this.id = id;
		this.dni = dni;
		this.name = name;
		this.apellido = apellido;
		this.nacimiento = nacimiento;
		this.fecha_licencia = fecha_licencia;
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

	public Long getFecha_licencia() {
		return fecha_licencia;
	}

	public void setFecha_licencia(Long fecha_licencia) {
		this.fecha_licencia = fecha_licencia;
	}

	@Override
	public String toString() {
		return getApellido() + ", " + getName();
	}
}
