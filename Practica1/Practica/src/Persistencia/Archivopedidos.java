package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Detallepedido;
import Modelo.pedido;
import Modelo.Producto;

public class Archivopedidos {
    private File archivo;

    public Archivopedidos(String ruta) {
        this.archivo = new File(ruta);
    }

    // RF-09: formato id;fecha;codProducto:cantidad:precioUnitario|...
    public void guardar(ArrayList<pedido> pedidos) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (pedido p : pedidos) {
                StringBuilder sb = new StringBuilder();
                sb.append(p.get_id()).append(";").append(p.getfecha()).append(";");

                ArrayList<Detallepedido> dets = p.getdetalle();
                for (int i = 0; i < dets.size(); i++) {
                    Detallepedido d = dets.get(i);
                    sb.append(d.getProducto().getCodigo()).append(":")
                      .append(d.getCantidad()).append(":")
                      .append(d.getPrecio_unit());
                    if (i < dets.size() - 1) sb.append("|");
                }
                pw.println(sb);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pedidos: " + e.getMessage());
        }
    }

    // Recibe el catalogo porque cada detalle apunta a un objeto Producto
    public ArrayList<pedido> cargar(ArrayList<Producto> catalogo) {
        ArrayList<pedido> pedidos = new ArrayList<>();

        if (!archivo.exists()) {
            return pedidos;
        }

        try (Scanner sc = new Scanner(archivo)) {
            int numLinea = 0;
            while (sc.hasNextLine()) {
                numLinea++;
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;

                try {
                    String[] partes = linea.split(";");
                    if (partes.length != 3) {
                        throw new IllegalArgumentException("formato incorrecto");
                    }
                    int id = Integer.parseInt(partes[0].trim());
                    pedido ped = new pedido(id, partes[1].trim());

                    for (String det : partes[2].split("\\|")) {
                        String[] c = det.split(":");
                        if (c.length != 3) {
                            throw new IllegalArgumentException("detalle incorrecto");
                        }
                        Producto prod = buscar(catalogo, c[0].trim());
                        if (prod == null) {
                            throw new IllegalArgumentException("producto no existe");
                        }
                        int cantidad = Integer.parseInt(c[1].trim());
                        double precio = Double.parseDouble(c[2].trim());
                        if (cantidad <= 0 || precio <= 0) {
                            throw new IllegalArgumentException("valores incorrectos");
                        }
                        ped.agregardetalle(new Detallepedido(prod, cantidad, precio));
                    }
                    pedidos.add(ped);
                } catch (IllegalArgumentException e) {
                    System.out.println("Linea " + numLinea + " danada en " + archivo.getName() + ", se omite.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo de pedidos.");
        }
        return pedidos;
    }

    private Producto buscar(ArrayList<Producto> catalogo, String codigo) {
        for (Producto p : catalogo) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) return p;
        }
        return null;
    }
}
