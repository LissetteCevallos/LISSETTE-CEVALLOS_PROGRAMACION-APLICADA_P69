
package Controlador;

import Modelo.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class HotelControlador {

    private Hotel hotel;
    private ArchivoHotel archivo;

    // CONSTRUCTOR
    public HotelControlador() {
        hotel = new Hotel();
        archivo = new ArchivoHotel();
        cargarDatos();
    }

    // CARGAR DATOS GUARDADOS
    private void cargarDatos() {

        try {

            for (Habitacion habitacion :
                    archivo.cargarHabitaciones()) {

                if (!hotel.agregarHabitacion(habitacion)) {
                    throw new IOException(
                            "Habitación duplicada.");
                }
            }

            for (Cliente cliente :
                    archivo.cargarClientes()) {

                if (!hotel.agregarCliente(cliente)) {
                    throw new IOException(
                            "Cliente duplicado.");
                }
            }

            for (Reserva reserva :
                    archivo.cargarReservas(hotel)) {

                if (!hotel.cargarReserva(reserva)) {
                    throw new IOException(
                            "Reserva duplicada o incompatible.");
                }
            }

        } catch (IOException e) {

            throw new IllegalStateException(
                    "Error al cargar datos: "
                    + e.getMessage(), e);
        }
    }

    // GUARDAR DATOS
    public void guardarDatos() throws IOException {

        archivo.guardarHabitaciones(
                hotel.listarHabitaciones());

        archivo.guardarClientes(
                hotel.listarClientes());

        archivo.guardarReservas(
                hotel.listarReservas());
    }

    // REGISTRAR HABITACION
    public boolean registrarHabitacion(
            int numero, String tipo, double precio)
            throws IOException {

        Habitacion habitacion =
                new Habitacion(numero, tipo, precio);

        if (hotel.agregarHabitacion(habitacion)) {
            guardarDatos();
            return true;
        }

        return false;
    }

    // BUSCAR HABITACION
    public Habitacion buscarHabitacion(int numero) {
        return hotel.buscarHabitacion(numero);
    }

    // LISTAR HABITACIONES
    public ArrayList<Habitacion> listarHabitaciones() {
        return hotel.listarHabitaciones();
    }

    // ACTUALIZAR HABITACION
    public boolean actualizarHabitacion(
            int numero, String tipo, double precio)
            throws IOException {

        Habitacion habitacion =
                hotel.buscarHabitacion(numero);

        if (habitacion == null) {
            return false;
        }

        habitacion.setTipo(tipo);
        habitacion.setPrecio(precio);

        guardarDatos();
        return true;
    }

    // ELIMINAR HABITACION
    public boolean eliminarHabitacion(int numero)
            throws IOException {

        if (hotel.eliminarHabitacion(numero)) {
            guardarDatos();
            return true;
        }

        return false;
    }

    // REGISTRAR CLIENTE
    public boolean registrarCliente(
            String cedula, String nombre,
            String telefono, String correo)
            throws IOException {

        Cliente cliente = new Cliente(
                cedula, nombre, telefono, correo);

        if (hotel.agregarCliente(cliente)) {
            guardarDatos();
            return true;
        }

        return false;
    }

    // BUSCAR CLIENTE
    public Cliente buscarCliente(String cedula) {
        return hotel.buscarCliente(cedula);
    }

    // LISTAR CLIENTES
    public ArrayList<Cliente> listarClientes() {
        return hotel.listarClientes();
    }

    // REGISTRAR RESERVA
    public boolean registrarReserva(
            int numeroReserva,
            String cedula,
            int numeroHabitacion,
            LocalDate fechaEntrada,
            LocalDate fechaSalida)
            throws IOException {

        Cliente cliente = hotel.buscarCliente(cedula);

        Habitacion habitacion =
                hotel.buscarHabitacion(numeroHabitacion);

        if (cliente == null || habitacion == null) {
            return false;
        }

        Reserva reserva = new Reserva(
                numeroReserva,
                cliente,
                habitacion,
                fechaEntrada,
                fechaSalida
        );

        if (hotel.agregarReserva(reserva)) {
            guardarDatos();
            return true;
        }

        return false;
    }

    // BUSCAR RESERVA
    public Reserva buscarReserva(int numeroReserva) {
        return hotel.buscarReserva(numeroReserva);
    }

    // LISTAR RESERVAS
    public ArrayList<Reserva> listarReservas() {
        return hotel.listarReservas();
    }

    // REALIZAR CHECK-IN
    public boolean realizarCheckIn(int numeroReserva)
            throws IOException {

        if (hotel.realizarCheckIn(numeroReserva)) {
            guardarDatos();
            return true;
        }

        return false;
    }

    // REALIZAR CHECK-OUT
    public boolean realizarCheckOut(int numeroReserva)
            throws IOException {

        if (hotel.realizarCheckOut(numeroReserva)) {
            guardarDatos();
            return true;
        }

        return false;
    }
    

    }


