package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import base_datos.Utils;
import base_datos.managerDB;
import extended.MainController;

public class horas_vendida_adelantado {

	private String id;
	private int pilotoId, avionId, formaDePago;
	private long fechaVencimiento, fecha;
	private float saldo, saldoOriginal, cantidadHoras;

	
	public horas_vendida_adelantado() {
		super();
	}

	public horas_vendida_adelantado(String id, int pilotoId, int avionId, float cantidadHoras, long fechaVencimiento, float saldo, float saldoOriginal, int formaDePago, long fecha) {
		super();
		this.id = id;
		this.pilotoId = pilotoId;
		this.avionId = avionId;
		this.formaDePago = formaDePago;
		this.fechaVencimiento = fechaVencimiento;
		this.saldo = saldo;
		this.saldoOriginal = saldoOriginal;
		this.cantidadHoras = cantidadHoras;
		this.setFecha(fecha);
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getPilotoId() {
		return pilotoId;
	}
	public void setPilotoId(int pilotoId) {
		this.pilotoId = pilotoId;
	}
	public int getAvionId() {
		return avionId;
	}
	public void setAvionId(int avionId) {
		this.avionId = avionId;
	}
	public float getCantidadHoras() {
		return cantidadHoras;
	}
	public void setCantidadHoras(float cantidadHoras) {
		this.cantidadHoras = cantidadHoras;
	}
	public long getFechaVencimiento() {
		return fechaVencimiento;
	}
	public void setFechaVencimiento(long fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public int getFormaDePago() {
		return formaDePago;
	}

	public void setFormaDePago(int formaDePago) {
		this.formaDePago = formaDePago;
	}

	public float getSaldoOriginal() {
		return saldoOriginal;
	}

	public void setSaldoOriginal(float saldoOriginal) {
		this.saldoOriginal = saldoOriginal;
	}
	
	private static String getScriptDataBase() {
		return "	SELECT *" + "	FROM " + MainController.getEsquema() + ".horas_vendida_adelantado;";
	}

	private static List<String> getFieldScriptBase() {
		return Arrays.asList(new String[]{"id", "pilotoId", "avionId", "cantidadHoras", "fechaVencimiento", "saldo", "saldoOriginal", "formaDePago", "fecha"});
	}

	private static horas_vendida_adelantado loadFromList(List<String> valores) {
		return new horas_vendida_adelantado(
				valores.get(0),
				Integer.parseInt(valores.get(1)), 
				Integer.parseInt(valores.get(2)), 
				Float.parseFloat(valores.get(3)), 
				Long.parseLong(valores.get(4)), 
				Float.parseFloat(valores.get(5)), 
				Float.parseFloat(valores.get(6)), 
				Integer.parseInt(valores.get(7)),
				Long.parseLong(valores.get(8)));
	}

	private static List<horas_vendida_adelantado> loadFromDB(String script) {
		List<List<String>> horasData = managerDB.executeScript_Query(script, getFieldScriptBase());
		List<horas_vendida_adelantado> horas = new ArrayList<horas_vendida_adelantado>();

		for (List<String> list : horasData) {
			horas.add(loadFromList(list));
		}
		return horas;
	}

	/**
	 * Resta a un vale de horas la cantidad de horas voladas. Si se voló más de lo que tiene el ticket retorna la cantidad faltante, sino retorna 0. 
	 * @param minutosVolados
	 * @return
	 */
	public float quitarHoraVolada(float minutosVolados) {

		// TODO ACOMODAR BIEN LA LOGICA

		float horasVoladas = minutosVolados / 60;

		if (Utils.alredyOcurred(new Date(getFechaVencimiento()))) {

			Avion avion = Avion.getAvionById(getAvionId() + "");
			float saldoConsumido = avion.getPrecio() * (minutosVolados / 60);

			if (saldoConsumido > getSaldo()) {

				float horasPagadas = getSaldo() / avion.getPrecio();
				setSaldo(0);
				setCantidadHoras(0);
				return horasVoladas - horasPagadas;
			} 
			else{
				float horasReAjustadas = (getSaldo() - saldoConsumido) / avion.getPrecio();
				setCantidadHoras(horasReAjustadas);
				setSaldo(getSaldo() - avion.getPrecio() * (minutosVolados / 60));
				return 0;
			}
		} 
		else 
		{
			if (getCantidadHoras() > horasVoladas) {
				setCantidadHoras(getCantidadHoras() - horasVoladas);
				float precioHora = getSaldo() / getCantidadHoras();
				setSaldo(getSaldo() - precioHora * (minutosVolados / 60));
				return 0;
			} 
			else 
			{
				setSaldo(0);
				setCantidadHoras(0);
				return horasVoladas - getCantidadHoras();
			}
		}

	}
	public static List<horas_vendida_adelantado> loadFromDB() {

		List<horas_vendida_adelantado> horas = loadFromDB(getScriptDataBase());

		for (horas_vendida_adelantado hora : horas) {
			if (Utils.minutesBetweenDates(new Date(System.currentTimeMillis()), new Date(hora.getFechaVencimiento())) < 0) {
				Avion avion = Avion.getAvionById(hora.getAvionId() + "");

				float horasReAjustadas = hora.getSaldo() / avion.getPrecio();
				hora.setCantidadHoras(horasReAjustadas);
			}
		}

		return horas;
	}

	public static List<horas_vendida_adelantado> getHorasByAvionPiloto(String avionId, String pilotoId) {

		String script = "SELECT * " + "FROM " + MainController.getEsquema() + ".horas_vendida_adelantado " + "WHERE pilotoId like '" + pilotoId + "' and avionId like '" + avionId + "'and cantidadHoras<>0 ORDER BY fechaVencimiento;";

		return loadFromDB(script);

	}
	public static long getMinutosAdelantados(List<horas_vendida_adelantado> horas) {

		long total = 0;
		for (horas_vendida_adelantado horasAdelantadas : horas) {
			total += horasAdelantadas.getCantidadHoras() * 60;
		}
		return total;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

	
}
