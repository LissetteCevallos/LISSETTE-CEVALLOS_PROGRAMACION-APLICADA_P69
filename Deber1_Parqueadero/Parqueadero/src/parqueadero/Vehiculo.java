package parqueadero;

import java.time.LocalDateTime; 

public class Vehiculo {
	private String placa;
	private LocalDateTime horaEntrada;
	private LocalDateTime horaSalida;
	
	public Vehiculo(String placa) {
		this.placa =placa;
		this.horaEntrada = LocalDateTime.now();
	}
	
	public Vehiculo( String placa,LocalDateTime entrada , LocalDateTime salida) {
		this.placa =placa;
		this.horaEntrada =entrada;
		this.horaSalida = salida;
		
	}
	
	public String get_placa() {
		return placa;
	}
	
	public LocalDateTime get_hora_entrada() {
		return horaEntrada;
	}
	public LocalDateTime get_hora_salida() {
		return horaSalida;
	}
	
	public void set_hora_Salida (LocalDateTime horaSalida) {
		this.horaSalida =horaSalida;
	}
	
	public boolean Dentro() {
		return horaSalida == null;
		
	}

}
