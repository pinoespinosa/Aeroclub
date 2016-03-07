package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import base_datos.managerDB;
import extended.MainController;

public class Cheque {

	private int id;
	private String numeroCheque, bancoCheque, montoCheque, vencimientoCheque;
	
		
	public Cheque() {
		super();
	}
	public Cheque(int id, String numeroCheque, String bancoCheque, String montoCheque, String vencimientoCheque) {
		super();
		this.id = id;
		this.numeroCheque = numeroCheque;
		this.bancoCheque = bancoCheque;
		this.montoCheque = montoCheque;
		this.vencimientoCheque = vencimientoCheque;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumeroCheque() {
		return numeroCheque;
	}
	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}
	public String getBancoCheque() {
		return bancoCheque;
	}
	public void setBancoCheque(String bancoCheque) {
		this.bancoCheque = bancoCheque;
	}
	public String getMontoCheque() {
		return montoCheque;
	}
	public void setMontoCheque(String montoCheque) {
		this.montoCheque = montoCheque;
	}
	public String getVencimientoCheque() {
		return vencimientoCheque;
	}
	public void setVencimientoCheque(String vencimientoCheque) {
		this.vencimientoCheque = vencimientoCheque;
	}


	private static String getScriptDataBase(){
		return 	"	SELECT *" +
				"	FROM "+MainController.getEsquema()+".cheque;";
	}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"id","numeroCheque", "bancoCheque", "montoCheque", "vencimientoCheque"});
	}
	
	private static Cheque loadFromList(List<String> valores){		
		return new Cheque(
							Integer.parseInt(valores.get(0)),
							valores.get(1),
							valores.get(2),
							valores.get(3),
							valores.get(4)
							);
	}
	
	private static List<Cheque> loadFromDB(String script){
		List<List<String>> chequesData = managerDB.executeScript_Query(script, Cheque.getFieldScriptBase());
		List<Cheque> cheques = new ArrayList<Cheque>();
		
		for (List<String> list : chequesData) {
			cheques.add(loadFromList(list));
		}
	return cheques;
	}
	
	public static List<Cheque> loadFromDB(){
		return loadFromDB(Cheque.getScriptDataBase());
	}

	@Override
	public String toString() {
		return bancoCheque + " - $" + montoCheque ;
	}
	
	public static Cheque getChequeById(String id){

		String script = 	"SELECT * " +
							"FROM "+MainController.getEsquema()+".cheque " +
							"WHERE id like '"+id +"' ;";
		
		return loadFromDB(script).get(0);
	}
	
}
