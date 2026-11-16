package controlador;

import java.io.IOException;
import java.util.ArrayList;
import modelo.Aportador;
import modelo.LectorRegistro;
import vista.VistaConsola;

public class Controlador {
    private static final String RUTA = "registro_ingresos.txt";

    private ArrayList<Aportador> aportadores = new ArrayList<>();
    private LectorRegistro lector = new LectorRegistro();
    private VistaConsola vista = new VistaConsola();

    public void iniciar() {
        int op;
        do {
            op = vista.mostrarMenu();
            switch (op) {
            case 1:
                cargarArchivo();
                break;
            case 2:
                if (hayDatos()) vista.mostrarListado(aportadores);
                break;
            case 3:
                if (hayDatos()) mostrarResumen();
                break;
            case 0:
                vista.mostrarMensaje("Hasta pronto.");
                break;
            default:
                vista.mostrarMensaje("Opción no válida.");
            }
        } while (op != 0);
    }

    private void cargarArchivo() {
        try {
            aportadores = lector.leer(RUTA);
            vista.mostrarMensaje("Se procesaron " + aportadores.size() + " registros.");
        } catch (IOException e) {
            vista.mostrarMensaje("Error al leer el archivo: " + e.getMessage());
        }
    }

    private boolean hayDatos() {
        if (aportadores.isEmpty()) {
            vista.mostrarMensaje("Primero cargue el archivo (opción 1).");
            return false;
        }
        return true;
    }

    private void mostrarResumen() {
        double total = 0;
        int validos = 0, errCedula = 0, errCorreo = 0, errMonto = 0;

        for (Aportador a : aportadores) {
            if (a.isValido()) {
                total += a.getMonto(); // solo suma los registros correctos
                validos++;
            }
            if (!a.isCedulaValida()) errCedula++;
            if (!a.isCorreoValido()) errCorreo++;
            if (!a.isMontoValido()) errMonto++;
        }
        vista.mostrarResumen(aportadores.size(), validos, total, errCedula, errCorreo, errMonto);
    }
}
