package Modelo;

import java.io.*;
import java.util.ArrayList;

public class ArchivoLibros {

    private final String ruta = "libros.txt";

    // GUARDAR TODOS LOS LIBROS
    public void guardar(ArrayList<Libro> libros) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(
                new FileWriter(ruta))) {

            for (Libro libro : libros) {

                bw.write(libro.getCodigo() + ";" +
                         libro.getTitulo() + ";" +
                         libro.getAutor() + ";" +
                         libro.isDisponible());

                bw.newLine();
            }
        }
    }

    // CARGAR LIBROS DESDE EL ARCHIVO
    public ArrayList<Libro> cargar() throws IOException {

        ArrayList<Libro> libros = new ArrayList<>();

        File archivo = new File(ruta);

        if (!archivo.exists()) {
            return libros;
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(archivo))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(";", -1);

                if (datos.length == 4) {

                    Libro libro = new Libro(
                            datos[0], datos[1], datos[2]);

                    libro.setDisponible(
                            Boolean.parseBoolean(datos[3]));

                    libros.add(libro);
                }
            }
        }

        return libros;
    }
}
