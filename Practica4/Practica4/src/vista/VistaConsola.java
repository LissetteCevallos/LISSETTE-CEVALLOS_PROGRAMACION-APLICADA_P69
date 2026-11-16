package vista;

import java.util.ArrayList;
import java.util.Scanner;
import modelo.Aportador;

public class VistaConsola {
    private Scanner sc = new Scanner(System.in);

    public int mostrarMenu() {
        System.out.println("\n--- REGISTRO DE INGRESOS DEL TESORERO ---");
        System.out.println("1. Leer y procesar el archivo");
        System.out.println("2. Mostrar listado con validación");
        System.out.println("3. Total válido y resumen de errores");
        System.out.println("0. Salir");
        System.out.print("Opción: ");
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1; // si escriben una letra, no se cierra el programa
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarListado(ArrayList<Aportador> lista) {
        System.out.printf("%-4s %-12s %-32s %-10s %s%n",
                "N°", "CÉDULA", "CORREO", "MONTO", "VALIDACIÓN");
        int i = 1;
        for (Aportador a : lista) {
            System.out.printf("%-4d %-12s %-32s %-10s %s%n",
                    i++,
                    texto(a.getCedula()),
                    texto(a.getCorreo()),
                    texto(a.getMontoTexto()),
                    a.isValido() ? "VÁLIDO" : "ERROR en: " + a.getErrores());
        }
    }

    public void mostrarResumen(int totalRegistros, int validos, double totalValido,
                               int errCedula, int errCorreo, int errMonto) {
        System.out.println("\n--- RESUMEN ---");
        System.out.println("Registros procesados : " + totalRegistros);
        System.out.println("Registros válidos    : " + validos);
        System.out.printf("Total de ingresos válidos: $%.2f%n", totalValido);
        System.out.println("Cédulas con error    : " + errCedula);
        System.out.println("Correos con error    : " + errCorreo);
        System.out.println("Montos con error     : " + errMonto);
    }

    private String texto(String t) {
        return (t == null) ? "NO ENCONTRADO" : t;
    }
}
