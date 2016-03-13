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
	
	
	private String tipo, detalle, claseDeGasto;
	private long id;
	private float monto, unidades, total;
	private  Precios.TYPE_PAGO formaPago;
	private double fecha;
	private int idPersona;
	


	public Gasto(long id, String claseDeGasto, String tipo,  float monto, float unidades, String detalle, float total, Precios.TYPE_PAGO formaPago, double fecha, int idPersona) {
		super();
		this.tipo = tipo;
		this.detalle = detalle;
		this.claseDeGasto = claseDeGasto;
		this.id = id;
		this.monto = monto;
		this.unidades = unidades;
		this.total = total;
		this.setFormaPago(formaPago);
		this.fecha = fecha;
		this.idPersona = idPersona;
	}


	public static List<Gasto> loadFromDB(){
		
		String script = "SELECT * FROM "+MainController.getEsquema()+".gasto;";
		List<String> campos = Arrays.asList(new String[]{"id","claseDeGasto","tipo","precio_por_unidad","unidades", "detalle","total","formaPago","fecha","idPersona" });
				
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
							Float.parseFloat(valores.get(3)),
							Float.parseFloat(valores.get(4)),
							valores.get(5),
							Float.parseFloat(valores.get(6)),
							Precios.TYPE_PAGO.values()[Integer.parseInt(valores.get(7))],
							Double.parseDouble(valores.get(8)),
							Integer.parseInt(valores.get(9))
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


	public float getUnidades() {
		return unidades;
	}


	public void setUnidades(float unidades) {
		this.unidades = unidades;
	}


	public String getClaseDeGasto() {
		return claseDeGasto;
	}


	public void setClaseDeGasto(String claseDeGasto) {
		this.claseDeGasto = claseDeGasto;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}

	public double getFecha() {
		return fecha;
	}


	public void setFecha(double fecha) {
		this.fecha = fecha;
	}


	public int getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}


	public Precios.TYPE_PAGO getFormaPago() {
		return formaPago;
	}


	public void setFormaPago(Precios.TYPE_PAGO formaPago) {
		this.formaPago = formaPago;
	}
}
