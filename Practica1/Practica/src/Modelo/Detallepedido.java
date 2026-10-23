package Modelo;

public class Detallepedido {
	private Producto producto;
	private int cantidad;
	private double precio_unit; //guarda precio al vender
	
	public Detallepedido(Producto producto, int cantidad, double precio) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.precio_unit = precio;
	}
	
	public Producto getProducto () {
		return producto;
	}
	public int getCantidad() {
		return cantidad;
	}
	public double getPrecio_unit() {
		return precio_unit;
	}
	
	//total =precio*cantidad
	
	public double getSubtotal() {
		return precio_unit *cantidad;
	}
	
}
