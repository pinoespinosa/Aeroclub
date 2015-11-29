package data;

public class Vuelo {
	
	private Long horaInicio, horaFinal;
	
	public Vuelo(Long horaInicio, Long horaFinal) {
		super();
		this.horaInicio = horaInicio;
		this.horaFinal = horaFinal;
	}

	public Long getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Long horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Long getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(Long horaFinal) {
		this.horaFinal = horaFinal;
	}

	@Override
	public String toString() {
		return "Vuelo Hora Despegue:" + horaInicio + ", Hora llegada:" + horaFinal;
	}

}
