package Logica;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import Modelo.Detallepedido;
import Modelo.pedido;

public class ReporteVentas {

    // RF-08: numero de pedidos, total vendido y ranking de productos
    public void generar(ArrayList<pedido> pedidos) {
        System.out.println("--- REPORTE DE VENTAS ---");
        if (pedidos.isEmpty()) {
            System.out.println("Aun no hay pedidos registrados.");
            return;
        }

        double totalVendido = 0;
        LinkedHashMap<String, Integer> unidades = new LinkedHashMap<>();

        for (pedido p : pedidos) {
            totalVendido += p.Calculo();
            for (Detallepedido d : p.getdetalle()) {
                String clave = d.getProducto().getCodigo() + " - " + d.getProducto().getNombre();
                unidades.put(clave, unidades.getOrDefault(clave, 0) + d.getCantidad());
            }
        }

        System.out.println("Numero de pedidos: " + pedidos.size());
        System.out.printf("Total vendido: $%.2f%n", totalVendido);
        System.out.println("Ranking de productos mas vendidos:");

        ArrayList<Map.Entry<String, Integer>> ranking = new ArrayList<>(unidades.entrySet());
        ranking.sort((a, b) -> b.getValue() - a.getValue());

        int puesto = 1;
        for (Map.Entry<String, Integer> e : ranking) {
            System.out.println("  " + puesto + ". " + e.getKey() + " -> " + e.getValue() + " unidades");
            puesto++;
        }
    }
}