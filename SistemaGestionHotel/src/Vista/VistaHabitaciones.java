
package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.HotelControlador;
import Modelo.Habitacion;

public class VistaHabitaciones extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtNumero;
    private JTextField txtPrecio;
    private JComboBox<String> cmbTipo;
    private JTable tblHabitaciones;

    private HotelControlador controlador;

    // EJECUTAR VENTANA
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                VistaHabitaciones frame = new VistaHabitaciones();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error al iniciar: " + e.getMessage());
            }
        });
    }

    // CONSTRUCTOR
    public VistaHabitaciones() {

        controlador = new HotelControlador();

        setTitle("Gestión de Habitaciones");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 767, 490);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setBounds(0, 0, 751, 451);
        contentPane.add(panel);
        panel.setLayout(null);

        // TITULO
        JLabel lblTitulo =
                new JLabel("GESTIÓN DE HABITACIONES");
        lblTitulo.setForeground(Color.LIGHT_GRAY);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setBounds(203, 11, 400, 30);
        panel.add(lblTitulo);

        JLabel lblDatos =
                new JLabel("DATOS DE LA HABITACIÓN");
        lblDatos.setForeground(Color.LIGHT_GRAY);
        lblDatos.setFont(new Font("Arial", Font.BOLD, 13));
        lblDatos.setBounds(30, 40, 245, 26);
        panel.add(lblDatos);

        // NUMERO
        JLabel lblNumero =
                new JLabel("Número de habitación:");
        lblNumero.setFont(new Font("Arial", Font.BOLD, 14));
        lblNumero.setForeground(Color.WHITE);
        lblNumero.setBounds(44, 77, 200, 18);
        panel.add(lblNumero);

        txtNumero = new JTextField();
        txtNumero.setBounds(50, 100, 225, 25);
        panel.add(txtNumero);
        txtNumero.setColumns(10);

        // TIPO
        JLabel lblTipo =
                new JLabel("Tipo de habitación:");
        lblTipo.setFont(new Font("Arial", Font.BOLD, 14));
        lblTipo.setForeground(Color.WHITE);
        lblTipo.setBounds(376, 73, 200, 26);
        panel.add(lblTipo);

        cmbTipo = new JComboBox<>();
        cmbTipo.addItem("Individual");
        cmbTipo.addItem("Doble");
        cmbTipo.addItem("Matrimonial");
        cmbTipo.addItem("Suite");
        cmbTipo.setBounds(376, 99, 206, 25);
        panel.add(cmbTipo);

        // PRECIO
        JLabel lblPrecio =
                new JLabel("Precio por noche:");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setForeground(Color.WHITE);
        lblPrecio.setBounds(44, 131, 200, 31);
        panel.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(54, 158, 221, 31);
        panel.add(txtPrecio);
        txtPrecio.setColumns(10);

        // BOTON REGISTRAR
        JButton btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.setBounds(33, 213, 115, 25);
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {

            try {
                int numero = Integer.parseInt(
                        txtNumero.getText().trim());

                String tipo =
                        cmbTipo.getSelectedItem().toString();

                double precio = Double.parseDouble(
                        txtPrecio.getText().trim());

                if (numero <= 0 || !Double.isFinite(precio)
                        || precio <= 0) {

                    JOptionPane.showMessageDialog(this,
                            "El número y el precio deben ser "
                            + "mayores que cero.");
                    return;
                }

                boolean registrado =
                        controlador.registrarHabitacion(
                                numero, tipo, precio);

                if (registrado) {

                    JOptionPane.showMessageDialog(this,
                            "Habitación registrada correctamente.");

                    cargarTablaHabitaciones();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Ya existe una habitación con ese número.");
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Ingrese un número y un precio válidos.");

            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error al guardar la habitación: "
                        + ex.getMessage());
            }
        });

        // BOTON BUSCAR
        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 14));
        btnBuscar.setBounds(172, 213, 115, 25);
        panel.add(btnBuscar);

        btnBuscar.addActionListener(e -> {

            try {
                int numero = Integer.parseInt(
                        txtNumero.getText().trim());

                Habitacion habitacion =
                        controlador.buscarHabitacion(numero);

                if (habitacion != null) {

                    cmbTipo.setSelectedItem(
                            habitacion.getTipo());

                    txtPrecio.setText(
                            String.valueOf(
                                    habitacion.getPrecio()));

                    String estado =
                            habitacion.isDisponible()
                            ? "Disponible" : "Ocupada";

                    JOptionPane.showMessageDialog(this,
                            "Habitación encontrada.\n"
                            + "Número: "
                            + habitacion.getNumero()
                            + "\nTipo: "
                            + habitacion.getTipo()
                            + "\nPrecio: $"
                            + habitacion.getPrecio()
                            + "\nEstado: " + estado);

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No existe una habitación "
                            + "con ese número.");

                    txtPrecio.setText("");
                    cmbTipo.setSelectedIndex(0);
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Ingrese un número de habitación válido.");
            }
        });

        // BOTON ACTUALIZAR
        JButton btnActualizar =
                new JButton("ACTUALIZAR");
        btnActualizar.setFont(
                new Font("Arial", Font.BOLD, 14));
        btnActualizar.setBounds(308, 211, 143, 26);
        panel.add(btnActualizar);

        btnActualizar.addActionListener(e -> {

            try {
                int numero = Integer.parseInt(
                        txtNumero.getText().trim());

                String tipo =
                        cmbTipo.getSelectedItem().toString();

                double precio = Double.parseDouble(
                        txtPrecio.getText().trim());

                if (numero <= 0 || !Double.isFinite(precio)
                        || precio <= 0) {

                    JOptionPane.showMessageDialog(this,
                            "Ingrese un número y un precio válidos.");
                    return;
                }

                Habitacion habitacion =
                        controlador.buscarHabitacion(numero);

                if (habitacion == null) {

                    JOptionPane.showMessageDialog(this,
                            "No existe una habitación "
                            + "con ese número.");
                    return;
                }

                int confirmar =
                        JOptionPane.showConfirmDialog(
                                this,
                                "¿Desea actualizar la habitación "
                                + numero + "?",
                                "Confirmar actualización",
                                JOptionPane.YES_NO_OPTION);

                if (confirmar != JOptionPane.YES_OPTION) {
                    return;
                }

                boolean actualizado =
                        controlador.actualizarHabitacion(
                                numero, tipo, precio);

                if (actualizado) {

                    JOptionPane.showMessageDialog(this,
                            "Habitación actualizada correctamente.");

                    cargarTablaHabitaciones();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No se pudo actualizar la habitación.");
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Ingrese valores numéricos válidos.");

            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error al guardar los cambios: "
                        + ex.getMessage());
            }
        });

        // BOTON ELIMINAR
        JButton btnEliminar = new JButton("ELIMINAR");
        btnEliminar.setFont(
                new Font("Arial", Font.BOLD, 14));
        btnEliminar.setBounds(476, 213, 106, 25);
        panel.add(btnEliminar);

        btnEliminar.addActionListener(e -> {

            try {
                int numero = Integer.parseInt(
                        txtNumero.getText().trim());

                if (numero <= 0) {

                    JOptionPane.showMessageDialog(this,
                            "Ingrese un número válido.");
                    return;
                }

                Habitacion habitacion =
                        controlador.buscarHabitacion(numero);

                if (habitacion == null) {

                    JOptionPane.showMessageDialog(this,
                            "No existe una habitación "
                            + "con ese número.");
                    return;
                }

                int confirmar =
                        JOptionPane.showConfirmDialog(
                                this,
                                "¿Está seguro de eliminar "
                                + "la habitación " + numero + "?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                if (confirmar != JOptionPane.YES_OPTION) {
                    return;
                }

                boolean eliminado =
                        controlador.eliminarHabitacion(numero);

                if (eliminado) {

                    JOptionPane.showMessageDialog(this,
                            "Habitación eliminada correctamente.");

                    cargarTablaHabitaciones();
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No se puede eliminar la habitación "
                            + "porque tiene reservas asociadas.");
                }

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(this,
                        "Ingrese un número de habitación válido.");

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error al guardar los cambios: "
                        + ex.getMessage());
            }
        });

        // BOTON LIMPIAR
        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setFont(
                new Font("Arial", Font.BOLD, 14));
        btnLimpiar.setBounds(600, 213, 100, 25);
        panel.add(btnLimpiar);

        btnLimpiar.addActionListener(e -> {
            limpiarCampos();
        });

        // TABLA DE HABITACIONES
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(50, 259, 650, 155);
        panel.add(scrollPane);

        tblHabitaciones = new JTable();
        tblHabitaciones.setRowHeight(24);
        tblHabitaciones.setFillsViewportHeight(true);

        // CORRECCION: COLOCAR LA TABLA EN EL VIEWPORT
        scrollPane.setViewportView(tblHabitaciones);

        // CARGAR HABITACIONES AL ABRIR LA VENTANA
        cargarTablaHabitaciones();

        setLocationRelativeTo(null);
    }

    // LIMPIAR CAMPOS
    private void limpiarCampos() {

        txtNumero.setText("");
        txtPrecio.setText("");
        cmbTipo.setSelectedIndex(0);
        txtNumero.requestFocusInWindow();
    }

    // MOSTRAR HABITACIONES EN LA TABLA
    private void cargarTablaHabitaciones() {

        DefaultTableModel modelo =
                new DefaultTableModel(
                        new Object[] {
                                "Número",
                                "Tipo",
                                "Precio por noche",
                                "Estado"
                        }, 0) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public boolean isCellEditable(
                            int fila, int columna) {
                        return false;
                    }
                };

        ArrayList<Habitacion> habitaciones =
                controlador.listarHabitaciones();

        for (Habitacion habitacion : habitaciones) {

            String estado = habitacion.isDisponible()
                    ? "Disponible" : "Ocupada";

            Object[] fila = {
                    habitacion.getNumero(),
                    habitacion.getTipo(),
                    String.format("%.2f",
                            habitacion.getPrecio()),
                    estado
            };

            modelo.addRow(fila);
        }

        tblHabitaciones.setModel(modelo);
    }
}
