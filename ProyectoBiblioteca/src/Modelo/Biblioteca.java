package Modelo;

import java.util.ArrayList;

public class Biblioteca {

    // Lista donde se almacenan los libros
    private ArrayList<Libro> libros;

    // Constructor
    public Biblioteca() {
        libros = new ArrayList<>();
    }
    
    public void cargarLibros(ArrayList<Libro> librosCargados) {
        libros.clear();

        for (Libro libro : librosCargados) {
            agregarLibro(libro);
        }
    }

    // Registrar un libro
    public boolean agregarLibro(Libro libro) {
        if (libro == null || buscarLibro(libro.getCodigo()) != null) {
            return false;
        }

        libros.add(libro);
        return true;
    }

    // Obtener todos los libros
    public ArrayList<Libro> listarLibros() {
        return new ArrayList<>(libros);
    }

    // Buscar un libro por su codigo
    public Libro buscarLibro(String codigo) {
        for (Libro libro : libros) {
            if (libro.getCodigo().equals(codigo)) {
                return libro;
            }
        }
        return null;
    }

    // Eliminar un libro
    public boolean eliminarLibro(String codigo) {
        Libro libro = buscarLibro(codigo);

        if (libro != null) {
            libros.remove(libro);
            return true;
        }

        return false;
    }

    // Prestar un libro
    public boolean prestarLibro(String codigo) {
        Libro libro = buscarLibro(codigo);

        if (libro != null && libro.isDisponible()) {
            libro.setDisponible(false);
            return true;
        }

        return false;
    }

    // Devolver un libro
    public boolean devolverLibro(String codigo) {
        Libro libro = buscarLibro(codigo);

        if (libro != null && !libro.isDisponible()) {
            libro.setDisponible(true);
            return true;
        }

        return false;
    }

    // Metodo con genericos y varargs
    @SafeVarargs
    public static <T> ArrayList<T> crearLista(T... elementos) {
        ArrayList<T> lista = new ArrayList<>();

        for (T elemento : elementos) {
            lista.add(elemento);
        }

        return lista;
    }

    // Agregar varios libros usando varargs
    public int agregarVarios(Libro... nuevosLibros) {
        int cantidad = 0;

        for (Libro libro : nuevosLibros) {
            if (agregarLibro(libro)) {
                cantidad++;
            }
        }

        return cantidad;
    }
}
