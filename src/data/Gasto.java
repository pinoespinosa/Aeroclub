package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import base_datos.managerDB;
import extended.MainController;

public class Gasto implements Comparable<Gasto>{

	public static final int GASTO = 0,
							ACTIVACION_BIEN_DE_USO=1;
	
	
	private String tipo, detalle;
	private long id;
	private float monto;
	
	
	public Gasto(long id, String tipo, String detalle,  float monto) {
		super();
		this.tipo = tipo;
		this.detalle = detalle;
		this.id = id;
		this.monto = monto;
	}


	public static List<Gasto> loadFromDB(){
		
		
		String script = "SELECT * FROM "+MainController.getEsquema()+".gasto;";
		List<String> campos = Arrays.asList(new String[]{"id","tipo", "detalle", "monto"});
				
		List<List<String>> vuelosData = managerDB.executeScript_Query(script,campos);

		
		List<Gasto> gastos = new ArrayList<Gasto>();
		
		for (List<String> list : vuelosData) {
			gastos.add(loadFromList(list));
		}
		
		Collections.sort(gastos);
		
		
	return gastos;
	}
	
	
	private static Gasto loadFromList(List<String> valores){		
		return new Gasto(
							Long.parseLong(valores.get(0)),
							valores.get(1),
							valores.get(2),
							Float.parseFloat(valores.get(3))
							);
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public String getDetalle() {
		return detalle;
	}


	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public float getMonto() {
		return monto;
	}


	public void setMonto(float monto) {
		this.monto = monto;
	}


	@Override
	public int compareTo(Gasto arg0) {
		return 0;
	}
}
