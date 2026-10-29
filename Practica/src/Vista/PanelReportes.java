
package Vista;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class PanelReportes extends JPanel {

    private JTextArea areaReporte;
    private JButton botonGenerar;
    private JLabel etiquetaTitulo;

    public PanelReportes() {
        setLayout(new BorderLayout(10, 10));

        etiquetaTitulo = new JLabel("REPORTE DE VENTAS");

        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setText(
            "Aqui se mostrara el reporte de ventas.\n" +
            "Se incluiran el numero de pedidos,\n" +
            "el total vendido y los productos mas vendidos."
        );

        botonGenerar = new JButton("Generar reporte");

        JPanel panelBotones = new JPanel(new FlowLayout());
        panelBotones.add(botonGenerar);

        add(etiquetaTitulo, BorderLayout.NORTH);
        add(new JScrollPane(areaReporte), BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public JTextArea getAreaReporte() {
        return areaReporte;
    }

    public JButton getBotonGenerar() {
        return botonGenerar;
    }
}
