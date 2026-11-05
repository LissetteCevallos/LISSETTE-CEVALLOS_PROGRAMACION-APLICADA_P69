package Controlador;

import Modelo.ArchivoLibros;
import Modelo.Biblioteca;
import Modelo.Libro;
import Vista.VistaBiblioteca;
import Vista.VistaListaLibros;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class BibliotecaControlador {

    private Biblioteca biblioteca;
    private ArchivoLibros archivo;
    private VistaBiblioteca vista;

    public BibliotecaControlador(VistaBiblioteca vista) {

        this.vista = vista;
        this.biblioteca = new Biblioteca();
        this.archivo = new ArchivoLibros();

        // Recuperar los libros del archivo TXT
        try {
            biblioteca.cargarLibros(archivo.cargar());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista,
                    "Error al cargar: " + e.getMessage());
        }

        // Conectar los botones
        vista.getBtnAgregar().addActionListener(e -> agregar());
        vista.getBtnListar().addActionListener(e -> listar());
        vista.getBtnBuscar().addActionListener(e -> buscar());
        vista.getBtnEliminar().addActionListener(e -> eliminar());
        vista.getBtnPrestar().addActionListener(e -> prestar());
        vista.getBtnDevolver().addActionListener(e -> devolver());
    }

    // GUARDAR CAMBIOS EN TXT
    private void guardar() {
        try {
            archivo.guardar(biblioteca.listarLibros());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(vista,
                    "Error al guardar: " + e.getMessage());
        }
    }

    // AGREGAR LIBRO
    private void agregar() {

        String codigo = vista.getTxtCodigo().getText().trim();
        String titulo = vista.getTxtTitulo().getText().trim();
        String autor = vista.getTxtAutor().getText().trim();

        if (codigo.isEmpty() || titulo.isEmpty() || autor.isEmpty()) {
            JOptionPane.showMessageDialog(vista,
                    "Complete todos los campos");
            return;
        }

        if (codigo.contains(";") || titulo.contains(";")
                || autor.contains(";")) {
            JOptionPane.showMessageDialog(vista,
                    "Los campos no pueden contener punto y coma");
            return;
        }

        Libro libro = new Libro(codigo, titulo, autor);

        if (biblioteca.agregarLibro(libro)) {
            guardar();
            JOptionPane.showMessageDialog(vista,
                    "Libro agregado correctamente");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Ya existe un libro con ese codigo");
        }
    }

    // LISTAR LIBROS
    private void listar() {

        VistaListaLibros ventana = new VistaListaLibros();

        DefaultTableModel modelo = new DefaultTableModel(
                new String[]{"Codigo", "Titulo", "Autor", "Estado"}, 0);

        for (Libro libro : biblioteca.listarLibros()) {
            modelo.addRow(new Object[]{
                    libro.getCodigo(),
                    libro.getTitulo(),
                    libro.getAutor(),
                    libro.isDisponible() ? "Disponible" : "Prestado"
            });
        }

        ventana.getTable().setModel(modelo);
        ventana.setLocationRelativeTo(vista);
        ventana.setVisible(true);
        
        limpiar();
    }

    // BUSCAR LIBRO

private void buscar() {

    String codigo = vista.getTxtCodigo().getText().trim();
    Libro libro = biblioteca.buscarLibro(codigo);

    if (libro != null) {

        JOptionPane.showMessageDialog(vista,
                "Código: " + libro.getCodigo() +
                "\nTítulo: " + libro.getTitulo() +
                "\nAutor: " + libro.getAutor() +
                "\nEstado: " +
                (libro.isDisponible() ? "Disponible" : "Prestado"));

        limpiar();

    } else {
        JOptionPane.showMessageDialog(vista,
                "Libro no encontrado");
    }
}


    // ELIMINAR LIBRO
    private void eliminar() {

        String codigo = vista.getTxtCodigo().getText().trim();

        if (biblioteca.eliminarLibro(codigo)) {
            guardar();
            JOptionPane.showMessageDialog(vista,
                    "Libro eliminado");
            limpiar();
        } else {
            JOptionPane.showMessageDialog(vista,
                    "Libro no encontrado");
        }
    }

    // PRESTAR LIBRO

private void prestar() {

    String codigo = vista.getTxtCodigo().getText().trim();

    if (biblioteca.prestarLibro(codigo)) {
        guardar();

        JOptionPane.showMessageDialog(vista,
                "Libro prestado correctamente");

        limpiar();

    } else {
        JOptionPane.showMessageDialog(vista,
                "El libro no existe o ya esta prestado");
    }
}


    // DEVOLVER LIBRO

private void devolver() {

    String codigo = vista.getTxtCodigo().getText().trim();

    if (biblioteca.devolverLibro(codigo)) {
        guardar();

        JOptionPane.showMessageDialog(vista,
                "Libro devuelto correctamente");

        limpiar();

    } else {
        JOptionPane.showMessageDialog(vista,
                "El libro no existe o ya esta disponible");
    }
}


    // LIMPIAR CAMPOS
	private void limpiar() {
	    vista.getTxtCodigo().setText("");
	    vista.getTxtTitulo().setText("");
	    vista.getTxtAutor().setText("");
	    vista.getTxtCodigo().requestFocusInWindow();
	}
}
