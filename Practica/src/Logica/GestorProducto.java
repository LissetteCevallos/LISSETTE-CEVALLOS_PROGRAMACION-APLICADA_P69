package Logica;

import java.util.ArrayList;

import Modelo.Producto;
import Persistencia.ArchivoProductos;

public class GestorProducto {
    private ArrayList<Producto> productos;
    private ArchivoProductos archivo;

    public GestorProducto(ArchivoProductos archivo) {
        this.archivo = archivo;
        this.productos = archivo.cargar(); // RF-10: carga al iniciar
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    // RF-01: valida y registra. Devuelve un mensaje para mostrar al usuario
    public String registrar(String codigo, String nombre, double precio, int stock) {
        if (codigo.isEmpty() || nombre.isEmpty()) {
            return "Error: el codigo y el nombre no pueden estar vacios.";
        }
        if (tieneSeparadores(codigo) || tieneSeparadores(nombre)) {
            return "Error: no use los caracteres ; : | en el codigo ni en el nombre.";
        }
        if (buscar(codigo) != null) {
            return "Error: ya existe un producto con el codigo " + codigo + ".";
        }
        if (precio <= 0) {
            return "Error: el precio debe ser mayor que cero.";
        }
        if (stock < 0) {
            return "Error: el stock no puede ser negativo.";
        }
        productos.add(new Producto(codigo, nombre, precio, stock));
        guardar(); // RF-09
        return "Producto registrado correctamente.";
    }

    // RF-02: listar
    public void listar() {
        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        System.out.println("--- CATALOGO ---");
        for (Producto p : productos) {
            System.out.println(p);
        }
    }

    public Producto buscar(String codigo) {
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    // RF-03: reponer stock
    public String reponerStock(String codigo, int cantidad) {
        Producto p = buscar(codigo);
        if (p == null) {
            return "Error: no existe el producto " + codigo + ".";
        }
        if (cantidad <= 0) {
            return "Error: la cantidad debe ser mayor que cero.";
        }
        p.reponer(cantidad);
        guardar();
        return "Stock actualizado. Nuevo stock: " + p.getstock();
    }

    // RF-09: lo usa tambien GestorPedidos despues de descontar stock
    public void guardar() {
        archivo.guardar(productos);
    }

    // Estos caracteres romperian el formato de los archivos .txt
    private boolean tieneSeparadores(String texto) {
        return texto.contains(";") || texto.contains(":") || texto.contains("|");
    }
}