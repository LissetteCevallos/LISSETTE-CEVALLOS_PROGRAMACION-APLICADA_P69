package Persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Producto;

public class ArchivoProductos {
    private File archivo;

    public ArchivoProductos(String ruta) {
        this.archivo = new File(ruta);
    }

    // RF-09: formato codigo;nombre;precio;stock
    public void guardar(ArrayList<Producto> lista) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Producto p : lista) {
                pw.println(p.getCodigo() + ";" + p.getNombre() + ";" + p.getPrecio() + ";" + p.getstock());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    // RF-10 y RNF-08: archivo inexistente o lineas danadas no detienen el programa
    public ArrayList<Producto> cargar() {
        ArrayList<Producto> lista = new ArrayList<>();

        if (!archivo.exists()) {
            return lista;
        }

        try (Scanner sc = new Scanner(archivo)) {
            int numLinea = 0;
            while (sc.hasNextLine()) {
                numLinea++;
                String linea = sc.nextLine().trim();
                if (linea.isEmpty()) continue;

                try {
                    String[] campos = linea.split(";");
                    if (campos.length != 4) {
                        throw new IllegalArgumentException("campos incorrectos");
                    }
                    String codigo = campos[0].trim();
                    String nombre = campos[1].trim();
                    double precio = Double.parseDouble(campos[2].trim());
                    int stock = Integer.parseInt(campos[3].trim());

                    if (codigo.isEmpty() || nombre.isEmpty() || precio <= 0 || stock < 0) {
                        throw new IllegalArgumentException("valores incorrectos");
                    }
                    if (existe(lista, codigo)) {
                        throw new IllegalArgumentException("codigo duplicado");
                    }
                    lista.add(new Producto(codigo, nombre, precio, stock));
                } catch (IllegalArgumentException e) { // incluye NumberFormatException
                    System.out.println("Linea " + numLinea + " danada en " + archivo.getName() + ", se omite.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo de productos.");
        }
        return lista;
    }

    private boolean existe(ArrayList<Producto> lista, String codigo) {
        for (Producto p : lista) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) return true;
        }
        return false;
    }
}
