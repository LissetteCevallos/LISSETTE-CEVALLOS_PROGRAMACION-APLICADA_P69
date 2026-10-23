package presentacion;

import java.util.Scanner;

import Logica.GestorPedidos;
import Logica.GestorProducto;
import Logica.ReporteVentas;
import Modelo.pedido;
import Persistencia.Archivopedidos;
import Persistencia.ArchivoProductos;

public class main {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestorProducto gp = new GestorProducto(new ArchivoProductos("productos.txt"));
        GestorPedidos gped = new GestorPedidos(gp, new Archivopedidos("pedidos.txt"));
        ReporteVentas reporte = new ReporteVentas();
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Elija una opcion: ");

            switch (opcion) {
                case 1:
                    registrarProducto(gp);
                    break;
                case 2:
                    gp.listar();
                    break;
                case 3:
                    reponerStock(gp);
                    break;
                case 4:
                    registrarPedido(gp, gped);
                    break;
                case 5:
                    reporte.generar(gped.getPedidos());
                    break;
                case 0:
                    System.out.println("Hasta luego.");
                    break;
                default:
                    System.out.println("Opcion no valida.");
            }
            System.out.println();
        } while (opcion != 0);
    }

    private static void mostrarMenu() {
        System.out.println("===== EL BUEN SABOR =====");
        System.out.println("1. Registrar producto");
        System.out.println("2. Listar catalogo");
        System.out.println("3. Reponer stock");
        System.out.println("4. Registrar pedido");
        System.out.println("5. Reporte de ventas");
        System.out.println("0. Salir");
    }

    private static void registrarProducto(GestorProducto gp) {
        String codigo = leerTexto("Codigo: ");
        String nombre = leerTexto("Nombre: ");
        double precio = leerDecimal("Precio: ");
        int stock = leerEntero("Stock inicial: ");
        System.out.println(gp.registrar(codigo, nombre, precio, stock));
    }

    private static void reponerStock(GestorProducto gp) {
        String codigo = leerTexto("Codigo del producto: ");
        int cantidad = leerEntero("Cantidad a reponer: ");
        System.out.println(gp.reponerStock(codigo, cantidad));
    }

    private static void registrarPedido(GestorProducto gp, GestorPedidos gped) {
        if (gp.getProductos().isEmpty()) {
            System.out.println("No hay productos registrados. Registre uno primero.");
            return;
        }
        gp.listar();
        pedido pedido = gped.crearPedido();

        boolean otro = true;
        while (otro) {
            String codigo = leerTexto("Codigo del producto: ");
            int cantidad = leerEntero("Cantidad: ");
            System.out.println(gped.agregarProducto(pedido, codigo, cantidad));
            otro = leerSiNo("Agregar otro producto? (s/n): ");
        }

        if (pedido.getdetalle().isEmpty()) {
            System.out.println("Pedido cancelado: no se agregaron productos.");
            return;
        }
        System.out.println();
        pedido.Resumen();
        if (leerSiNo("Confirmar pedido? (s/n): ")) {
            System.out.println(gped.confirmar(pedido));
        } else {
            System.out.println("Pedido cancelado. El stock no fue modificado.");
        }
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero entero valido.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un numero valido.");
            }
        }
    }

    private static boolean leerSiNo(String mensaje) {
        while (true) {
            String r = leerTexto(mensaje).toLowerCase();
            if (r.equals("s")) return true;
            if (r.equals("n")) return false;
            System.out.println("Responda con s o n.");
        }
    }
}