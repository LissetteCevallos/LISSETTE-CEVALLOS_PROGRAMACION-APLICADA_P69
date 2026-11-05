package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VistaProducto extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // CAMPOS DE TEXTO
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;

    // BOTONES
    private JButton btnAgregar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnActualizarStock;
    private JButton btnListar;

    // TABLA
    private JTable tablaProductos;

    // INICIAR VENTANA
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaProducto frame = new VistaProducto();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // CONSTRUCTOR
    public VistaProducto() {

        setTitle("Gestion de Productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 676, 487);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // PANEL PRINCIPAL
        JPanel panel = new JPanel();
        panel.setBackground(new Color(70, 130, 180));
        panel.setBounds(0, 0, 660, 448);
        contentPane.add(panel);
        panel.setLayout(null);

        // ETIQUETA CODIGO
        JLabel lblCodigo = new JLabel("Código :");
        lblCodigo.setForeground(new Color(211, 211, 211));
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 14));
        lblCodigo.setBounds(54, 27, 72, 25);
        panel.add(lblCodigo);

        // ETIQUETA NOMBRE
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.BOLD, 13));
        lblNombre.setForeground(new Color(240, 248, 255));
        lblNombre.setBounds(352, 33, 72, 14);
        panel.add(lblNombre);

        // ETIQUETA PRECIO
        JLabel lblPrecio = new JLabel("Precio: ");
        lblPrecio.setForeground(new Color(240, 248, 255));
        lblPrecio.setFont(new Font("Arial", Font.BOLD, 13));
        lblPrecio.setBounds(54, 94, 66, 25);
        panel.add(lblPrecio);

        // ETIQUETA STOCK
        JLabel lblStock = new JLabel("Stock :");
        lblStock.setFont(new Font("Arial", Font.BOLD, 13));
        lblStock.setForeground(new Color(240, 248, 255));
        lblStock.setBounds(362, 94, 58, 25);
        panel.add(lblStock);

        // CAMPO CODIGO
        txtCodigo = new JTextField();
        txtCodigo.setBounds(54, 50, 245, 33);
        panel.add(txtCodigo);
        txtCodigo.setColumns(10);

        // CAMPO NOMBRE
        txtNombre = new JTextField();
        txtNombre.setBounds(352, 50, 245, 33);
        panel.add(txtNombre);
        txtNombre.setColumns(10);

        // CAMPO PRECIO
        txtPrecio = new JTextField();
        txtPrecio.setBounds(59, 118, 240, 33);
        panel.add(txtPrecio);
        txtPrecio.setColumns(10);

        // CAMPO STOCK
        txtStock = new JTextField();
        txtStock.setBounds(352, 118, 251, 33);
        panel.add(txtStock);
        txtStock.setColumns(10);

        // BOTON AGREGAR
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(41, 190, 180, 23);
        panel.add(btnAgregar);

        // BOTON BUSCAR
        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(253, 190, 142, 23);
        panel.add(btnBuscar);

        // BOTON ELIMINAR
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(420, 190, 187, 23);
        panel.add(btnEliminar);

        // BOTON ACTUALIZAR STOCK
        btnActualizarStock = new JButton("Actualizar Stock");
        btnActualizarStock.setBounds(145, 231, 174, 23);
        panel.add(btnActualizarStock);

        // BOTON LISTAR
        btnListar = new JButton("Listar");
        btnListar.setBounds(340, 231, 153, 23);
        panel.add(btnListar);

        // PANEL DESPLAZABLE
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 281, 537, 156);
        panel.add(scrollPane);

        // TABLA DE PRODUCTOS
        tablaProductos = new JTable();

        tablaProductos.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {
                "Código", "Nombre", "Precio", "Stock"
            }
        ));

        // COLOCAR TABLA DENTRO DEL SCROLLPANE
        scrollPane.setViewportView(tablaProductos);
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

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnActualizarStock() {
        return btnActualizarStock;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    // GETTER DE LA TABLA

    public JTable getTablaProductos() {
        return tablaProductos;
    }
}
