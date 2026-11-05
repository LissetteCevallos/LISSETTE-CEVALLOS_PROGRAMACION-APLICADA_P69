
package Principal;

import java.awt.EventQueue;
import javax.swing.JOptionPane;
import Vista.VistaHotel;

public class Main {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                VistaHotel ventana = new VistaHotel();
                ventana.setLocationRelativeTo(null);
                ventana.setVisible(true);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Error al iniciar el sistema: "
                        + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        });
    }
}
