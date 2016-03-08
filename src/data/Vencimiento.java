package data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import base_datos.managerDB;
import extended.MainController;

public class Vencimiento implements Comparable<Vencimiento>{
	
	private Long fecha;
	private String detalle;
	
		
	public Vencimiento(Long fecha, String detalle) {
		super();
		this.fecha = fecha;
		this.detalle = detalle;
	}


	public static List<Vencimiento> loadFromDB(){
		List<List<String>> vuelosData = managerDB.executeScript_Query(Vencimiento.getScriptDataBase(), Vencimiento.getFieldScriptBase());
		List<Vencimiento> aviones = new ArrayList<Vencimiento>();
		
		for (List<String> list : vuelosData) {
			aviones.add(loadFromList(list));
		}
		
		Collections.sort(aviones);
		
	return aviones;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Long getFecha() {
		return fecha;
	}

	public void setFecha(Long fecha) {
		this.fecha = fecha;
	}

	@Override
	public int compareTo(Vencimiento arg0) {
		if (getFecha()==arg0.getFecha())
			return 0;
		if (getFecha()<arg0.getFecha())
			return -1;
		else
			return 1;
	}
	
	
	
	private static String getScriptDataBase(){
		return 	"	SELECT *" +
				"	FROM "+MainController.getEsquema()+".vencimientosproximos;";
	}
	
	private static List<String> getFieldScriptBase(){
		return Arrays.asList(new String[]{"fecha","detalle"});
	}
	
	private static Vencimiento loadFromList(List<String> valores){		
		return new Vencimiento(
				Long.parseLong(valores.get(0)) , 
				valores.get(1)
				);
	}


	@Override
	public String toString() {
		if (getFecha()<10)
			return "     " + detalle;
		
		Date fechaVencimiento = new Date(getFecha());
		SimpleDateFormat format = new SimpleDateFormat("dd/MMM/YYYY ");		
		return "     " + format.format(fechaVencimiento) + "  -  " + detalle ;
	}
	
	public static String getViewStript(){
		return 	
				"CREATE VIEW `" + MainController.getEsquema() + "`.`vencimientosproximos` AS " +
				"(" +
				"		SELECT 	'1' as fecha, " +
				"				concat('Alerta de stock. ',SUM(unidades),' litros de combustible') as detalle " +
				"		FROM `" + MainController.getEsquema() + "`.gastos_normalizados " +
				"		WHERE tipo like 'COMBUSTIBLE%' " +
				"		GROUP BY TIPO " +
				"		having sum(unidades)<"+MainController.getProperties().get("MINIMO_COMBUSTIBLE") +
				")" +

				"	union " +


				"(" +
				"		SELECT 	'1' as fecha, " +
				"				concat('Alerta de stock. ',SUM(unidades),' litros de aceite') as detalle " +
				"		FROM `" + MainController.getEsquema() + "`.gastos_normalizados " +
				"		WHERE tipo like 'ACEITE%' " +
				"		GROUP BY TIPO " +
				"		having sum(unidades)<"+MainController.getProperties().get("MINIMO_ACEITE") +
				"		) " +

				"	union " +

				"(" +
				"		select 	`vencimientos`.`fecha` AS `fecha`, " +
				"				`vencimientos`.`detalle` AS `detalle` " +
				"		from 	`" + MainController.getEsquema() + "`.`vencimientos` " +
				"" +
				"		where (`vencimientos`.`fecha` is not null)" +
				") " +
				
				"order by `fecha`;";
		
	}
	
}
