
package Principal;

import javax.swing.SwingUtilities;

import Vista.VistaTienda;
import Controlador.TiendaControlador;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VistaTienda vista = new VistaTienda();

            new TiendaControlador(vista);

            vista.setVisible(true);
        });
    }
}
