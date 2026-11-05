
package Vista;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

public class VistaHotel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                VistaHotel frame = new VistaHotel();
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();

                JOptionPane.showMessageDialog(
                        null,
                        "Error al iniciar el sistema: "
                        + e.getMessage());
            }
        });
    }

    public VistaHotel() {

        setTitle("Sistema de Gestión de Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 604, 405);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(0, 0, 594, 355);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel(
                "SISTEMA DE GESTIÓN DE HOTEL");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 23));
        lblTitulo.setBounds(109, 11, 450, 30);
        panel.add(lblTitulo);

        // HABITACIONES
        JButton btnHabitaciones = new JButton("HABITACIONES");
        btnHabitaciones.setFont(new Font("Arial", Font.BOLD, 14));
        btnHabitaciones.setBounds(120, 64, 340, 43);
        panel.add(btnHabitaciones);

        btnHabitaciones.addActionListener(e -> {
            try {
                VistaHabitaciones ventana = new VistaHabitaciones();
                ventana.setLocationRelativeTo(VistaHotel.this);
                ventana.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        VistaHotel.this,
                        "Error al abrir Habitaciones: "
                        + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // CLIENTES
        JButton btnClientes = new JButton("CLIENTES");
        btnClientes.setFont(new Font("Arial", Font.BOLD, 14));
        btnClientes.setBounds(120, 126, 340, 43);
        panel.add(btnClientes);

        btnClientes.addActionListener(e -> {
            try {
                VistaClientes ventana = new VistaClientes();
                ventana.setLocationRelativeTo(VistaHotel.this);
                ventana.setVisible(true);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        VistaHotel.this,
                        "Error al abrir Clientes: "
                        + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // RESERVAS
        JButton btnReservas = new JButton("RESERVAS");
        btnReservas.setFont(new Font("Arial", Font.BOLD, 14));
        btnReservas.setBounds(120, 180, 340, 47);
        panel.add(btnReservas);

        btnReservas.addActionListener(e -> {
            try {
                VistaReservas ventana = new VistaReservas();
                ventana.setLocationRelativeTo(VistaHotel.this);

                ventana.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent evento) {
                        if (VistaHotel.this.isDisplayable()) {
                            VistaHotel.this.setVisible(true);
                        }
                    }
                });

                VistaHotel.this.setVisible(false);
                ventana.setVisible(true);

            } catch (Exception ex) {
                VistaHotel.this.setVisible(true);

                JOptionPane.showMessageDialog(
                        VistaHotel.this,
                        "Error al abrir Reservas: "
                        + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // HISTORIAL
        JButton btnHistorial = new JButton("HISTORIAL");
        btnHistorial.setFont(new Font("Arial", Font.BOLD, 14));
        btnHistorial.setBounds(120, 238, 340, 43);
        panel.add(btnHistorial);

        btnHistorial.addActionListener(e -> {
            try {
                Class<?> clase = Class.forName("Vista.VistaHistorial");

                Object objeto = clase.getDeclaredConstructor()
                        .newInstance();

                if (!(objeto instanceof JFrame)) {
                    JOptionPane.showMessageDialog(
                            VistaHotel.this,
                            "La clase VistaHistorial debe ser una ventana JFrame.");
                    return;
                }

                JFrame ventana = (JFrame) objeto;
                ventana.setLocationRelativeTo(VistaHotel.this);
                ventana.setVisible(true);

            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(
                        VistaHotel.this,
                        "Todavía no existe la ventana VistaHistorial.\n"
                        + "Primero debemos crear el módulo de historial.",
                        "Historial pendiente",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        VistaHotel.this,
                        "Error al abrir Historial: "
                        + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // SALIR
        JButton btnSalir = new JButton("SALIR");
        btnSalir.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalir.setBounds(120, 292, 340, 43);
        panel.add(btnSalir);

        btnSalir.addActionListener(e -> {

            int confirmar = JOptionPane.showConfirmDialog(
                    VistaHotel.this,
                    "¿Está seguro de salir del sistema?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION);

            if (confirmar == JOptionPane.YES_OPTION) {
                dispose();
            }
        });
    }
}
