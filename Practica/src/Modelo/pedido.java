package Modelo;

import java.util.ArrayList;

public class pedido {
	private int id;
	private String fecha;
	private ArrayList <Detallepedido>detalles;
	
	public pedido(int id, String fecha) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.detalles = new ArrayList<>();
	}
	
	public int get_id() {
		return id;
	}
	public String getfecha() {
		return fecha;
	}
	public ArrayList<Detallepedido> getdetalle(){
		return detalles;
	}
	
	//para  productos en un mismo pedido
	public void agregardetalle(Detallepedido d) {
		detalles.add(d);
	}
	
	//total
	public double Calculo() {
		double total = 0;
		for(Detallepedido d: detalles) {
			total += d.getSubtotal();
		}
		 return total;
		
	}
	public void Resumen() {
		System.out.println( "Pedido # :"+id+" "+fecha);
		for(Detallepedido d:detalles) {
			System.out.printf("%-25s x%d  $%.2f%n ",d.getProducto().getNombre(),d.getCantidad(),d.getSubtotal());
		}
		System.out.printf("TOTAL : $%.2f%n", Calculo());
	}

	
	
}
