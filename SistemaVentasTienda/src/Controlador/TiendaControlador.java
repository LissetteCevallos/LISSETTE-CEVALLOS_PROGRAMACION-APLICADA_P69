
package Controlador;

import Modelo.Tienda;
import Modelo.Producto;
import Modelo.ArchivoTienda;
import Modelo.DetalleVenta;
import Modelo.Venta;

import Vista.VistaTienda;
import Vista.VistaProducto;
import Vista.VistaVentas;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.io.IOException;
import java.util.ArrayList;

public class TiendaControlador {

    private Tienda tienda;
    private ArchivoTienda archivo;

    private VistaTienda vistaPrincipal;
    private VistaProducto vistaProducto;
    private VistaVentas vistaVentas;

    private ArrayList<DetalleVenta> detallesVenta = new ArrayList<>();
    private ArrayList<Venta> historialVentas = new ArrayList<>();

    // CONSTRUCTOR
    public TiendaControlador(VistaTienda vistaPrincipal) {

        this.vistaPrincipal = vistaPrincipal;

        tienda = new Tienda();
        archivo = new ArchivoTienda();

        // CARGAR PRODUCTOS GUARDADOS
        try {
            tienda.cargarProductos(archivo.cargarProductos());

        } catch (IOException e) {
            JOptionPane.showMessageDialog(vistaPrincipal,
                    "Error al cargar productos: " + e.getMessage());
        }

        // CARGAR HISTORIAL DE VENTAS GUARDADO
        try {
            historialVentas = archivo.cargarVentas();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(vistaPrincipal,
                    "Error al cargar el historial de ventas: "
                    + e.getMessage());
        }

        // CONECTAR BOTONES PRINCIPALES
        vistaPrincipal.getBtnProductos().addActionListener(
                e -> abrirProductos());

        vistaPrincipal.getBtnVentas().addActionListener(
                e -> abrirVentas());

        vistaPrincipal.getBtnHistorial().addActionListener(
                e -> mostrarHistorial());

        // CONSULTA RAPIDA AL PRESIONAR ENTER
        vistaPrincipal.getTxtCodigo().addActionListener(
                e -> consultaRapida());
    }

    // ABRIR VENTANA DE PRODUCTOS
    private void abrirProductos() {

        vistaProducto = new VistaProducto();
        vistaProducto.setLocationRelativeTo(vistaPrincipal);

        vistaProducto.getBtnAgregar().addActionListener(
                e -> agregarProducto());

        vistaProducto.getBtnBuscar().addActionListener(
                e -> buscarProducto());

        vistaProducto.getBtnEliminar().addActionListener(
                e -> eliminarProducto());

        vistaProducto.getBtnActualizarStock().addActionListener(
                e -> actualizarStock());

        vistaProducto.getBtnListar().addActionListener(
                e -> listarProductos());

        vistaProducto.setVisible(true);
    }

    // ABRIR VENTANA DE VENTAS
    private void abrirVentas() {

        vistaVentas = new VistaVentas();
        vistaVentas.setLocationRelativeTo(vistaPrincipal);

        detallesVenta = new ArrayList<>();

        vistaVentas.getBtnBuscarProducto().addActionListener(
                e -> buscarProductoVenta());

        vistaVentas.getBtnAgregarVenta().addActionListener(
                e -> agregarVenta());

        vistaVentas.getBtnConfirmarVenta().addActionListener(
                e -> confirmarVenta());

        vistaVentas.setVisible(true);
    }

    // CONSULTA RAPIDA DE PRODUCTOS
    private void consultaRapida() {

        String codigo = vistaPrincipal.getTxtCodigo()
                .getText().trim();

        Producto producto = tienda.buscarProducto(codigo);

        if (producto != null) {

            vistaPrincipal.getTxtNombre().setText(
                    producto.getNombre());

            vistaPrincipal.getTxtPrecio().setText(
                    String.valueOf(producto.getPrecio()));

            vistaPrincipal.getTxtStock().setText(
                    String.valueOf(producto.getStock()));

        } else {

            vistaPrincipal.getTxtNombre().setText("");
            vistaPrincipal.getTxtPrecio().setText("");
            vistaPrincipal.getTxtStock().setText("");

            JOptionPane.showMessageDialog(vistaPrincipal,
                    "Producto no encontrado");
        }
    }


    // MOSTRAR HISTORIAL DE VENTAS
    private void mostrarHistorial() {

        // VERIFICAR SI EXISTEN VENTAS
        if (historialVentas.isEmpty()) {

            JOptionPane.showMessageDialog(vistaPrincipal,
                    "Todavía no existen ventas registradas.");
            return;
        }

        // CONSTRUIR EL HISTORIAL
        StringBuilder historial = new StringBuilder();

        historial.append("HISTORIAL DE VENTAS\n");
        historial.append("============================\n\n");

        double totalGeneral = 0;

        for (Venta venta : historialVentas) {

            historial.append("VENTA N.º ")
                    .append(venta.getNumeroVenta())
                    .append("\n");

            historial.append("Fecha: ")
                    .append(venta.getFecha()
                            .format(java.time.format.DateTimeFormatter
                                    .ofPattern("dd/MM/yyyy HH:mm")))
                    .append("\n");

            historial.append("----------------------------\n");

            for (DetalleVenta detalle : venta.getDetalles()) {

                historial.append("Producto: ")
                        .append(detalle.getProducto().getNombre())
                        .append("\n");

                historial.append("Cantidad: ")
                        .append(detalle.getCantidad())
                        .append("\n");

                historial.append(String.format(
                        "Precio unitario: $%.2f\n",
                        detalle.getPrecioUnitario()));

                historial.append(String.format(
                        "Subtotal: $%.2f\n\n",
                        detalle.calcularSubtotal()));
            }

            historial.append(String.format(
                    "TOTAL DE LA VENTA: $%.2f\n",
                    venta.calcularTotal()));

            historial.append("============================\n\n");

            totalGeneral += venta.calcularTotal();
        }

        historial.append(String.format(
                "TOTAL GENERAL DE VENTAS: $%.2f",
                totalGeneral));

        // CREAR AREA DE TEXTO
        javax.swing.JTextArea areaTexto =
                new javax.swing.JTextArea(historial.toString());

        areaTexto.setEditable(false);
        areaTexto.setRows(18);
        areaTexto.setColumns(45);
        areaTexto.setCaretPosition(0);

        // AGREGAR BARRA DE DESPLAZAMIENTO
        javax.swing.JScrollPane scroll =
                new javax.swing.JScrollPane(areaTexto);

        // MOSTRAR HISTORIAL
        JOptionPane.showMessageDialog(
                vistaPrincipal,
                scroll,
                "Historial de Ventas",
                JOptionPane.INFORMATION_MESSAGE);
    }


    // AGREGAR PRODUCTO
    private void agregarProducto() {

        try {

            String codigo = vistaProducto.getTxtCodigo()
                    .getText().trim();

            String nombre = vistaProducto.getTxtNombre()
                    .getText().trim();

            // VALIDAR CAMPOS VACIOS
            if (codigo.isEmpty() || nombre.isEmpty()
                    || vistaProducto.getTxtPrecio()
                            .getText().trim().isEmpty()
                    || vistaProducto.getTxtStock()
                            .getText().trim().isEmpty()) {

                JOptionPane.showMessageDialog(vistaProducto,
                        "Complete todos los campos.");
                return;
            }

            // VALIDAR CARACTERES
            if (codigo.contains(";") || nombre.contains(";")) {

                JOptionPane.showMessageDialog(vistaProducto,
                        "El código y el nombre no pueden "
                        + "contener punto y coma.");
                return;
            }

            // CONVERTIR DATOS
            double precio = Double.parseDouble(
                    vistaProducto.getTxtPrecio()
                            .getText().trim());

            int stock = Integer.parseInt(
                    vistaProducto.getTxtStock()
                            .getText().trim());

            // VALIDAR PRECIO Y STOCK
            if (!Double.isFinite(precio)
                    || precio < 0 || stock < 0) {

                JOptionPane.showMessageDialog(vistaProducto,
                        "El precio y el stock deben ser "
                        + "valores válidos.");
                return;
            }

            // CREAR PRODUCTO
            Producto producto = new Producto(
                    codigo, nombre, precio, stock);

            // REGISTRAR PRODUCTO
            if (tienda.agregarProducto(producto)) {

                try {
                    archivo.guardarProductos(
                            tienda.listarProductos());

                } catch (IOException e) {

                    JOptionPane.showMessageDialog(vistaProducto,
                            "Producto agregado en memoria, "
                            + "pero no se pudo guardar: "
                            + e.getMessage());
                    return;
                }

                JOptionPane.showMessageDialog(vistaProducto,
                        "Producto registrado correctamente.");

                // LIMPIAR CAMPOS
                vistaProducto.getTxtCodigo().setText("");
                vistaProducto.getTxtNombre().setText("");
                vistaProducto.getTxtPrecio().setText("");
                vistaProducto.getTxtStock().setText("");

            } else {

                JOptionPane.showMessageDialog(vistaProducto,
                        "No se pudo registrar el producto. "
                        + "Compruebe si el código ya existe.");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vistaProducto,
                    "Ingrese un precio y un stock "
                    + "numéricos válidos.");
        }
    }

    // BUSCAR PRODUCTO
    private void buscarProducto() {

        String codigo = vistaProducto.getTxtCodigo()
                .getText().trim();

        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(vistaProducto,
                    "Ingrese el código del producto.");
            return;
        }

        Producto producto = tienda.buscarProducto(codigo);

        if (producto != null) {

            vistaProducto.getTxtNombre().setText(
                    producto.getNombre());

            vistaProducto.getTxtPrecio().setText(
                    String.valueOf(producto.getPrecio()));

            vistaProducto.getTxtStock().setText(
                    String.valueOf(producto.getStock()));

            JOptionPane.showMessageDialog(vistaProducto,
                    "Producto encontrado correctamente.");

        } else {

            vistaProducto.getTxtNombre().setText("");
            vistaProducto.getTxtPrecio().setText("");
            vistaProducto.getTxtStock().setText("");

            JOptionPane.showMessageDialog(vistaProducto,
                    "No existe un producto con ese código.");
        }
    }

    // ELIMINAR PRODUCTO
    private void eliminarProducto() {

        String codigo = vistaProducto.getTxtCodigo()
                .getText().trim();

        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(vistaProducto,
                    "Ingrese el código del producto "
                    + "que desea eliminar.");
            return;
        }

        Producto producto = tienda.buscarProducto(codigo);

        if (producto == null) {

            JOptionPane.showMessageDialog(vistaProducto,
                    "No existe un producto con ese código.");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                vistaProducto,
                "¿Está seguro de eliminar el producto "
                        + producto.getNombre() + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        if (tienda.eliminarProducto(codigo)) {

            try {
                archivo.guardarProductos(
                        tienda.listarProductos());

            } catch (IOException e) {

                JOptionPane.showMessageDialog(vistaProducto,
                        "Producto eliminado en memoria, "
                        + "pero no se pudo guardar el cambio: "
                        + e.getMessage());
                return;
            }

            vistaProducto.getTxtCodigo().setText("");
            vistaProducto.getTxtNombre().setText("");
            vistaProducto.getTxtPrecio().setText("");
            vistaProducto.getTxtStock().setText("");

            JOptionPane.showMessageDialog(vistaProducto,
                    "Producto eliminado correctamente.");

        } else {

            JOptionPane.showMessageDialog(vistaProducto,
                    "No se pudo eliminar el producto.");
        }
    }

    // ACTUALIZAR STOCK
    private void actualizarStock() {

        String codigo = vistaProducto.getTxtCodigo()
                .getText().trim();

        String textoStock = vistaProducto.getTxtStock()
                .getText().trim();

        if (codigo.isEmpty() || textoStock.isEmpty()) {

            JOptionPane.showMessageDialog(vistaProducto,
                    "Ingrese el código y el nuevo stock.");
            return;
        }

        Producto producto = tienda.buscarProducto(codigo);

        if (producto == null) {

            JOptionPane.showMessageDialog(vistaProducto,
                    "No existe un producto con ese código.");
            return;
        }

        try {

            int nuevoStock = Integer.parseInt(textoStock);

            if (nuevoStock < 0) {

                JOptionPane.showMessageDialog(vistaProducto,
                        "El stock no puede ser negativo.");
                return;
            }

            if (tienda.actualizarStock(codigo, nuevoStock)) {

                try {
                    archivo.guardarProductos(
                            tienda.listarProductos());

                } catch (IOException e) {

                    JOptionPane.showMessageDialog(vistaProducto,
                            "Stock actualizado en memoria, "
                            + "pero no se pudo guardar: "
                            + e.getMessage());
                    return;
                }

                vistaProducto.getTxtStock().setText(
                        String.valueOf(producto.getStock()));

                JOptionPane.showMessageDialog(vistaProducto,
                        "Stock actualizado correctamente.");

            } else {

                JOptionPane.showMessageDialog(vistaProducto,
                        "No se pudo actualizar el stock.");
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vistaProducto,
                    "El stock debe ser un número entero válido.");
        }
    }

    // LISTAR PRODUCTOS
    private void listarProductos() {

        DefaultTableModel modelo =
                (DefaultTableModel) vistaProducto
                        .getTablaProductos().getModel();

        modelo.setRowCount(0);

        for (Producto producto : tienda.listarProductos()) {

            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock()
            };

            modelo.addRow(fila);
        }
    }

    // BUSCAR PRODUCTO PARA LA VENTA
    private void buscarProductoVenta() {

        String codigo = vistaVentas.getTxtCodigoProducto()
                .getText().trim();

        if (codigo.isEmpty()) {

            JOptionPane.showMessageDialog(vistaVentas,
                    "Ingrese el código del producto.");
            return;
        }

        Producto producto = tienda.buscarProducto(codigo);

        if (producto == null) {

            JOptionPane.showMessageDialog(vistaVentas,
                    "No existe un producto con ese código.");
            return;
        }

        JOptionPane.showMessageDialog(vistaVentas,
                "PRODUCTO ENCONTRADO\n\n"
                + "Código: " + producto.getCodigo()
                + "\nNombre: " + producto.getNombre()
                + "\nPrecio: $" + producto.getPrecio()
                + "\nStock disponible: " + producto.getStock());
    }

    // AGREGAR PRODUCTO A LA VENTA
    private void agregarVenta() {

        String codigo = vistaVentas.getTxtCodigoProducto()
                .getText().trim();

        String textoCantidad = vistaVentas.getTxtCantidad()
                .getText().trim();

        if (codigo.isEmpty() || textoCantidad.isEmpty()) {

            JOptionPane.showMessageDialog(vistaVentas,
                    "Ingrese el código y la cantidad.");
            return;
        }

        Producto producto = tienda.buscarProducto(codigo);

        if (producto == null) {

            JOptionPane.showMessageDialog(vistaVentas,
                    "Producto no encontrado.");
            return;
        }

        try {

            int cantidad = Integer.parseInt(textoCantidad);

            if (cantidad <= 0) {

                JOptionPane.showMessageDialog(vistaVentas,
                        "La cantidad debe ser mayor que cero.");
                return;
            }

            // CALCULAR UNIDADES YA AGREGADAS
            int cantidadAgregada = 0;

            for (DetalleVenta detalle : detallesVenta) {

                if (detalle.getProducto().getCodigo()
                        .equals(codigo)) {

                    cantidadAgregada += detalle.getCantidad();
                }
            }

            // VALIDAR STOCK DISPONIBLE
            if (cantidad > producto.getStock()
                    - cantidadAgregada) {

                JOptionPane.showMessageDialog(vistaVentas,
                        "Stock insuficiente.\n"
                        + "Disponible para agregar: "
                        + (producto.getStock()
                        - cantidadAgregada));
                return;
            }

            DetalleVenta detalle =
                    new DetalleVenta(producto, cantidad);

            detallesVenta.add(detalle);

            actualizarTablaVentas();

            vistaVentas.getTxtCodigoProducto().setText("");
            vistaVentas.getTxtCantidad().setText("");

            JOptionPane.showMessageDialog(vistaVentas,
                    "Producto agregado a la venta.");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(vistaVentas,
                    "La cantidad debe ser un número "
                    + "entero válido.");
        }
    }

    // ACTUALIZAR TABLA DE VENTAS
    private void actualizarTablaVentas() {

        DefaultTableModel modelo =
                (DefaultTableModel) vistaVentas
                        .getTablaVentas().getModel();

        modelo.setRowCount(0);

        double total = 0;

        for (DetalleVenta detalle : detallesVenta) {

            Producto producto = detalle.getProducto();

            Object[] fila = {
                    producto.getCodigo(),
                    producto.getNombre(),
                    detalle.getCantidad(),
                    detalle.getPrecioUnitario(),
                    detalle.calcularSubtotal()
            };

            modelo.addRow(fila);

            total += detalle.calcularSubtotal();
        }

        vistaVentas.getLblTotal().setText(
                String.format("TOTAL: $ %.2f", total));
    }

    // CONFIRMAR VENTA
    private void confirmarVenta() {

        if (detallesVenta.isEmpty()) {

            JOptionPane.showMessageDialog(vistaVentas,
                    "Debe agregar al menos un producto.");
            return;
        }

        // COMPROBAR STOCK
        for (DetalleVenta detalle : detallesVenta) {

            Producto producto = tienda.buscarProducto(
                    detalle.getProducto().getCodigo());

            if (producto == null) {

                JOptionPane.showMessageDialog(vistaVentas,
                        "Uno de los productos ya no existe.");
                return;
            }

            int cantidadTotal = 0;

            for (DetalleVenta otro : detallesVenta) {

                if (otro.getProducto().getCodigo()
                        .equals(producto.getCodigo())) {

                    cantidadTotal += otro.getCantidad();
                }
            }

            if (cantidadTotal > producto.getStock()) {

                JOptionPane.showMessageDialog(vistaVentas,
                        "Stock insuficiente para: "
                        + producto.getNombre());
                return;
            }
        }

        // CALCULAR NUMERO DE VENTA
        int numeroVenta = 1;

        for (Venta ventaAnterior : historialVentas) {

            if (ventaAnterior.getNumeroVenta() >= numeroVenta) {

                numeroVenta =
                        ventaAnterior.getNumeroVenta() + 1;
            }
        }

        // CREAR VENTA
        Venta venta = new Venta(numeroVenta);

        for (DetalleVenta detalle : detallesVenta) {
            venta.agregarDetalle(detalle);
        }

        // SOLICITAR CONFIRMACION
        int respuesta = JOptionPane.showConfirmDialog(
                vistaVentas,
                String.format(
                        "Total de la venta: $%.2f\n"
                        + "¿Desea confirmar la venta?",
                        venta.calcularTotal()),
                "Confirmar venta",
                JOptionPane.YES_NO_OPTION);

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        // DESCONTAR STOCK
        for (DetalleVenta detalle : detallesVenta) {

            Producto producto = tienda.buscarProducto(
                    detalle.getProducto().getCodigo());

            int nuevoStock =
                    producto.getStock() - detalle.getCantidad();

            tienda.actualizarStock(
                    producto.getCodigo(), nuevoStock);
        }

        // GUARDAR INVENTARIO ACTUALIZADO
        try {

            archivo.guardarProductos(tienda.listarProductos());

        } catch (IOException e) {

            // RESTAURAR STOCK EN MEMORIA
            for (DetalleVenta detalle : detallesVenta) {

                Producto producto = tienda.buscarProducto(
                        detalle.getProducto().getCodigo());

                if (producto != null) {

                    tienda.actualizarStock(
                            producto.getCodigo(),
                            producto.getStock()
                            + detalle.getCantidad());
                }
            }

            JOptionPane.showMessageDialog(vistaVentas,
                    "No se pudo guardar el inventario: "
                    + e.getMessage());
            return;
        }

        // REGISTRAR VENTA EN HISTORIAL
        historialVentas.add(venta);

        // GUARDAR HISTORIAL EN ARCHIVO
        try {

            archivo.guardarVentas(historialVentas);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(vistaVentas,
                    "El inventario fue actualizado, "
                    + "pero no se pudo guardar el historial.\n"
                    + "Revise el archivo ventas.txt.\n"
                    + e.getMessage());

            return;
        }

        // MOSTRAR CONFIRMACION
        JOptionPane.showMessageDialog(vistaVentas,
                "Venta N.º " + venta.getNumeroVenta()
                + " registrada correctamente.\n"
                + String.format("Total: $%.2f",
                        venta.calcularTotal()));

        // LIMPIAR VENTA ACTUAL
        detallesVenta.clear();

        vistaVentas.getTxtCodigoProducto().setText("");
        vistaVentas.getTxtCantidad().setText("");

        actualizarTablaVentas();
    }
}
