package data;

public class Piloto {

	private int id;
	private String name, apellido;
	private Long nacimiento, fecha_licencia;
	
	
	
	
	public Piloto(int id, String name, String apellido, Long nacimiento,
			Long fecha_licencia) {
		super();
		this.id = id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getApellido() {
		return apellido;
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
	
}
