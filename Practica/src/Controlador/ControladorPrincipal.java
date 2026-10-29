package Controlador;

import Vista.VentanaPrincipal;
import Logica.GestorProducto;
import Logica.GestorPedidos;
import Persistencia.ArchivoProductos;
import Persistencia.Archivopedidos;

import javax.swing.SwingUtilities;

public class ControladorPrincipal {

    public void iniciar() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                GestorProducto gp = new GestorProducto(
                    new ArchivoProductos("productos.txt")
                );

                GestorPedidos gped = new GestorPedidos(
                    gp,
                    new Archivopedidos("pedidos.txt")
                );

                VentanaPrincipal ventana =
                    new VentanaPrincipal(gp, gped);

                ventana.setVisible(true);
            }
        });
    }
}
