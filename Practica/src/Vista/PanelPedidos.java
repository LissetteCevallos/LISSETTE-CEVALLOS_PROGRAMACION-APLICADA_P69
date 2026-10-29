
package Vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class PanelPedidos extends JPanel {

    private JComboBox<String> comboProductos;
    private JTextField campoCantidad;
    private JButton botonAgregar;
    private JButton botonConfirmar;
    private JTable tablaDetalles;
    private DefaultTableModel modeloTabla;
    private JLabel etiquetaSubtotal;
    private JLabel etiquetaIVA;
    private JLabel etiquetaTotal;

    public PanelPedidos() {
        setLayout(new BorderLayout(10, 10));

        JPanel panelEntrada = new JPanel(new GridLayout(2, 2, 5, 5));

        panelEntrada.add(new JLabel("Producto:"));
        comboProductos = new JComboBox<String>();
        panelEntrada.add(comboProductos);

        panelEntrada.add(new JLabel("Cantidad:"));
        campoCantidad = new JTextField();
        panelEntrada.add(campoCantidad);

        modeloTabla = new DefaultTableModel(
            new Object[]{"Producto", "Cantidad", "Precio", "Subtotal"}, 0
        );

        tablaDetalles = new JTable(modeloTabla);

        JPanel panelInferior = new JPanel(new BorderLayout());

        JPanel panelBotones = new JPanel(new FlowLayout());
        botonAgregar = new JButton("Agregar producto");
        botonConfirmar = new JButton("Confirmar pedido");

        panelBotones.add(botonAgregar);
        panelBotones.add(botonConfirmar);

        JPanel panelTotales = new JPanel(new GridLayout(3, 1));
        etiquetaSubtotal = new JLabel("Subtotal: $0.00");
        etiquetaIVA = new JLabel("IVA: $0.00");
        etiquetaTotal = new JLabel("Total: $0.00");

        panelTotales.add(etiquetaSubtotal);
        panelTotales.add(etiquetaIVA);
        panelTotales.add(etiquetaTotal);

        panelInferior.add(panelBotones, BorderLayout.NORTH);
        panelInferior.add(panelTotales, BorderLayout.SOUTH);

        add(panelEntrada, BorderLayout.NORTH);
        add(new JScrollPane(tablaDetalles), BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public JComboBox<String> getComboProductos() {
        return comboProductos;
    }

    public JTextField getCampoCantidad() {
        return campoCantidad;
    }

    public JButton getBotonAgregar() {
        return botonAgregar;
    }

    public JButton getBotonConfirmar() {
        return botonConfirmar;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JLabel getEtiquetaSubtotal() {
        return etiquetaSubtotal;
    }

    public JLabel getEtiquetaIVA() {
        return etiquetaIVA;
    }

    public JLabel getEtiquetaTotal() {
        return etiquetaTotal;
    }
}
