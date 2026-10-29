
package Controlador;

import Vista.PanelProductos;
import Logica.GestorProducto;
import Modelo.Producto;
import Utilidades.UtilidadGenerica;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorProductos {

    private PanelProductos vista;
    private GestorProducto gestor;

    public ControladorProductos(
            PanelProductos vista, GestorProducto gestor) {

        this.vista = vista;
        this.gestor = gestor;

        cargarProductos();

        vista.getBotonRegistrar().addActionListener(
            e -> registrarProducto()
        );

        vista.getBotonReponer().addActionListener(
            e -> reponerStock()
        );

        vista.getBotonEditar().addActionListener(
            e -> editarProducto()
        );
    }

    public void cargarProductos() {

        DefaultTableModel modelo = vista.getModeloTabla();
        modelo.setRowCount(0);

        for (Producto producto : gestor.getProductos()) {

            UtilidadGenerica.mostrarElementos(producto);
            UtilidadGenerica.mostrarElementos(
                producto.getCodigo()
            );

            modelo.addRow(new Object[]{
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getstock()
            });
        }
    }

    private void registrarProducto() {

        String codigo = JOptionPane.showInputDialog(
            vista, "Ingrese el codigo del producto:"
        );

        if (codigo == null) {
            return;
        }

        String nombre = JOptionPane.showInputDialog(
            vista, "Ingrese el nombre del producto:"
        );

        if (nombre == null) {
            return;
        }

        String precioTexto = JOptionPane.showInputDialog(
            vista, "Ingrese el precio:"
        );

        if (precioTexto == null) {
            return;
        }

        String stockTexto = JOptionPane.showInputDialog(
            vista, "Ingrese el stock inicial:"
        );

        if (stockTexto == null) {
            return;
        }

        try {

            double precio = Double.parseDouble(
                precioTexto.trim().replace(",", ".")
            );

            int stock = Integer.parseInt(
                stockTexto.trim()
            );

            boolean codigoExiste = false;

            for (Producto producto : gestor.getProductos()) {

                if (UtilidadGenerica.existeElemento(
                        codigo.trim(),
                        producto.getCodigo())) {

                    codigoExiste = true;
                    break;
                }
            }

            if (codigoExiste) {
                JOptionPane.showMessageDialog(
                    vista,
                    "Ya existe un producto con ese codigo."
                );
                return;
            }

            String mensaje = gestor.registrar(
                codigo.trim(),
                nombre.trim(),
                precio,
                stock
            );

            JOptionPane.showMessageDialog(vista, mensaje);

            if (mensaje.startsWith("Producto registrado")) {
                cargarProductos();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                vista,
                "El precio debe ser decimal y el stock debe ser entero.",
                "Dato incorrecto",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void reponerStock() {

        String codigo = JOptionPane.showInputDialog(
            vista, "Ingrese el codigo del producto:"
        );

        if (codigo == null) {
            return;
        }

        String cantidadTexto = JOptionPane.showInputDialog(
            vista, "Ingrese la cantidad que desea reponer:"
        );

        if (cantidadTexto == null) {
            return;
        }

        try {

            int cantidad = Integer.parseInt(
                cantidadTexto.trim()
            );

            String mensaje = gestor.reponerStock(
                codigo.trim(), cantidad
            );

            JOptionPane.showMessageDialog(vista, mensaje);

            if (mensaje.startsWith("Stock actualizado")) {
                cargarProductos();
            }

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                vista,
                "Ingrese una cantidad entera valida.",
                "Dato incorrecto",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void editarProducto() {

        int fila = vista.getTablaProductos().getSelectedRow();

        if (fila == -1) {
            JOptionPane.showMessageDialog(
                vista,
                "Seleccione un producto de la tabla."
            );
            return;
        }

        String codigo = vista.getTablaProductos()
            .getValueAt(fila, 0).toString();

        Producto producto = gestor.buscar(codigo);

        if (producto == null) {
            JOptionPane.showMessageDialog(
                vista,
                "No se encontro el producto."
            );
            return;
        }

        String nombre = JOptionPane.showInputDialog(
            vista,
            "Nuevo nombre:",
            producto.getNombre()
        );

        if (nombre == null) {
            return;
        }

        String precioTexto = JOptionPane.showInputDialog(
            vista,
            "Nuevo precio:",
            producto.getPrecio()
        );

        if (precioTexto == null) {
            return;
        }

        nombre = nombre.trim();

        if (nombre.isEmpty()
                || nombre.contains(";")
                || nombre.contains(":")
                || nombre.contains("|")) {

            JOptionPane.showMessageDialog(
                vista,
                "Ingrese un nombre valido."
            );
            return;
        }

        try {

            double precio = Double.parseDouble(
                precioTexto.trim().replace(",", ".")
            );

            if (precio <= 0) {
                JOptionPane.showMessageDialog(
                    vista,
                    "El precio debe ser mayor que cero."
                );
                return;
            }

            producto.setNombre(nombre);
            producto.setPrecio(precio);

            gestor.guardar();
            cargarProductos();

            JOptionPane.showMessageDialog(
                vista,
                "Producto actualizado correctamente."
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                vista,
                "Ingrese un precio numerico valido."
            );
        }
    }
}

