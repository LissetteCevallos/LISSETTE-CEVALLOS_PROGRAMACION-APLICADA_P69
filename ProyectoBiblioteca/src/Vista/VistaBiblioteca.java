
package Vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VistaBiblioteca extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    // CAMPOS DE TEXTO
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAutor;

    // BOTONES
    private JButton btnAgregar;
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JButton btnPrestar;
    private JButton btnDevolver;

    // INICIAR LA VENTANA
    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VistaBiblioteca frame = new VistaBiblioteca();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // CONSTRUCTOR
    public VistaBiblioteca() {

        setBackground(Color.BLACK);
        setTitle("Sistema de Gestion de Biblioteca");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 685, 446);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(1, 0, 0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setToolTipText("");
        contentPane.add(panel);
        panel.setLayout(null);

        // ETIQUETA CODIGO
        JLabel lbl_Codigo = new JLabel("Código: ");
        lbl_Codigo.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lbl_Codigo.setForeground(Color.WHITE);
        lbl_Codigo.setBounds(36, 29, 60, 14);
        panel.add(lbl_Codigo);

        // ETIQUETA TITULO
        JLabel lbl_Titulo = new JLabel("Título: ");
        lbl_Titulo.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lbl_Titulo.setForeground(Color.WHITE);
        lbl_Titulo.setBounds(378, 29, 60, 14);
        panel.add(lbl_Titulo);

        // ETIQUETA AUTOR
        JLabel lbl_Autor = new JLabel("Autor: ");
        lbl_Autor.setFont(new Font("Times New Roman", Font.BOLD, 13));
        lbl_Autor.setForeground(Color.WHITE);
        lbl_Autor.setBounds(36, 85, 60, 14);
        panel.add(lbl_Autor);

        // CAMPO CODIGO
        txtCodigo = new JTextField();
        txtCodigo.setBackground(Color.LIGHT_GRAY);
        txtCodigo.setBounds(76, 54, 188, 20);
        panel.add(txtCodigo);
        txtCodigo.setColumns(10);

        // CAMPO TITULO
        txtTitulo = new JTextField();
        txtTitulo.setBackground(Color.LIGHT_GRAY);
        txtTitulo.setBounds(388, 54, 211, 20);
        panel.add(txtTitulo);
        txtTitulo.setColumns(10);

        // CAMPO AUTOR
        txtAutor = new JTextField();
        txtAutor.setBackground(Color.LIGHT_GRAY);
        txtAutor.setBounds(75, 107, 412, 20);
        panel.add(txtAutor);
        txtAutor.setColumns(10);

        // BOTON AGREGAR
        btnAgregar = new JButton("Agregar");
        btnAgregar.setBounds(127, 183, 89, 23);
        panel.add(btnAgregar);

        // BOTON LISTAR
        btnListar = new JButton("Listar");
        btnListar.setBounds(285, 251, 89, 23);
        panel.add(btnListar);

       
        btnBuscar = new JButton("Buscar");
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setBounds(439, 251, 89, 23);
        panel.add(btnBuscar);

      
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(127, 251, 89, 23);
        panel.add(btnEliminar);

       
        btnPrestar = new JButton("Prestar");
        btnPrestar.setBounds(285, 183, 89, 23);
        panel.add(btnPrestar);


        btnDevolver = new JButton("Devolver");
        btnDevolver.setBounds(439, 183, 89, 23);
        panel.add(btnDevolver);
    }

    // GETTERS DE LOS CAMPOS DE TEXTO

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JTextField getTxtTitulo() {
        return txtTitulo;
    }

    public JTextField getTxtAutor() {
        return txtAutor;
    }

    // GETTERS DE LOS BOTONES

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnPrestar() {
        return btnPrestar;
    }

    public JButton getBtnDevolver() {
        return btnDevolver;
    }
}

