
package Modelo;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ArchivoTienda {

    private final String rutaProductos = "productos.txt";
    private final String rutaVentas = "ventas.txt";

    // GUARDAR PRODUCTOS
    public void guardarProductos(ArrayList<Producto> productos)
            throws IOException {

        try (BufferedWriter escritor =
                new BufferedWriter(new FileWriter(rutaProductos))) {

            for (Producto producto : productos) {

                escritor.write(
                        producto.getCodigo() + ";" +
                        producto.getNombre() + ";" +
                        producto.getPrecio() + ";" +
                        producto.getStock()
                );

                escritor.newLine();
            }
        }
    }

    // CARGAR PRODUCTOS
    public ArrayList<Producto> cargarProductos()
            throws IOException {

        ArrayList<Producto> productos = new ArrayList<>();

        File archivo = new File(rutaProductos);

        if (!archivo.exists()) {
            return productos;
        }

        try (BufferedReader lector =
                new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";", -1);

                if (datos.length != 4) {
                    throw new IOException(
                            "Formato incorrecto en productos.txt");
                }

                try {

                    String codigo = datos[0];
                    String nombre = datos[1];

                    double precio =
                            Double.parseDouble(datos[2]);

                    int stock =
                            Integer.parseInt(datos[3]);

                    if (codigo.isBlank() || nombre.isBlank()
                            || codigo.contains(";")
                            || nombre.contains(";")
                            || !Double.isFinite(precio)
                            || precio < 0 || stock < 0) {

                        throw new IOException(
                                "Datos inválidos en productos.txt");
                    }

                    Producto producto =
                            new Producto(codigo, nombre, precio, stock);

                    productos.add(producto);

                } catch (NumberFormatException e) {

                    throw new IOException(
                            "Precio o stock inválido en productos.txt", e);
                }
            }
        }

        return productos;
    }

    // GUARDAR HISTORIAL DE VENTAS
    public void guardarVentas(ArrayList<Venta> ventas)
            throws IOException {

        try (BufferedWriter escritor =
                new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(rutaVentas),
                                StandardCharsets.UTF_8))) {

            for (Venta venta : ventas) {

                // DATOS GENERALES DE LA VENTA
                escritor.write(
                        "VENTA;" +
                        venta.getNumeroVenta() + ";" +
                        venta.getFecha()
                );

                escritor.newLine();

                // DETALLES DE LA VENTA
                for (DetalleVenta detalle : venta.getDetalles()) {

                    Producto producto = detalle.getProducto();

                    escritor.write(
                            "DETALLE;" +
                            producto.getCodigo() + ";" +
                            producto.getNombre() + ";" +
                            detalle.getCantidad() + ";" +
                            detalle.getPrecioUnitario()
                    );

                    escritor.newLine();
                }

                // FIN DE LA VENTA
                escritor.write("FIN");
                escritor.newLine();
            }
        }
    }

    // CARGAR HISTORIAL DE VENTAS
    public ArrayList<Venta> cargarVentas()
            throws IOException {

        ArrayList<Venta> ventas = new ArrayList<>();

        File archivo = new File(rutaVentas);

        if (!archivo.exists()) {
            return ventas;
        }

        try (BufferedReader lector =
                new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(archivo),
                                StandardCharsets.UTF_8))) {

            String linea;
            Venta ventaActual = null;

            while ((linea = lector.readLine()) != null) {

                String[] datos = linea.split(";", -1);

                try {

                    // INICIO DE UNA VENTA
                    if (datos[0].equals("VENTA")) {

                        if (datos.length != 3
                                || ventaActual != null) {

                            throw new IOException(
                                    "Formato de venta incorrecto.");
                        }

                        int numero =
                                Integer.parseInt(datos[1]);

                        LocalDateTime fecha =
                                LocalDateTime.parse(datos[2]);

                        ventaActual = new Venta(numero, fecha);

                    // DETALLE DE UNA VENTA
                    } else if (datos[0].equals("DETALLE")) {

                        if (datos.length != 5
                                || ventaActual == null) {

                            throw new IOException(
                                    "Formato de detalle incorrecto.");
                        }

                        String codigo = datos[1];
                        String nombre = datos[2];

                        int cantidad =
                                Integer.parseInt(datos[3]);

                        double precio =
                                Double.parseDouble(datos[4]);

                        if (codigo.isBlank()
                                || nombre.isBlank()
                                || codigo.contains(";")
                                || nombre.contains(";")
                                || cantidad <= 0
                                || !Double.isFinite(precio)
                                || precio < 0) {

                            throw new IOException(
                                    "Datos de detalle inválidos.");
                        }

                        // RECONSTRUIR PRODUCTO HISTORICO
                        Producto producto = new Producto(
                                codigo, nombre, precio, 0);

                        DetalleVenta detalle =
                                new DetalleVenta(producto, cantidad);

                        ventaActual.agregarDetalle(detalle);

                    // FINALIZAR VENTA
                    } else if (linea.equals("FIN")) {

                        if (ventaActual == null
                                || ventaActual.getDetalles().isEmpty()) {

                            throw new IOException(
                                    "Cierre de venta incorrecto.");
                        }

                        ventas.add(ventaActual);
                        ventaActual = null;

                    } else {

                        throw new IOException(
                                "Línea desconocida en ventas.txt");
                    }

                } catch (IllegalArgumentException e) {

                    throw new IOException(
                            "Datos incorrectos en ventas.txt", e);
                }
            }

            // VERIFICAR QUE NO HAYA VENTAS INCOMPLETAS
            if (ventaActual != null) {

                throw new IOException(
                        "Venta incompleta en ventas.txt");
            }
        }

        return ventas;
    }
}
