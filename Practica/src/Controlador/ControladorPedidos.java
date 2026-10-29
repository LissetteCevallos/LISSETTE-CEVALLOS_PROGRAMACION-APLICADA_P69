package Controlador;

import Vista.PanelPedidos;
import Logica.GestorProducto;
import Logica.GestorPedidos;
import Modelo.Producto;
import Modelo.pedido;
import Modelo.Detallepedido;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class ControladorPedidos {

    private PanelPedidos vista;
    private GestorProducto gestorProductos;
    private GestorPedidos gestorPedidos;
    private pedido pedidoActual;

    public ControladorPedidos(
            PanelPedidos vista,
            GestorProducto gestorProductos,
            GestorPedidos gestorPedidos) {

        this.vista = vista;
        this.gestorProductos = gestorProductos;
        this.gestorPedidos = gestorPedidos;

        cargarProductos();

        vista.getBotonAgregar().addActionListener(e -> agregarProducto());
        vista.getBotonConfirmar().addActionListener(e -> confirmarPedido());
    }

    private void cargarProductos() {
        vista.getComboProductos().removeAllItems();

        for (Producto producto : gestorProductos.getProductos()) {
            vista.getComboProductos().addItem(
                producto.getCodigo() + " - " + producto.getNombre()
            );
        }
    }

    private void agregarProducto() {
        try {
            if (vista.getComboProductos().getSelectedItem() == null) {
                JOptionPane.showMessageDialog(vista,
                    "No hay productos disponibles.");
                return;
            }

            int cantidad = Integer.parseInt(
                vista.getCampoCantidad().getText().trim()
            );

            String seleccion = vista.getComboProductos()
                                   .getSelectedItem().toString();

            String codigo = seleccion.split(" - ", 2)[0];

            if (pedidoActual == null) {
                pedidoActual = gestorPedidos.crearPedido();
            }

            String mensaje = gestorPedidos.agregarProducto(
                pedidoActual, codigo, cantidad
            );

            JOptionPane.showMessageDialog(vista, mensaje);

            if (mensaje.startsWith("Agregado:")) {
                actualizarTabla();
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista,
                "Ingrese una cantidad entera valida.");
        }
    }

    private void actualizarTabla() {
        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);

        double subtotal = 0;

        ArrayList<Detallepedido> detalles = pedidoActual.getdetalle();

        for (Detallepedido detalle : detalles) {
            double valor = detalle.getSubtotal();
            subtotal += valor;

            modelo.addRow(new Object[]{
                detalle.getProducto().getNombre(),
                detalle.getCantidad(),
                detalle.getPrecio_unit(),
                valor
            });
        }

        // La tasa de IVA debe definirse según los requisitos.
        double iva = subtotal * 0.15;
        double total = subtotal + iva;

        vista.getEtiquetaSubtotal().setText(
            String.format("Subtotal: $%.2f", subtotal)
        );

        vista.getEtiquetaIVA().setText(
            String.format("IVA (15%%): $%.2f", iva)
        );

        vista.getEtiquetaTotal().setText(
            String.format("Total: $%.2f", total)
        );
    }

    private void confirmarPedido() {
        if (pedidoActual == null || pedidoActual.getdetalle().isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                "Agregue al menos un producto.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
            vista,
            "¿Desea confirmar el pedido?",
            "Confirmar pedido",
            JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            String mensaje = gestorPedidos.confirmar(pedidoActual);

            JOptionPane.showMessageDialog(vista, mensaje);

            if (mensaje.startsWith("Pedido #")) {
                pedidoActual = null;
                vista.getModeloTabla().setRowCount(0);
                vista.getCampoCantidad().setText("");
                vista.getEtiquetaSubtotal().setText("Subtotal: $0.00");
                vista.getEtiquetaIVA().setText("IVA: Por definir");
                vista.getEtiquetaTotal().setText("Total: $0.00");
                cargarProductos();
            }
        }
    }
}
