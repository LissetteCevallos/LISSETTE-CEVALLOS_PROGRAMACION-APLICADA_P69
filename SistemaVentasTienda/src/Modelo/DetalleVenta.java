package Modelo;

public class DetalleVenta {

    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    // Constructor
    public DetalleVenta(Producto producto, int cantidad) {

        if (producto == null) {
            throw new IllegalArgumentException(
                "El producto no puede ser nulo");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                "La cantidad debe ser mayor que cero");
        }

        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    // Getters
    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    // Calcular subtotal
    public double calcularSubtotal() {
        return precioUnitario * cantidad;
    }

    // Mostrar detalle
    @Override
    public String toString() {
        return producto.getNombre() +
               " | Cantidad: " + cantidad +
               " | Precio: $" + precioUnitario +
               " | Subtotal: $" + calcularSubtotal();
    }
}
