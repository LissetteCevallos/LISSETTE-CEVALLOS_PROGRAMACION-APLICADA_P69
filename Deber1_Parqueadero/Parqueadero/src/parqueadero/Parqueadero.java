package parqueadero;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Parqueadero {
	private static final double TARIFA = 0.50;
	
	private ArrayList <Vehiculo> vehiculos;
	private ArrayList <String>caja;
	
	public Parqueadero() {
		vehiculos =GestorArchivos.cargarVehiculos();
		caja = GestorArchivos.cargarCaja();
	}
	public boolean registrarEntrada (String placa) {
		for (Vehiculo v :vehiculos) {
			
			if(v.get_placa().equalsIgnoreCase(placa) && v.Dentro()) {
				return false;
			}//nos preguntamos si la plaa coincide y esta el auto dentro si a esta registrado no se puede registar 2 veces 
			
		}
	
	vehiculos.add(new Vehiculo (placa.toUpperCase()));
	
	GestorArchivos.guardarVehiculos(vehiculos);
	return true;
	
	}
	
	public double registarSalida (String placa) {
		for (Vehiculo v: vehiculos) {
			if(v.get_placa().equalsIgnoreCase(placa)&& v.Dentro()) {
				
				v.set_hora_Salida(LocalDateTime.now());// hora actual salida
				
				double valor = calcularValor(v);
				
				caja.add(v.get_placa()+ ";" +valor + ";"+ v.get_hora_salida());
				
				GestorArchivos.guardarVehiculos(vehiculos);
				GestorArchivos.guardarCaja(caja);
				return valor;
				
			}
		}
		return -1;
		
	}
	
	private double calcularValor(Vehiculo v) {
		long minutos = Duration.between(v.get_hora_entrada(), v.get_hora_salida()).toMinutes();
		
		int horas =(int) Math.ceil( minutos / 60.0);
		
		if(horas ==0) horas =1;
		
		return horas * TARIFA;
		
	}
	
	public void listar_Vehiculos() {
		
		for (Vehiculo v : vehiculos) {
			String estado = v.Dentro() ? "DENTRO" : "SALIO";
			
			System.out.println (v.get_placa() + "|" +v.get_hora_entrada()+ "|"+ estado);
		}
	}
	public double TOTAL () {
		double total =0;
		
		for (String linea :caja) {
			total += Double.parseDouble(linea.split(";")[1]);
		}
		return total;
	}

}
