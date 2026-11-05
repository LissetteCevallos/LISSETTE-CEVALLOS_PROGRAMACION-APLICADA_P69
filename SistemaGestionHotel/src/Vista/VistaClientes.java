
package Vista;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;

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

public class VistaClientes extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField txtCedula;
    private JTextField txtNombre;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JTable tblClientes;

    private HotelControlador controlador;

    // INICIAR LA VENTANA
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            try {

                VistaClientes frame = new VistaClientes();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            } catch (Exception e) {

                e.printStackTrace();

                JOptionPane.showMessageDialog(null,
                        "Error al iniciar Gestión de Clientes: "
                        + e.getMessage());
            }
        });
    }

    // CONSTRUCTOR
    public VistaClientes() {

        controlador = new HotelControlador();

        setTitle("Gestión de Clientes");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 659, 437);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // PANEL PRINCIPAL
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(0, 0, 641, 398);
        contentPane.add(panel);
        panel.setLayout(null);

        // TITULO
        JLabel lblTitulo =
                new JLabel("GESTIÓN DE CLIENTES");

        lblTitulo.setForeground(Color.DARK_GRAY);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setBounds(205, 11, 273, 22);
        panel.add(lblTitulo);

        // CEDULA
        JLabel lblCedula = new JLabel("Cédula:");
        lblCedula.setFont(new Font("Arial", Font.BOLD, 13));
        lblCedula.setBounds(54, 31, 100, 24);
        panel.add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(61, 63, 214, 29);
        txtCedula.setColumns(10);
        panel.add(txtCedula);

        // NOMBRE
        JLabel lblNombre =
                new JLabel("Nombre Completo:");

        lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
        lblNombre.setBounds(318, 32, 180, 22);
        panel.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(318, 63, 234, 29);
        txtNombre.setColumns(10);
        panel.add(txtNombre);

        // TELEFONO
        JLabel lblTelefono = new JLabel("Teléfono:");
        lblTelefono.setFont(new Font("Arial", Font.BOLD, 13));
        lblTelefono.setBounds(54, 103, 100, 22);
        panel.add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(61, 127, 190, 29);
        txtTelefono.setColumns(10);
        panel.add(txtTelefono);

        // CORREO
        JLabel lblCorreo =
                new JLabel("Correo electrónico:");

        lblCorreo.setFont(new Font("Arial", Font.BOLD, 13));
        lblCorreo.setBounds(318, 103, 180, 22);
        panel.add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(318, 127, 234, 29);
        txtCorreo.setColumns(10);
        panel.add(txtCorreo);

        // ==================================
        // BOTON REGISTRAR
        // ==================================

        JButton btnRegistrar =
                new JButton("REGISTRAR");

        btnRegistrar.setBounds(54, 185, 97, 23);
        panel.add(btnRegistrar);

        btnRegistrar.addActionListener(e -> {

            try {

                String cedula =
                        txtCedula.getText().trim();

                String nombre =
                        txtNombre.getText().trim();

                String telefono =
                        txtTelefono.getText().trim();

                String correo =
                        txtCorreo.getText().trim();

                // VALIDAR CAMPOS VACIOS
                if (cedula.isEmpty()
                        || nombre.isEmpty()
                        || telefono.isEmpty()
                        || correo.isEmpty()) {

                    JOptionPane.showMessageDialog(this,
                            "Debe completar todos los campos.");
                    return;
                }

                // VALIDAR CEDULA
                if (!cedula.matches("\\d{10}")) {

                    JOptionPane.showMessageDialog(this,
                            "La cédula debe contener 10 dígitos.");
                    return;
                }

                // VALIDAR TELEFONO
                if (!telefono.matches("\\d{7,10}")) {

                    JOptionPane.showMessageDialog(this,
                            "El teléfono debe contener "
                            + "entre 7 y 10 dígitos.");
                    return;
                }

                // VALIDAR CORREO
                if (!correo.matches(
                        "^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {

                    JOptionPane.showMessageDialog(this,
                            "Ingrese un correo electrónico válido.");
                    return;
                }

                // REGISTRAR CLIENTE
                boolean registrado =
                        controlador.registrarCliente(
                                cedula,
                                nombre,
                                telefono,
                                correo);

                if (registrado) {

                    JOptionPane.showMessageDialog(this,
                            "Cliente registrado correctamente.");

                    // ACTUALIZAR TABLA
                    cargarTablaClientes();

                    // LIMPIAR FORMULARIO
                    limpiarCampos();

                } else {

                    JOptionPane.showMessageDialog(this,
                            "Ya existe un cliente con esa cédula.");
                }

            } catch (IllegalArgumentException ex) {

                JOptionPane.showMessageDialog(this,
                        ex.getMessage());

            } catch (IOException ex) {

                JOptionPane.showMessageDialog(this,
                        "Error al guardar el cliente: "
                        + ex.getMessage());
            }
        });

        // ==================================
        // BOTON BUSCAR
        // ==================================

        JButton btnBuscar = new JButton("BUSCAR");
        btnBuscar.setBounds(177, 185, 110, 23);
        panel.add(btnBuscar);

        btnBuscar.addActionListener(e -> {

            try {

                String cedula =
                        txtCedula.getText().trim();

                if (cedula.isEmpty()) {

                    JOptionPane.showMessageDialog(this,
                            "Ingrese la cédula del cliente.");
                    return;
                }

                if (!cedula.matches("\\d{10}")) {

                    JOptionPane.showMessageDialog(this,
                            "La cédula debe contener 10 dígitos.");
                    return;
                }

                Cliente cliente =
                        controlador.buscarCliente(cedula);

                if (cliente != null) {

                    txtNombre.setText(
                            cliente.getNombre());

                    txtTelefono.setText(
                            cliente.getTelefono());

                    txtCorreo.setText(
                            cliente.getCorreo());

                    JOptionPane.showMessageDialog(this,
                            "Cliente encontrado correctamente.");

                } else {

                    JOptionPane.showMessageDialog(this,
                            "No existe un cliente con esa cédula.");

                    txtNombre.setText("");
                    txtTelefono.setText("");
                    txtCorreo.setText("");
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(this,
                        "Error al buscar el cliente: "
                        + ex.getMessage());
            }
        });

        // ==================================
        // BOTON LIMPIAR
        // ==================================

        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBounds(321, 185, 115, 23);
        panel.add(btnLimpiar);

        btnLimpiar.addActionListener(e -> {
            limpiarCampos();
        });

        // ==================================
        // BOTON VOLVER
        // ==================================

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(459, 185, 115, 23);
        panel.add(btnVolver);

        btnVolver.addActionListener(e -> {
            dispose();
        });

        // ==================================
        // TABLA DE CLIENTES
        // ==================================

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(35, 228, 570, 126);
        panel.add(scrollPane);

        tblClientes = new JTable();
        tblClientes.setRowHeight(24);
        tblClientes.setFillsViewportHeight(true);

        // COLOCAR LA TABLA CORRECTAMENTE
        scrollPane.setViewportView(tblClientes);

        // CARGAR CLIENTES DESPUES DE CREAR LA TABLA
        cargarTablaClientes();

        // CENTRAR VENTANA
        setLocationRelativeTo(null);
    }

    // ==================================
    // METODO PARA LIMPIAR CAMPOS
    // ==================================

    private void limpiarCampos() {

        txtCedula.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtCorreo.setText("");

        txtCedula.requestFocusInWindow();
    }

    // ==================================
    // METODO PARA CARGAR LA TABLA
    // ==================================

    private void cargarTablaClientes() {

        DefaultTableModel modelo =
                new DefaultTableModel(
                        new Object[] {
                                "Cédula",
                                "Nombre completo",
                                "Teléfono",
                                "Correo electrónico"
                        }, 0) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public boolean isCellEditable(
                            int fila, int columna) {
                        return false;
                    }
                };

        ArrayList<Cliente> clientes =
                controlador.listarClientes();

        for (Cliente cliente : clientes) {

            Object[] fila = {
                    cliente.getCedula(),
                    cliente.getNombre(),
                    cliente.getTelefono(),
                    cliente.getCorreo()
            };

            modelo.addRow(fila);
        }

        tblClientes.setModel(modelo);
    }
}
