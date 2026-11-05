
package Vista;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;
import java.time.temporal.ChronoUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.HotelControlador;
import Modelo.Reserva;

public class VistaHistorial extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tblHistorial;
    private DefaultTableModel modeloTabla;
    private HotelControlador controlador;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                VistaHistorial frame = new VistaHistorial();
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VistaHistorial() {

        setTitle("Gestion de Historial");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 625, 393);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(0, 0, 609, 343);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("HISTORIAL DE RESERVAS");
        lblTitulo.setBounds(191, 11, 230, 21);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 17));
        panel.add(lblTitulo);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(25, 50, 560, 186);
        panel.add(scrollPane);

        tblHistorial = new JTable();
        scrollPane.setViewportView(tblHistorial);

        modeloTabla = new DefaultTableModel(
                new Object[][] {},
                new String[] {
                        "Cédula",
                        "Cliente",
                        "Habitación",
                        "Entrada",
                        "Salida",
                        "Noches",
                        "Total ($)"
                }) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tblHistorial.setModel(modeloTabla);
        tblHistorial.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);
        tblHistorial.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF);
        tblHistorial.getTableHeader().setReorderingAllowed(false);
        tblHistorial.setRowHeight(24);

        tblHistorial.getColumnModel().getColumn(0)
                .setPreferredWidth(95);
        tblHistorial.getColumnModel().getColumn(1)
                .setPreferredWidth(140);
        tblHistorial.getColumnModel().getColumn(2)
                .setPreferredWidth(90);
        tblHistorial.getColumnModel().getColumn(3)
                .setPreferredWidth(100);
        tblHistorial.getColumnModel().getColumn(4)
                .setPreferredWidth(100);
        tblHistorial.getColumnModel().getColumn(5)
                .setPreferredWidth(65);
        tblHistorial.getColumnModel().getColumn(6)
                .setPreferredWidth(90);

        JButton btnActualizar = new JButton("ACTUALIZAR");
        btnActualizar.setBounds(70, 255, 145, 35);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(btnActualizar);

        JButton btnLimpiar = new JButton("LIMPIAR");
        btnLimpiar.setBounds(235, 255, 145, 35);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(btnLimpiar);

        JButton btnVolver = new JButton("VOLVER");
        btnVolver.setBounds(400, 255, 145, 35);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 12));
        panel.add(btnVolver);

        btnActualizar.addActionListener(e -> {
            cargarHistorial();
        });

        btnLimpiar.addActionListener(e -> {
            modeloTabla.setRowCount(0);
            tblHistorial.clearSelection();
        });

        btnVolver.addActionListener(e -> {
            dispose();
        });

        try {
            controlador = new HotelControlador();
            cargarHistorial();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al cargar el historial: "
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarHistorial() {

        modeloTabla.setRowCount(0);

        if (controlador == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo inicializar el controlador.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            for (Reserva reserva : controlador.listarReservas()) {

                if (reserva == null
                        || reserva.getCliente() == null
                        || reserva.getHabitacion() == null
                        || reserva.getFechaEntrada() == null
                        || reserva.getFechaSalida() == null) {
                    continue;
                }

                long noches = ChronoUnit.DAYS.between(
                        reserva.getFechaEntrada(),
                        reserva.getFechaSalida());

                double total = noches
                        * reserva.getHabitacion().getPrecio();

                modeloTabla.addRow(new Object[] {
                        reserva.getCliente().getCedula(),
                        reserva.getCliente().getNombre(),
                        reserva.getHabitacion().getNumero(),
                        reserva.getFechaEntrada(),
                        reserva.getFechaSalida(),
                        noches,
                        String.format("%.2f", total)
                });
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al consultar las reservas: "
                            + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
