
package Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;

public class ArchivoHotel {

    // ARCHIVOS DE ALMACENAMIENTO
    private final String rutaHabitaciones = "habitaciones.txt";
    private final String rutaClientes = "clientes.txt";
    private final String rutaReservas = "reservas.txt";

    // GUARDAR HABITACIONES
    public void guardarHabitaciones(
            ArrayList<Habitacion> habitaciones) throws IOException {

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(rutaHabitaciones),
                        StandardCharsets.UTF_8))) {

            for (Habitacion habitacion : habitaciones) {

                escritor.write(
                        habitacion.getNumero() + ";" +
                        habitacion.getTipo() + ";" +
                        habitacion.getPrecio() + ";" +
                        habitacion.isDisponible()
                );

                escritor.newLine();
            }
        }
    }

    // CARGAR HABITACIONES
    public ArrayList<Habitacion> cargarHabitaciones()
            throws IOException {

        ArrayList<Habitacion> habitaciones = new ArrayList<>();

        File archivo = new File(rutaHabitaciones);

        if (!archivo.exists()) {
            return habitaciones;
        }

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(archivo),
                        StandardCharsets.UTF_8))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                if (datos.length != 4) {
                    throw new IOException(
                            "Formato incorrecto en habitaciones.txt");
                }

                try {

                    int numero = Integer.parseInt(datos[0]);
                    String tipo = datos[1];
                    double precio = Double.parseDouble(datos[2]);

                    if (!datos[3].equals("true")
                            && !datos[3].equals("false")) {
                        throw new IOException(
                                "Estado de habitación inválido.");
                    }

                    boolean disponible =
                            Boolean.parseBoolean(datos[3]);

                    Habitacion habitacion =
                            new Habitacion(numero, tipo, precio);

                    habitacion.setDisponible(disponible);

                    habitaciones.add(habitacion);

                } catch (IllegalArgumentException e) {

                    throw new IOException(
                            "Datos inválidos en habitaciones.txt", e);
                }
            }
        }

        return habitaciones;
    }

    // GUARDAR CLIENTES
    public void guardarClientes(
            ArrayList<Cliente> clientes) throws IOException {

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(rutaClientes),
                        StandardCharsets.UTF_8))) {

            for (Cliente cliente : clientes) {

                escritor.write(
                        cliente.getCedula() + ";" +
                        cliente.getNombre() + ";" +
                        cliente.getTelefono() + ";" +
                        cliente.getCorreo()
                );

                escritor.newLine();
            }
        }
    }

    // CARGAR CLIENTES
    public ArrayList<Cliente> cargarClientes()
            throws IOException {

        ArrayList<Cliente> clientes = new ArrayList<>();

        File archivo = new File(rutaClientes);

        if (!archivo.exists()) {
            return clientes;
        }

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(archivo),
                        StandardCharsets.UTF_8))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                if (datos.length != 4) {
                    throw new IOException(
                            "Formato incorrecto en clientes.txt");
                }

                try {

                    Cliente cliente = new Cliente(
                            datos[0],
                            datos[1],
                            datos[2],
                            datos[3]
                    );

                    clientes.add(cliente);

                } catch (IllegalArgumentException e) {

                    throw new IOException(
                            "Datos inválidos en clientes.txt", e);
                }
            }
        }

        return clientes;
    }

    // GUARDAR RESERVAS
    public void guardarReservas(
            ArrayList<Reserva> reservas) throws IOException {

        try (BufferedWriter escritor = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(rutaReservas),
                        StandardCharsets.UTF_8))) {

            for (Reserva reserva : reservas) {

                escritor.write(
                        reserva.getNumeroReserva() + ";" +
                        reserva.getCliente().getCedula() + ";" +
                        reserva.getHabitacion().getNumero() + ";" +
                        reserva.getFechaEntrada() + ";" +
                        reserva.getFechaSalida() + ";" +
                        reserva.getEstado()
                );

                escritor.newLine();
            }
        }
    }

    // CARGAR RESERVAS
    public ArrayList<Reserva> cargarReservas(
            Hotel hotel) throws IOException {

        ArrayList<Reserva> reservas = new ArrayList<>();

        File archivo = new File(rutaReservas);

        if (!archivo.exists()) {
            return reservas;
        }

        try (BufferedReader lector = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(archivo),
                        StandardCharsets.UTF_8))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(";", -1);

                if (datos.length != 6) {
                    throw new IOException(
                            "Formato incorrecto en reservas.txt");
                }

                try {

                    int numeroReserva =
                            Integer.parseInt(datos[0]);

                    String cedula = datos[1];

                    int numeroHabitacion =
                            Integer.parseInt(datos[2]);

                    Cliente cliente =
                            hotel.buscarCliente(cedula);

                    Habitacion habitacion =
                            hotel.buscarHabitacion(numeroHabitacion);

                    if (cliente == null) {
                        throw new IOException(
                                "Cliente no encontrado en la reserva "
                                + numeroReserva);
                    }

                    if (habitacion == null) {
                        throw new IOException(
                                "Habitación no encontrada en la reserva "
                                + numeroReserva);
                    }

                    LocalDate fechaEntrada =
                            LocalDate.parse(datos[3]);

                    LocalDate fechaSalida =
                            LocalDate.parse(datos[4]);

                    Reserva reserva = new Reserva(
                            numeroReserva,
                            cliente,
                            habitacion,
                            fechaEntrada,
                            fechaSalida
                    );

                    // RECUPERAR ESTADO DE LA RESERVA
                    reserva.restaurarEstado(datos[5]);

                    reservas.add(reserva);

                } catch (IllegalArgumentException e) {

                    throw new IOException(
                            "Datos inválidos en reservas.txt: "
                            + linea, e);
                }
            }
        }

        return reservas;
    }
}
