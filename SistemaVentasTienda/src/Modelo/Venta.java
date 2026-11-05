
package Modelo;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Venta {

    private int numeroVenta;
    private LocalDateTime fecha;
    private ArrayList<DetalleVenta> detalles;

    // CONSTRUCTOR PARA VENTAS NUEVAS
    public Venta(int numeroVenta) {

        this(numeroVenta, LocalDateTime.now());
    }

    // CONSTRUCTOR PARA RECUPERAR VENTAS GUARDADAS
    public Venta(int numeroVenta, LocalDateTime fecha) {

        if (numeroVenta <= 0) {
            throw new IllegalArgumentException(
                    "El número de venta debe ser positivo.");
        }

        if (fecha == null) {
            throw new IllegalArgumentException(
                    "La fecha de la venta no puede ser nula.");
        }

        this.numeroVenta = numeroVenta;
        this.fecha = fecha;
        this.detalles = new ArrayList<>();
    }

    // GETTERS

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public ArrayList<DetalleVenta> getDetalles() {
        return new ArrayList<>(detalles);
    }

    // AGREGAR DETALLE DE VENTA
    public void agregarDetalle(DetalleVenta detalle) {

        if (detalle == null) {
            throw new IllegalArgumentException(
                    "El detalle no puede ser nulo.");
        }

        detalles.add(detalle);
    }

    // CALCULAR TOTAL DE LA VENTA
    public double calcularTotal() {

        double total = 0;

        for (DetalleVenta detalle : detalles) {
            total += detalle.calcularSubtotal();
        }

        return total;
    }

    // MOSTRAR INFORMACION DE LA VENTA
    @Override
    public String toString() {

        return "Venta N° " + numeroVenta
                + " | Fecha: " + fecha
                + " | Total: $"
                + String.format("%.2f", calcularTotal());
    }
}
