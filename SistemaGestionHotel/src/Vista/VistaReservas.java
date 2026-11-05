
package Vista;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.HotelControlador;
import Modelo.Cliente;
import Modelo.Habitacion;
import Modelo.Reserva;

public class VistaReservas extends JFrame {

    private static final long serialVersionUID = 1L;

    private HotelControlador controlador;
    private JPanel contentPane;

    private JTextField txtNumeroReserva;
    private JTextField txtCedulaCliente;
    private JTextField txtNumeroHabitacion;
    private JTextField txtFechaEntrada;
    private JTextField txtFechaSalida;
    private JTextField txtCostoTotal;

    private JTable tblReservas;
    private DefaultTableModel modeloReservas;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VistaReservas frame = new VistaReservas();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VistaReservas() {

        controlador = new HotelControlador();

        setTitle("Gestión de Reservas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 706, 474);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(0, 0, 690, 435);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("GESTIÓN DE RESERVAS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 13));
        lblTitulo.setBounds(230, 11, 287, 16);
        panel.add(lblTitulo);

        JLabel lblNumeroReserva = new JLabel("Número de reserva:");
        lblNumeroReserva.setFont(new Font("Arial", Font.BOLD, 13));
        lblNumeroReserva.setBounds(41, 39, 151, 27);
        panel.add(lblNumeroReserva);

        txtNumeroReserva = new JTextField();
        txtNumeroReserva.setBounds(41, 62, 169, 31);
        txtNumeroReserva.setColumns(10);
        panel.add(txtNumeroReserva);

        JLabel lblCedulaCliente = new JLabel("Cédula del cliente:");
        lblCedulaCliente.setFont(new Font("Arial", Font.BOLD, 13));
        lblCedulaCliente.setBounds(240, 38, 151, 28);
        panel.add(lblCedulaCliente);

        txtCedulaCliente = new JTextField();
        txtCedulaCliente.setBounds(242, 64, 165, 26);
        txtCedulaCliente.setColumns(10);
        panel.add(txtCedulaCliente);

        JLabel lblNumeroHabitacion =
                new JLabel("Número de Habitación:");
        lblNumeroHabitacion.setFont(new Font("Arial", Font.BOLD, 13));
        lblNumeroHabitacion.setBounds(431, 42, 180, 21);
        panel.add(lblNumeroHabitacion);

        txtNumeroHabitacion = new JTextField();
        txtNumeroHabitacion.setBounds(431, 67, 177, 26);
        txtNumeroHabitacion.setColumns(10);
        panel.add(txtNumeroHabitacion);

        JLabel lblFechaEntrada = new JLabel("Fecha de entrada:");
        lblFechaEntrada.setFont(new Font("Arial", Font.BOLD, 13));
        lblFechaEntrada.setBounds(38, 104, 136, 21);
        panel.add(lblFechaEntrada);

        txtFechaEntrada = new JTextField();
        txtFechaEntrada.setBounds(41, 121, 169, 31);
        txtFechaEntrada.setColumns(10);
        panel.add(txtFechaEntrada);

        JLabel lblFechaSalida = new JLabel("Fecha de salida:");
        lblFechaSalida.setFont(new Font("Arial", Font.BOLD, 13));
        lblFechaSalida.setBounds(241, 100, 127, 28);
        panel.add(lblFechaSalida);

        txtFechaSalida = new JTextField();
        txtFechaSalida.setBounds(251, 126, 156, 26);
        txtFechaSalida.setColumns(10);
        panel.add(txtFechaSalida);

        JLabel lblCostoTotal = new JLabel("Costo total:");
        lblCostoTotal.setFont(new Font("Arial", Font.BOLD, 13));
        lblCostoTotal.setBounds(431, 97, 106, 28);
        panel.add(lblCostoTotal);

        txtCostoTotal = new JTextField();
        txtCostoTotal.setBounds(431, 126, 177, 26);
        txtCostoTotal.setColumns(10);
        txtCostoTotal.setEditable(false);
        panel.add(txtCostoTotal);

        // REGISTRAR RESERVA
        JButton btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setBounds(78, 177, 89, 23);
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {
            try {
                String numeroTexto = txtNumeroReserva.getText().trim();
                String cedula = txtCedulaCliente.getText().trim();
                String habitacionTexto =
                        txtNumeroHabitacion.getText().trim();
                String entradaTexto = txtFechaEntrada.getText().trim();
                String salidaTexto = txtFechaSalida.getText().trim();

                if (numeroTexto.isEmpty()
                        || cedula.isEmpty()
                        || habitacionTexto.isEmpty()
                        || entradaTexto.isEmpty()
                        || salidaTexto.isEmpty()) {

                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "Debe completar todos los campos.");
                    return;
                }

                int numeroReserva = Integer.parseInt(numeroTexto);
                int numeroHabitacion =
                        Integer.parseInt(habitacionTexto);

                if (numeroReserva <= 0 || numeroHabitacion <= 0) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "Los números deben ser mayores que cero.");
                    return;
                }

                if (!cedula.matches("\\d{10}")) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "La cédula debe contener 10 dígitos.");
                    return;
                }

                LocalDate fechaEntrada = LocalDate.parse(entradaTexto);
                LocalDate fechaSalida = LocalDate.parse(salidaTexto);

                if (!fechaSalida.isAfter(fechaEntrada)) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "La fecha de salida debe ser posterior "
                            + "a la fecha de entrada.");
                    return;
                }

                Cliente cliente = controlador.buscarCliente(cedula);

                if (cliente == null) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "El cliente no está registrado.");
                    return;
                }

                Habitacion habitacion =
                        controlador.buscarHabitacion(numeroHabitacion);

                if (habitacion == null) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "La habitación no existe.");
                    return;
                }

                if (!habitacion.isDisponible()) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "La habitación no está disponible.");
                    return;
                }

                boolean registrado = controlador.registrarReserva(
                        numeroReserva,
                        cedula,
                        numeroHabitacion,
                        fechaEntrada,
                        fechaSalida);

                if (registrado) {
                    long noches = ChronoUnit.DAYS.between(
                            fechaEntrada, fechaSalida);

                    double costoTotal = noches * habitacion.getPrecio();

                    txtCostoTotal.setText(
                            String.format("%.2f", costoTotal));

                    actualizarTablaReservas();

                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "Reserva registrada correctamente.\n"
                            + "Número de reserva: " + numeroReserva
                            + "\nCliente: " + cliente.getNombre()
                            + "\nHabitación: " + numeroHabitacion
                            + "\nNoches: " + noches
                            + "\nCosto total: $"
                            + String.format("%.2f", costoTotal));
                } else {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "No se pudo registrar la reserva.\n"
                            + "Verifique el número de reserva "
                            + "y la disponibilidad de la habitación.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Ingrese números válidos.");

            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Ingrese las fechas en formato AAAA-MM-DD.\n"
                        + "Ejemplo: 2026-11-10.");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Error al guardar la reserva: " + ex.getMessage());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Error al registrar la reserva: " + ex.getMessage());
            }
        });

        // BUSCAR RESERVA
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(238, 177, 109, 23);
        panel.add(btnBuscar);

        btnBuscar.addActionListener(e -> {
            try {
                String numeroTexto = txtNumeroReserva.getText().trim();

                if (numeroTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "Ingrese el número de reserva.");
                    return;
                }

                int numeroReserva = Integer.parseInt(numeroTexto);

                if (numeroReserva <= 0) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "El número de reserva debe ser mayor que cero.");
                    return;
                }

                Reserva reserva =
                        controlador.buscarReserva(numeroReserva);

                if (reserva != null) {
                    txtCedulaCliente.setText(
                            reserva.getCliente().getCedula());

                    txtNumeroHabitacion.setText(
                            String.valueOf(
                                    reserva.getHabitacion().getNumero()));

                    txtFechaEntrada.setText(
                            reserva.getFechaEntrada().toString());

                    txtFechaSalida.setText(
                            reserva.getFechaSalida().toString());

                    long noches = ChronoUnit.DAYS.between(
                            reserva.getFechaEntrada(),
                            reserva.getFechaSalida());

                    double costoTotal =
                            noches * reserva.getHabitacion().getPrecio();

                    txtCostoTotal.setText(
                            String.format("%.2f", costoTotal));

                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "Reserva encontrada correctamente.");

                } else {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "No existe una reserva con ese número.");

                    txtCedulaCliente.setText("");
                    txtNumeroHabitacion.setText("");
                    txtFechaEntrada.setText("");
                    txtFechaSalida.setText("");
                    txtCostoTotal.setText("");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Ingrese un número de reserva válido.");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Error al buscar la reserva: " + ex.getMessage());
            }
        });

        // CHECK-IN
        JButton btnChekin = new JButton("CHECK-IN");
        btnChekin.setBounds(431, 177, 109, 23);
        panel.add(btnChekin);

        btnChekin.addActionListener(e -> {
            try {
                String numeroTexto = txtNumeroReserva.getText().trim();

                if (numeroTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "Ingrese el número de reserva.");
                    return;
                }

                int numeroReserva = Integer.parseInt(numeroTexto);

                if (numeroReserva <= 0) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "El número de reserva debe ser mayor que cero.");
                    return;
                }

                Reserva reserva =
                        controlador.buscarReserva(numeroReserva);

                if (reserva == null) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "No existe una reserva con ese número.");
                    return;
                }

                int confirmar = JOptionPane.showConfirmDialog(
                        VistaReservas.this,
                        "¿Desea realizar el CHECK-IN de la reserva "
                        + numeroReserva + "?",
                        "Confirmar Check-in",
                        JOptionPane.YES_NO_OPTION);

                if (confirmar != JOptionPane.YES_OPTION) {
                    return;
                }

                boolean realizado =
                        controlador.realizarCheckIn(numeroReserva);

                if (realizado) {
                    actualizarTablaReservas();

                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "CHECK-IN realizado correctamente.\n"
                            + "Reserva: " + numeroReserva
                            + "\nCliente: "
                            + reserva.getCliente().getNombre()
                            + "\nHabitación: "
                            + reserva.getHabitacion().getNumero());
                } else {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "No se pudo realizar el CHECK-IN.\n"
                            + "Verifique el estado actual de la reserva.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Ingrese un número de reserva válido.");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Error al guardar el CHECK-IN: "
                        + ex.getMessage());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Error al realizar el CHECK-IN: "
                        + ex.getMessage());
            }
        });

        // CHECK-OUT
        JButton btnCheckOut = new JButton("CHECK-OUT");
        btnCheckOut.setBounds(78, 213, 96, 27);
        panel.add(btnCheckOut);

        btnCheckOut.addActionListener(e -> {
            try {
                String numeroTexto = txtNumeroReserva.getText().trim();

                if (numeroTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "Ingrese el número de reserva.");
                    return;
                }

                int numeroReserva = Integer.parseInt(numeroTexto);

                if (numeroReserva <= 0) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "El número de reserva debe ser mayor que cero.");
                    return;
                }

                Reserva reserva =
                        controlador.buscarReserva(numeroReserva);

                if (reserva == null) {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "No existe una reserva con ese número.");
                    return;
                }

                int confirmar = JOptionPane.showConfirmDialog(
                        VistaReservas.this,
                        "¿Desea realizar el CHECK-OUT de la reserva "
                        + numeroReserva + "?",
                        "Confirmar Check-out",
                        JOptionPane.YES_NO_OPTION);

                if (confirmar != JOptionPane.YES_OPTION) {
                    return;
                }

                boolean realizado =
                        controlador.realizarCheckOut(numeroReserva);

                if (realizado) {
                    actualizarTablaReservas();

                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "CHECK-OUT realizado correctamente.\n"
                            + "Reserva: " + numeroReserva
                            + "\nCliente: "
                            + reserva.getCliente().getNombre()
                            + "\nHabitación: "
                            + reserva.getHabitacion().getNumero());
                } else {
                    JOptionPane.showMessageDialog(
                            VistaReservas.this,
                            "No se pudo realizar el CHECK-OUT.\n"
                            + "Verifique que la reserva tenga "
                            + "un CHECK-IN registrado.");
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Ingrese un número de reserva válido.");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Error al guardar el CHECK-OUT: "
                        + ex.getMessage());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        VistaReservas.this,
                        "Error al realizar el CHECK-OUT: "
                        + ex.getMessage());
            }
        });

        // LIMPIAR
        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBounds(240, 211, 107, 31);
        panel.add(btnLimpiar);

        btnLimpiar.addActionListener(e -> {
            txtNumeroReserva.setText("");
            txtCedulaCliente.setText("");
            txtNumeroHabitacion.setText("");
            txtFechaEntrada.setText("");
            txtFechaSalida.setText("");
            txtCostoTotal.setText("");
            txtNumeroReserva.requestFocusInWindow();
        });

        // VOLVER
        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(431, 211, 109, 31);
        panel.add(btnVolver);

        btnVolver.addActionListener(e -> {
            VistaHotel vistaPrincipal = new VistaHotel();
            vistaPrincipal.setVisible(true);
            dispose();
        });

        // TABLA DE RESERVAS
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(59, 265, 549, 127);
        panel.add(scrollPane);

        modeloReservas = new DefaultTableModel(
                new Object[] {
                        "Reserva", "Cédula", "Cliente", "Habitación",
                        "Entrada", "Salida", "Noches", "Total ($)"
                }, 0) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblReservas = new JTable(modeloReservas);
        tblReservas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tblReservas.setRowHeight(23);

        tblReservas.getColumnModel().getColumn(0)
                .setPreferredWidth(65);
        tblReservas.getColumnModel().getColumn(1)
                .setPreferredWidth(100);
        tblReservas.getColumnModel().getColumn(2)
                .setPreferredWidth(140);
        tblReservas.getColumnModel().getColumn(3)
                .setPreferredWidth(85);
        tblReservas.getColumnModel().getColumn(4)
                .setPreferredWidth(100);
        tblReservas.getColumnModel().getColumn(5)
                .setPreferredWidth(100);
        tblReservas.getColumnModel().getColumn(6)
                .setPreferredWidth(60);
        tblReservas.getColumnModel().getColumn(7)
                .setPreferredWidth(85);

        scrollPane.setViewportView(tblReservas);

        actualizarTablaReservas();
    }

    private void actualizarTablaReservas() {

        modeloReservas.setRowCount(0);

        for (Reserva reserva : controlador.listarReservas()) {

            long noches = ChronoUnit.DAYS.between(
                    reserva.getFechaEntrada(),
                    reserva.getFechaSalida());

            double total =
                    noches * reserva.getHabitacion().getPrecio();

            modeloReservas.addRow(new Object[] {
                    reserva.getNumeroReserva(),
                    reserva.getCliente().getCedula(),
                    reserva.getCliente().getNombre(),
                    reserva.getHabitacion().getNumero(),
                    reserva.getFechaEntrada(),
                    reserva.getFechaSalida(),
                    noches,
                    String.format("%.2f", total)
            });
        }
    }
}
