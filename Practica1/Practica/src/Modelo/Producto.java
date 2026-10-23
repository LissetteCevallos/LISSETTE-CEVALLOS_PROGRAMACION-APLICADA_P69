package Modelo;

public class Producto {
	private String codigo;
	private String nombre;
	private double precio;
	private int stock ;
	
	public Producto(String codigo, String nombre ,double precio, int stock) {
		this.codigo = codigo;
		this.nombre=nombre;
		this.precio = precio;
		this.stock =stock;
	}

	public String getNombre() {
		return nombre;
	}

	public String getCodigo() {
		return codigo;
	}
	public double getPrecio() {
		return precio;
	}
	public int getstock() {
		return stock;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPrecio(double precio) {
		this.precio=precio;
	}

	
	 // ¿Hay suficiente stock para esta cantidad?
    public boolean hayStock(int cantidad) {
        return cantidad > 0 && cantidad <= stock;
    }

    // RF-03: suma al stock existente
    public void reponer(int cantidad) {
        if (cantidad > 0) {
            stock += cantidad;
        }
    }

    // RF-07: resta las unidades vendidas
    public void descontar(int cantidad) {
        if (hayStock(cantidad)) {
            stock -= cantidad;
        }
    }
    @Override
    public String toString() {
        return String.format("%-8s %-20s $%7.2f  Stock: %d",
                codigo, nombre, precio, stock);
    }
	
}
