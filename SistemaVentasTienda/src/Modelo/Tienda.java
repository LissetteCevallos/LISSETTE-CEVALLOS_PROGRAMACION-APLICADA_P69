package Modelo;

import java.util.ArrayList;

public class Tienda {

    // Lista de productos
    private ArrayList<Producto> productos;

    // Constructor
    public Tienda() {
        productos = new ArrayList<>();
    }

    // Agregar producto
    public boolean agregarProducto(Producto producto) {

        if (producto == null ||
            producto.getCodigo() == null ||
            producto.getCodigo().trim().isEmpty() ||
            producto.getNombre() == null ||
            producto.getNombre().trim().isEmpty() ||
            producto.getPrecio() < 0 ||
            !Double.isFinite(producto.getPrecio()) ||
            producto.getStock() < 0) {
            return false;
        }

        if (buscarProducto(producto.getCodigo()) != null) {
            return false;
        }

        productos.add(producto);
        return true;
    }

    // Listar productos
    public ArrayList<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    // Buscar producto por codigo
    public Producto buscarProducto(String codigo) {

        if (codigo == null) {
            return null;
        }

        for (Producto producto : productos) {

            if (producto.getCodigo().equalsIgnoreCase(codigo.trim())) {
                return producto;
            }
        }

        return null;
    }

    // Eliminar producto
    public boolean eliminarProducto(String codigo) {

        Producto producto = buscarProducto(codigo);

        if (producto != null) {
            productos.remove(producto);
            return true;
        }

        return false;
    }

    // Actualizar stock
    public boolean actualizarStock(String codigo, int nuevoStock) {

        Producto producto = buscarProducto(codigo);

        if (producto != null && nuevoStock >= 0) {
            producto.setStock(nuevoStock);
            return true;
        }

        return false;
    }

    // Genericos y varargs
    @SafeVarargs
    public static <T> ArrayList<T> crearLista(T... elementos) {

        ArrayList<T> lista = new ArrayList<>();

        for (T elemento : elementos) {
            lista.add(elemento);
        }

        return lista;
    }

    // Agregar varios productos
    public void agregarVarios(Producto... nuevosProductos) {

        for (Producto producto : nuevosProductos) {
            agregarProducto(producto);
        }
    }

    // Cargar productos desde un archivo
    public void cargarProductos(ArrayList<Producto> productosCargados) {

        productos.clear();

        if (productosCargados != null) {
            for (Producto producto : productosCargados) {
                agregarProducto(producto);
            }
        }
    }
}
