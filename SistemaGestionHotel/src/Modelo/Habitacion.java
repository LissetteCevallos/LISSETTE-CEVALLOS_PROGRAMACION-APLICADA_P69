
package Modelo;

public class Habitacion {

    // ATRIBUTOS
    private int numero;
    private String tipo;
    private double precio;
    private boolean disponible;

    // CONSTRUCTOR
    public Habitacion(int numero, String tipo, double precio) {

        if (numero <= 0) {
            throw new IllegalArgumentException(
                    "El número de habitación debe ser positivo.");
        }

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Ingrese el tipo de habitación.");
        }

        if (!Double.isFinite(precio) || precio <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor que cero.");
        }

        this.numero = numero;
        this.tipo = tipo;
        this.precio = precio;
        this.disponible = true;
    }

    // GETTERS
    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public double getPrecio() {
        return precio;
    }

    public boolean isDisponible() {
        return disponible;
    }

    // SETTERS
    public void setTipo(String tipo) {

        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El tipo no puede estar vacío.");
        }

        this.tipo = tipo;
    }

    public void setPrecio(double precio) {

        if (!Double.isFinite(precio) || precio <= 0) {
            throw new IllegalArgumentException(
                    "El precio debe ser mayor que cero.");
        }

        this.precio = precio;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // MOSTRAR INFORMACION
    @Override
    public String toString() {

        return "Habitación N.º " + numero
                + " | Tipo: " + tipo
                + " | Precio: $" + precio
                + " | Estado: "
                + (disponible ? "Disponible" : "Ocupada");
    }
}
