
package Modelo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {

    // ATRIBUTOS
    private int numeroReserva;
    private Cliente cliente;
    private Habitacion habitacion;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String estado;

    // CONSTRUCTOR
    public Reserva(int numeroReserva, Cliente cliente,
                   Habitacion habitacion,
                   LocalDate fechaEntrada,
                   LocalDate fechaSalida) {

        if (numeroReserva <= 0) {
            throw new IllegalArgumentException(
                    "El número de reserva debe ser positivo.");
        }

        if (cliente == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar un cliente.");
        }

        if (habitacion == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar una habitación.");
        }

        if (fechaEntrada == null || fechaSalida == null) {
            throw new IllegalArgumentException(
                    "Debe ingresar las fechas de la reserva.");
        }

        if (!fechaSalida.isAfter(fechaEntrada)) {
            throw new IllegalArgumentException(
                    "La fecha de salida debe ser posterior "
                    + "a la fecha de entrada.");
        }

        this.numeroReserva = numeroReserva;
        this.cliente = cliente;
        this.habitacion = habitacion;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.estado = "Reservada";
    }

    // GETTERS
    public int getNumeroReserva() {
        return numeroReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public String getEstado() {
        return estado;
    }
    


    // RESTAURAR ESTADO DE UNA RESERVA
    public void restaurarEstado(String estado) {

        if (estado == null ||
                (!estado.equals("Reservada")
                && !estado.equals("Ocupada")
                && !estado.equals("Finalizada"))) {

            throw new IllegalArgumentException(
                    "Estado de reserva no válido.");
        }

        this.estado = estado;
    }



    // CALCULAR NUMERO DE NOCHES
    public long calcularNoches() {

        return ChronoUnit.DAYS.between(
                fechaEntrada, fechaSalida);
    }

    // CALCULAR COSTO TOTAL
    public double calcularTotal() {

        return calcularNoches() * habitacion.getPrecio();
    }

    // REGISTRAR CHECK-IN
    public void realizarCheckIn() {

        if (!estado.equals("Reservada")) {
            throw new IllegalStateException(
                    "La reserva no está pendiente.");
        }

        if (!habitacion.isDisponible()) {
            throw new IllegalStateException(
                    "La habitación está ocupada.");
        }

        estado = "Ocupada";
        habitacion.setDisponible(false);
    }

    // REGISTRAR CHECK-OUT
    public void realizarCheckOut() {

        if (!estado.equals("Ocupada")) {
            throw new IllegalStateException(
                    "La reserva no tiene un check-in activo.");
        }

        estado = "Finalizada";
        habitacion.setDisponible(true);
    }

    // MOSTRAR INFORMACION
    @Override
    public String toString() {

        return "Reserva N.º " + numeroReserva
                + " | Cliente: " + cliente.getNombre()
                + " | Habitación: " + habitacion.getNumero()
                + " | Entrada: " + fechaEntrada
                + " | Salida: " + fechaSalida
                + " | Estado: " + estado
                + " | Total: $"
                + String.format("%.2f", calcularTotal());
    }
}

