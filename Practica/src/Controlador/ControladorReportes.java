package Controlador;

import Vista.PanelReportes;
import Logica.ReporteVentas;
import Logica.GestorPedidos;
import Modelo.pedido;
import Modelo.Detallepedido;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class ControladorReportes {

    private PanelReportes vista;
    private GestorPedidos gestorPedidos;

    public ControladorReportes(
            PanelReportes vista,
            GestorPedidos gestorPedidos) {

        this.vista = vista;
        this.gestorPedidos = gestorPedidos;

        vista.getBotonGenerar().addActionListener(
            e -> generarReporte()
        );
    }


private void generarReporte() {

	ArrayList<pedido> todosLosPedidos = gestorPedidos.getPedidos();
	ArrayList<pedido> pedidos = new ArrayList<pedido>();
	String fechaActual = LocalDate.now().toString();

	for (pedido p : todosLosPedidos) {
	    if (p.getfecha().startsWith(fechaActual)) {
	        pedidos.add(p);
	    }
	}
    StringBuilder texto = new StringBuilder();

    LinkedHashMap<String, Integer> unidades =
        new LinkedHashMap<String, Integer>();

    double subtotalVendido = 0;

    texto.append("===== REPORTE DE VENTAS =====\n\n");

    texto.append("Numero de pedidos: ")
         .append(pedidos.size()).append("\n\n");

    for (pedido p : pedidos) {

        subtotalVendido += p.Calculo();

        for (Detallepedido detalle : p.getdetalle()) {

            String clave = detalle.getProducto().getCodigo()
                + " - " + detalle.getProducto().getNombre();

            Integer cantidad = unidades.get(clave);

            if (cantidad == null) {
                cantidad = 0;
            }

            unidades.put(
                clave, cantidad + detalle.getCantidad()
            );
        }
    }

    double ivaVendido = subtotalVendido * 0.15;
    double totalVendido = subtotalVendido + ivaVendido;

    texto.append(String.format(
        "Subtotal vendido: $%.2f\n", subtotalVendido
    ));

    texto.append(String.format(
        "IVA (15%%): $%.2f\n", ivaVendido
    ));

    texto.append(String.format(
        "Total vendido con IVA: $%.2f\n\n", totalVendido
    ));

    texto.append("PRODUCTOS MAS VENDIDOS\n");

    if (unidades.isEmpty()) {
        texto.append("Todavia no hay productos vendidos hoy.\n");
    } else {
        ArrayList<Map.Entry<String, Integer>> listaProductos =
            new ArrayList<Map.Entry<String, Integer>>(unidades.entrySet());

        java.util.Collections.sort(listaProductos,
            new java.util.Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> a,
                                   Map.Entry<String, Integer> b) {
                    return b.getValue().compareTo(a.getValue());
                }
            });

        for (Map.Entry<String, Integer> entrada : listaProductos) {
            texto.append(entrada.getKey())
                 .append(" -> ")
                 .append(entrada.getValue())
                 .append(" unidades\n");
        }
    }

    vista.getAreaReporte().setText(texto.toString());
}

}
