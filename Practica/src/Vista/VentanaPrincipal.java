package Vista;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import Logica.GestorProducto;
import Logica.GestorPedidos;
import Controlador.ControladorProductos;
import Controlador.ControladorPedidos;
import Controlador.ControladorReportes;

public class VentanaPrincipal extends JFrame {

    private JButton botonProductos;
    private JButton botonPedidos;
    private JButton botonReportes;

    private GestorProducto gestorProductos;
    private GestorPedidos gestorPedidos;

    public VentanaPrincipal(
            GestorProducto gestorProductos,
            GestorPedidos gestorPedidos) {

        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

        setTitle("El Buen Sabor - Sistema de Cafeteria");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel titulo = new JLabel(
            "SISTEMA DE GESTION EL BUEN SABOR",
            JLabel.CENTER
        );

        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(3, 1, 10, 10));

        botonProductos = new JButton("Gestionar Productos");
        botonPedidos = new JButton("Registrar Pedidos");
        botonReportes = new JButton("Consultar Reportes");

        panelBotones.add(botonProductos);
        panelBotones.add(botonPedidos);
        panelBotones.add(botonReportes);

        add(titulo, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);

        botonProductos.addActionListener(e -> abrirProductos());
        botonPedidos.addActionListener(e -> abrirPedidos());
        botonReportes.addActionListener(e -> abrirReportes());
    }

    private void abrirProductos() {
        PanelProductos panel = new PanelProductos();

        new ControladorProductos(panel, gestorProductos);

        abrirVentana("Gestion de Productos", panel);
    }

    private void abrirPedidos() {
        PanelPedidos panel = new PanelPedidos();

        new ControladorPedidos(
            panel, gestorProductos, gestorPedidos
        );

        abrirVentana("Registro de Pedidos", panel);
    }

    private void abrirReportes() {
        PanelReportes panel = new PanelReportes();

        new ControladorReportes(panel, gestorPedidos);

        abrirVentana("Reportes de Ventas", panel);
    }

    private void abrirVentana(String titulo, JPanel panel) {
        JFrame ventana = new JFrame(titulo);
        ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventana.setSize(750, 500);
        ventana.setLocationRelativeTo(this);
        ventana.add(panel);
        ventana.setVisible(true);
    }
}
