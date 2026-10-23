package Logica;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import Modelo.Detallepedido;
import Modelo.pedido;
import Modelo.Producto;
import Persistencia.Archivopedidos;

public class GestorPedidos {
    private ArrayList<pedido> pedidos;
    private GestorProducto gestorProductos;
    private Archivopedidos archivo;

    public GestorPedidos(GestorProducto gestorProductos, Archivopedidos archivo) {
        this.gestorProductos = gestorProductos;
        this.archivo = archivo;
        this.pedidos = archivo.cargar(gestorProductos.getProductos()); // RF-10
    }

    public ArrayList<pedido> getPedidos() {
        return pedidos;
    }

    // Crea un pedido vacio; solo se guarda en la lista al confirmarlo
    public pedido crearPedido() {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return new pedido(siguienteId(), fecha);
    }

    // RF-04 y RF-05: agrega un producto validando la disponibilidad
    public String agregarProducto(pedido p, String codigo, int cantidad) {
        Producto prod = gestorProductos.buscar(codigo);
        if (prod == null) {
            return "Error: no existe el producto " + codigo + ".";
        }
        if (cantidad <= 0) {
            return "Error: la cantidad debe ser mayor que cero.";
        }
        if (prod.getstock() == 0) {
            return "Error: el producto " + prod.getNombre() + " esta agotado.";
        }
        // Se descuenta lo que ya se pidio de este producto en el mismo pedido
        int disponible = prod.getstock() - cantidadEnPedido(p, prod);
        if (cantidad > disponible) {
            return "Error: stock insuficiente. Disponible: " + disponible;
        }
        p.agregardetalle(new Detallepedido(prod, cantidad, prod.getPrecio()));
        return "Agregado: " + prod.getNombre() + " x" + cantidad;
    }

    // RF-07 y RF-09: descuenta stock y guarda
    public String confirmar(pedido p) {
        if (p.getdetalle().isEmpty()) {
            return "Error: el pedido no tiene productos.";
        }
        for (Detallepedido d : p.getdetalle()) {
            if (!d.getProducto().hayStock(cantidadEnPedido(p, d.getProducto()))) {
                return "Error: stock insuficiente para " + d.getProducto().getNombre() + ".";
            }
        }
        for (Detallepedido d : p.getdetalle()) {
            d.getProducto().descontar(d.getCantidad());
        }
        pedidos.add(p);
        gestorProductos.guardar();
        archivo.guardar(pedidos);
        return String.format("Pedido #%d confirmado. Total a pagar: $%.2f", p.get_id(), p.Calculo());
    }

    private int cantidadEnPedido(pedido p, Producto prod) {
        int total = 0;
        for (Detallepedido d : p.getdetalle()) {
            if (d.getProducto() == prod) {
                total += d.getCantidad();
            }
        }
        return total;
    }

    private int siguienteId() {
        int max = 0;
        for (pedido p : pedidos) {
            if (p.get_id() > max) max = p.get_id();
        }
        return max + 1;
    }
}
