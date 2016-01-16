package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import base_datos.managerDB;
import extended.MainController;


public class Vuelo implements Comparable<Vuelo>{

	private int id;

	private long horaInicio, horaFinal;

	private int cantAceite, cantCombustible;

	private int idAvion, idPiloto, idInstructor, formaDePago;

	
	
	/**
	 * En el momento que se crea el vuelo se guardan los precios y no se pueden modificar mas adelante
	 */
	private float precio, precioAceite, precioCombustible, precioAvion;
	
	public Vuelo(float precioAceite, float precioCombustible, float precioAvion) {
		super();
		this.precioAceite = precioAceite;
		this.precioCombustible = precioCombustible;
		this.setPrecioAvion(precioAvion);
	}



	public Vuelo(int id, long horaInicio, long horaFinal, int cantAceite, int cantCombustible, int idAvion, int idPiloto, int idInstructor,
			float precio, float precioAceite, float precioCombustible, float precioAvion, int formaDePago) {
		super();
		this.id = id;
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
		this.cantAceite = cantAceite;
		this.cantCombustible = cantCombustible;
		this.idAvion = idAvion;
		this.idPiloto = idPiloto;
		this.idInstructor = idInstructor;
		this.formaDePago = formaDePago;
		this.precio = precio;
		this.precioAceite = precioAceite;
		this.precioCombustible = precioCombustible;
		this.setPrecioAvion(precioAvion);
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public long getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(long horaFinal) {
		this.horaFinal = horaFinal;
	}

	public int getCantAceite() {
		return cantAceite;
	}

	public void setCantAceite(int cantAceite) {
		this.cantAceite = cantAceite;
	}

	public int getCantCombustible() {
		return cantCombustible;
	}

	public void setCantCombustible(int cantCombustible) {
		this.cantCombustible = cantCombustible;
	}

	public int getIdAvion() {
		return idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public int getIdPiloto() {
		return idPiloto;
	}

	public void setIdPiloto(int idPiloto) {
		this.idPiloto = idPiloto;
	}

	public int getIdInstructor() {
		return idInstructor;
	}

	public void setIdInstructor(int idInstructor) {
		this.idInstructor = idInstructor;
	}
	
	@Override
	public String toString() {
		
		SimpleDateFormat format = new SimpleDateFormat("HH:mm dd/MMM ");
		
		Date inicio = new Date(horaInicio);
		Date fin = new Date(horaFinal);
		
		String id;
		
		if (getId()<10)
			id="00"+getId();
		else
			if (getId()>9 && getId()<100)
				id="0"+getId();
			else
				id=""+getId();
			
		return "Vuelo "+ id +" - " + format.format(inicio) + "~ " + format.format(fin);
	}

	public float getPrecio() {
		return precio;
	}	
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	

	
	public String getCreateScriptDataBase(){
		return "INSERT INTO `"+MainController.getEsquema()+"`.`vuelo` values ( " +
				" " + getId()  +
				", " + getHoraInicio() +
				", " + getHoraFinal() +
				", " + getCantAceite() +
				", " + getCantCombustible() +
				", " + getIdAvion() +
				", " + getIdPiloto() + 
				", " + getIdInstructor() +
				", " + getPrecio() +
				", " + getPrecioAceite() +
				", " + getPrecioCombustible() +
				", " + getPrecioAvion() +
				", " + getFormaDePago() +
				 ");"; 
	}

	public float getPrecioAceite() {
		return precioAceite;
	}

	public void setPrecioAceite(float precioAceite) {
		this.precioAceite = precioAceite;
	}

	public float getPrecioCombustible() {
		return precioCombustible;
	}

	public void setPrecioCombustible(float precioCombustible) {
		this.precioCombustible = precioCombustible;
	}
	
	
	
	private static String getScriptDataBase(){
		return 	"	SELECT *" +
				"	FROM "+MainController.getEsquema()+".vuelo;";
	}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","horaInicio", "horaFinal", "cantAceite", "cantCombustible", "idAvion", "idPiloto", "idInstructor", "precio", "precioAceite", "precioCombustible","precioAvion","formaDePago"});
	}
	
	private static Vuelo loadFromList(List<String> valores){		
		return new Vuelo(
							Integer.parseInt(valores.get(0)),
							Long.parseLong(valores.get(1)),
							Long.parseLong(valores.get(2)),
							Integer.parseInt(valores.get(3)),
							Integer.parseInt(valores.get(4)),
							Integer.parseInt(valores.get(5)),
							Integer.parseInt(valores.get(6)),
							Integer.parseInt(valores.get(7)),
							Float.parseFloat(valores.get(8)),
							Float.parseFloat(valores.get(9)),
							Float.parseFloat(valores.get(10)),
							Float.parseFloat(valores.get(11)),
							Integer.parseInt(valores.get(12)));
	}
	
	public static List<Vuelo> loadFromDB(){
		List<List<String>> vuelosData = managerDB.executeScript_Query(Vuelo.getScriptDataBase(), Vuelo.getFieldScriptBase());
		List<Vuelo> aviones = new ArrayList<Vuelo>();
		
		for (List<String> list : vuelosData) {
			aviones.add(loadFromList(list));
		}
		
		Collections.sort(aviones);
		
	return aviones;
	}

	@Override
	public int compareTo(Vuelo arg0) {
		if (getId()==arg0.getId())
			return 0;
		if (getId()<arg0.getId())
			return 1;
		else
			return -1;
	}

	public int getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(int formaDePago) {
		this.formaDePago = formaDePago;
	}



	public float getPrecioAvion() {
		return precioAvion;
	}



	public void setPrecioAvion(float precioAvion) {
		this.precioAvion = precioAvion;
	}
	


}
