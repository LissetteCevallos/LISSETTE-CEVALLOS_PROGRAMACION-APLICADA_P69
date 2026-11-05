
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VistaVentas extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // CAMPOS DE TEXTO
    private JTextField txtCodigoProducto;
    private JTextField txtCantidad;

    // BOTONES
    private JButton btnBuscarProducto;
    private JButton btnAgregarVenta;
    private JButton btnConfirmarVenta;

    // TABLA Y TOTAL
    private JTable tablaVentas;
    private JLabel lblTotal;

    // INICIAR VENTANA
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaVentas frame = new VistaVentas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // CONSTRUCTOR
    public VistaVentas() {

        setTitle("Registro de Ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 701, 454);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new CardLayout(0, 0));

        // PANEL PRINCIPAL
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180));
        contentPane.add(panel, "panelVentas");
        panel.setLayout(null);

        // TITULO
        JLabel lblNewLabel = new JLabel("REGISTRO DE VENTAS");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setForeground(new Color(240, 255, 240));
        lblNewLabel.setBounds(201, 28, 291, 24);
        panel.add(lblNewLabel);

        // ETIQUETA CODIGO
        JLabel lblCodigoProducto =
                new JLabel("Código del producto:");
        lblCodigoProducto.setForeground(
                new Color(240, 255, 255));
        lblCodigoProducto.setFont(
                new Font("Arial", Font.BOLD, 14));
        lblCodigoProducto.setBounds(36, 61, 169, 33);
        panel.add(lblCodigoProducto);

        // ETIQUETA CANTIDAD
        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setForeground(new Color(240, 248, 255));
        lblCantidad.setFont(new Font("Arial", Font.BOLD, 14));
        lblCantidad.setBounds(362, 63, 146, 24);
        panel.add(lblCantidad);

        // CAMPO CODIGO DEL PRODUCTO
        txtCodigoProducto = new JTextField();
        txtCodigoProducto.setBounds(46, 92, 262, 33);
        panel.add(txtCodigoProducto);
        txtCodigoProducto.setColumns(10);

        // CAMPO CANTIDAD
        txtCantidad = new JTextField();
        txtCantidad.setBounds(362, 92, 236, 27);
        panel.add(txtCantidad);
        txtCantidad.setColumns(10);

        // BOTON BUSCAR PRODUCTO
        btnBuscarProducto = new JButton("Buscar producto");
        btnBuscarProducto.setFont(
                new Font("Arial", Font.BOLD, 14));
        btnBuscarProducto.setForeground(Color.BLACK);
        btnBuscarProducto.setBounds(496, 172, 157, 33);
        panel.add(btnBuscarProducto);

        // BOTON AGREGAR A LA VENTA
        btnAgregarVenta = new JButton("Agregar Venta");
        btnAgregarVenta.setFont(
                new Font("Arial", Font.BOLD, 14));
        btnAgregarVenta.setBounds(496, 216, 156, 33);
        panel.add(btnAgregarVenta);

        // BOTON CONFIRMAR VENTA
        btnConfirmarVenta = new JButton("Confirmar Venta");
        btnConfirmarVenta.setFont(
                new Font("Arial", Font.BOLD, 14));
        btnConfirmarVenta.setBounds(475, 327, 178, 33);
        panel.add(btnConfirmarVenta);

        // SCROLLPANE
        JScrollPane scrollPaneVentas = new JScrollPane();
        scrollPaneVentas.setBounds(57, 156, 408, 128);
        panel.add(scrollPaneVentas);

        // TABLA DE VENTAS
        tablaVentas = new JTable();

        tablaVentas.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Código",
                "Producto",
                "Cantidad",
                "Precio",
                "Subtotal"
            }
        ));

        // COLOCAR LA TABLA CORRECTAMENTE
        scrollPaneVentas.setViewportView(tablaVentas);

        // ETIQUETA TOTAL
        lblTotal = new JLabel("TOTAL: $ 0.00");
        lblTotal.setForeground(new Color(240, 255, 240));
        lblTotal.setFont(new Font("Arial", Font.BOLD, 18));
        lblTotal.setBounds(31, 327, 200, 45);
        panel.add(lblTotal);
    }

    // GETTERS DE LOS CAMPOS

    public JTextField getTxtCodigoProducto() {
        return txtCodigoProducto;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }

    // GETTERS DE LOS BOTONES

    public JButton getBtnBuscarProducto() {
        return btnBuscarProducto;
    }

    public JButton getBtnAgregarVenta() {
        return btnAgregarVenta;
    }

    public JButton getBtnConfirmarVenta() {
        return btnConfirmarVenta;
    }

    // GETTER DE LA TABLA

    public JTable getTablaVentas() {
        return tablaVentas;
    }

    // GETTER DEL TOTAL

    public JLabel getLblTotal() {
        return lblTotal;
    }
}
