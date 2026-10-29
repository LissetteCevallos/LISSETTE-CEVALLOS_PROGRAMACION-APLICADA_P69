
package Vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PanelProductos extends JPanel {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JButton botonRegistrar;
    private JButton botonEditar;
    private JButton botonReponer;

    public PanelProductos() {
        setLayout(new BorderLayout(10, 10));

        JLabel titulo = new JLabel("GESTION DE PRODUCTOS");

        modeloTabla = new DefaultTableModel(
            new Object[]{"Codigo", "Nombre", "Precio", "Stock"}, 0
        );

        tablaProductos = new JTable(modeloTabla);

        JScrollPane desplazamiento = new JScrollPane(tablaProductos);

        JPanel panelBotones = new JPanel(new FlowLayout());

        botonRegistrar = new JButton("Registrar");
        botonEditar = new JButton("Editar");
        botonReponer = new JButton("Reponer stock");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonEditar);
        panelBotones.add(botonReponer);

        add(titulo, BorderLayout.NORTH);
        add(desplazamiento, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTable getTablaProductos() {
        return tablaProductos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getBotonRegistrar() {
        return botonRegistrar;
    }

    public JButton getBotonEditar() {
        return botonEditar;
    }

    public JButton getBotonReponer() {
        return botonReponer;
    }
    
    public void limpiarTabla() {
    modeloTabla.setRowCount(0);
    }

}
