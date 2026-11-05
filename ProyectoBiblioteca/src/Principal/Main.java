package Principal;

import javax.swing.SwingUtilities;

import Vista.VistaBiblioteca;
import Controlador.BibliotecaControlador;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            VistaBiblioteca vista = new VistaBiblioteca();

            new BibliotecaControlador(vista);

            vista.setVisible(true);
        });
    }
}

