
package Vista;

import java.awt.EventQueue;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class VistaTienda extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // CAMPOS DE TEXTO
    private JTextField txtCodigo;
    private JTextField txtPrecio;
    private JTextField txtNombre;
    private JTextField txtStock;

    // BOTONES
    private JButton btnProductos;
    private JButton btnVentas;
    private JButton btnHistorial;

    // INICIAR VENTANA
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaTienda frame = new VistaTienda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // CONSTRUCTOR
    public VistaTienda() {

        setTitle("Sistema de Ventas de Tienda");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 673, 385);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));

        // PANEL PRINCIPAL
        JPanel panel = new JPanel();
        panel.setBackground(new Color(65, 105, 225));
        contentPane.add(panel, "panelPrincipal");
        panel.setLayout(null);

        // TITULO PRINCIPAL
        JLabel lblTituloPrincipal =
                new JLabel("SISTEMA DE VENTAS DE TIENDA ");
        lblTituloPrincipal.setForeground(
                new Color(211, 211, 211));
        lblTituloPrincipal.setFont(
                new Font("Arial", Font.BOLD, 20));
        lblTituloPrincipal.setBounds(165, 11, 359, 33);
        panel.add(lblTituloPrincipal);

        // ETIQUETA CODIGO
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 14));
        lblCodigo.setForeground(new Color(211, 211, 211));
        lblCodigo.setBounds(41, 45, 83, 31);
        panel.add(lblCodigo);

        // ETIQUETA NOMBRE
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(new Color(240, 248, 255));
        lblNombre.setFont(new Font("Arial", Font.BOLD, 14));
        lblNombre.setBounds(317, 49, 83, 22);
        panel.add(lblNombre);

        // ETIQUETA PRECIO
        JLabel lblPrecio = new JLabel("Precio :");
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 14));
        lblPrecio.setForeground(new Color(240, 248, 255));
        lblPrecio.setBounds(41, 114, 65, 22);
        panel.add(lblPrecio);

        // ETIQUETA STOCK
        JLabel lblStock = new JLabel("Stock:");
        lblStock.setForeground(new Color(240, 248, 255));
        lblStock.setFont(new Font("Arial", Font.BOLD, 14));
        lblStock.setBounds(317, 118, 46, 14);
        panel.add(lblStock);

        // CAMPO CODIGO
        txtCodigo = new JTextField();
        txtCodigo.setBounds(41, 72, 208, 33);
        panel.add(txtCodigo);
        txtCodigo.setColumns(10);

        // CAMPO PRECIO
        txtPrecio = new JTextField();
        txtPrecio.setBounds(41, 145, 208, 33);
        panel.add(txtPrecio);
        txtPrecio.setColumns(10);

        // CAMPO NOMBRE
        txtNombre = new JTextField();
        txtNombre.setBounds(327, 72, 250, 31);
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        // CAMPO STOCK
        txtStock = new JTextField();
        txtStock.setBounds(327, 145, 250, 33);
        panel.add(txtStock);
        txtStock.setColumns(10);

        // CAMPOS DE CONSULTA: SOLO LECTURA
        txtNombre.setEditable(false);
        txtPrecio.setEditable(false);
        txtStock.setEditable(false);

        // BOTON GESTIONAR PRODUCTOS
        btnProductos = new JButton("Gestionar Productos");
        btnProductos.setBounds(80, 228, 152, 23);
        panel.add(btnProductos);

        // BOTON REALIZAR VENTAS
        btnVentas = new JButton("Realizar Ventas");
        btnVentas.setBounds(271, 228, 134, 23);
        panel.add(btnVentas);

        // BOTON HISTORIAL DE VENTAS
        btnHistorial = new JButton("Historial de Ventas");
        btnHistorial.setBounds(438, 228, 121, 23);
        panel.add(btnHistorial);
    }

    // GETTERS DE LOS CAMPOS

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtPrecio() {
        return txtPrecio;
    }

    public JTextField getTxtStock() {
        return txtStock;
    }

    // GETTERS DE LOS BOTONES

    public JButton getBtnProductos() {
        return btnProductos;
    }

    public JButton getBtnVentas() {
        return btnVentas;
    }

    public JButton getBtnHistorial() {
        return btnHistorial;
    }
}
