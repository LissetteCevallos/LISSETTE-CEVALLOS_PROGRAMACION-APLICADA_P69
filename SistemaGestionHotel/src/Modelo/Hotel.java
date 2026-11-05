
package Modelo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Hotel {

    // ATRIBUTOS
    private ArrayList<Habitacion> habitaciones;
    private ArrayList<Cliente> clientes;
    private ArrayList<Reserva> reservas;

    // CONSTRUCTOR
    public Hotel() {
        habitaciones = new ArrayList<>();
        clientes = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    // AGREGAR HABITACION
    public boolean agregarHabitacion(Habitacion habitacion) {

        if (habitacion == null ||
                buscarHabitacion(habitacion.getNumero()) != null) {
            return false;
        }

        habitaciones.add(habitacion);
        return true;
    }

    // BUSCAR HABITACION
    public Habitacion buscarHabitacion(int numero) {

        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNumero() == numero) {
                return habitacion;
            }
        }

        return null;
    }

    // ELIMINAR HABITACION
    public boolean eliminarHabitacion(int numero) {

        Habitacion habitacion = buscarHabitacion(numero);

        if (habitacion == null) {
            return false;
        }

        // CONSERVAR HABITACIONES CON RESERVAS
        for (Reserva reserva : reservas) {
            if (reserva.getHabitacion().getNumero() == numero) {
                return false;
            }
        }

        return habitaciones.remove(habitacion);
    }

    // LISTAR HABITACIONES
    public ArrayList<Habitacion> listarHabitaciones() {
        return new ArrayList<>(habitaciones);
    }

    // AGREGAR CLIENTE
    public boolean agregarCliente(Cliente cliente) {

        if (cliente == null ||
                buscarCliente(cliente.getCedula()) != null) {
            return false;
        }

        clientes.add(cliente);
        return true;
    }

    // BUSCAR CLIENTE
    public Cliente buscarCliente(String cedula) {

        if (cedula == null) {
            return null;
        }

        for (Cliente cliente : clientes) {
            if (cliente.getCedula().equals(cedula.trim())) {
                return cliente;
            }
        }

        return null;
    }

    // LISTAR CLIENTES
    public ArrayList<Cliente> listarClientes() {
        return new ArrayList<>(clientes);
    }

    // COMPROBAR DISPONIBILIDAD POR FECHAS
    public boolean habitacionDisponible(
            int numero,
            LocalDate entrada,
            LocalDate salida) {

        Habitacion habitacion = buscarHabitacion(numero);

        if (habitacion == null ||
                entrada == null ||
                salida == null ||
                !salida.isAfter(entrada)) {
            return false;
        }

        for (Reserva reserva : reservas) {

            if (reserva.getHabitacion().getNumero() == numero
                    && !reserva.getEstado().equals("Finalizada")
                    && !reserva.getEstado().equals("Cancelada")) {

                boolean coinciden =
                        entrada.isBefore(reserva.getFechaSalida())
                        && salida.isAfter(reserva.getFechaEntrada());

                if (coinciden) {
                    return false;
                }
            }
        }

        return true;
    }

    // REGISTRAR RESERVA
    public boolean agregarReserva(Reserva reserva) {

        if (reserva == null) {
            return false;
        }

        // EVITAR NUMEROS DE RESERVA REPETIDOS
        if (buscarReserva(reserva.getNumeroReserva()) != null) {
            return false;
        }

        // COMPROBAR QUE EL CLIENTE EXISTA
        Cliente cliente = buscarCliente(
                reserva.getCliente().getCedula());

        if (cliente == null) {
            return false;
        }

        // COMPROBAR QUE LA HABITACION EXISTA
        Habitacion habitacion = buscarHabitacion(
                reserva.getHabitacion().getNumero());

        if (habitacion == null) {
            return false;
        }

        // COMPROBAR QUE SEAN LOS OBJETOS REGISTRADOS
        if (cliente != reserva.getCliente()
                || habitacion != reserva.getHabitacion()) {
            return false;
        }

        // COMPROBAR DISPONIBILIDAD
        if (!habitacionDisponible(
                habitacion.getNumero(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida())) {
            return false;
        }

        reservas.add(reserva);
        return true;
    }

    // BUSCAR RESERVA
    public Reserva buscarReserva(int numeroReserva) {

        for (Reserva reserva : reservas) {
            if (reserva.getNumeroReserva() == numeroReserva) {
                return reserva;
            }
        }

        return null;
    }

    // LISTAR RESERVAS
    public ArrayList<Reserva> listarReservas() {
        return new ArrayList<>(reservas);
    }

    // REALIZAR CHECK-IN
    public boolean realizarCheckIn(int numeroReserva) {

        Reserva reserva = buscarReserva(numeroReserva);

        if (reserva == null ||
                !reserva.getEstado().equals("Reservada")) {
            return false;
        }

        try {
            reserva.realizarCheckIn();
            return true;

        } catch (IllegalStateException e) {
            return false;
        }
    }

    // REALIZAR CHECK-OUT
    public boolean realizarCheckOut(int numeroReserva) {

        Reserva reserva = buscarReserva(numeroReserva);

        if (reserva == null ||
                !reserva.getEstado().equals("Ocupada")) {
            return false;
        }

        try {
            reserva.realizarCheckOut();
            return true;

        } catch (IllegalStateException e) {
            return false;
        }
    }

    // RECUPERAR RESERVAS GUARDADAS
    public boolean cargarReserva(Reserva reserva) {

        if (reserva == null) {
            return false;
        }

        // EVITAR NUMEROS REPETIDOS
        if (buscarReserva(reserva.getNumeroReserva()) != null) {
            return false;
        }

        // VERIFICAR CLIENTE Y HABITACION
        Cliente cliente = buscarCliente(
                reserva.getCliente().getCedula());

        Habitacion habitacion = buscarHabitacion(
                reserva.getHabitacion().getNumero());

        if (cliente == null || habitacion == null) {
            return false;
        }

        if (cliente != reserva.getCliente()
                || habitacion != reserva.getHabitacion()) {
            return false;
        }

        // COMPROBAR CONFLICTOS CON RESERVAS ACTIVAS
        if (!reserva.getEstado().equals("Finalizada")) {

            for (Reserva existente : reservas) {

                if (existente.getHabitacion().getNumero()
                        == habitacion.getNumero()
                        && !existente.getEstado().equals("Finalizada")) {

                    boolean coinciden =
                            reserva.getFechaEntrada().isBefore(
                                    existente.getFechaSalida())
                            && reserva.getFechaSalida().isAfter(
                                    existente.getFechaEntrada());

                    if (coinciden) {
                        return false;
                    }
                }
            }
        }

        reservas.add(reserva);
        return true;
    }
}
