
package Modelo;

public class Cliente {

    // ATRIBUTOS
    private String cedula;
    private String nombre;
    private String telefono;
    private String correo;

    // CONSTRUCTOR
    public Cliente(String cedula, String nombre,
                   String telefono, String correo) {

        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "La cédula no puede estar vacía.");
        }

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío.");
        }

        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El teléfono no puede estar vacío.");
        }

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El correo no puede estar vacío.");
        }

        this.cedula = cedula.trim();
        this.nombre = nombre.trim();
        this.telefono = telefono.trim();
        this.correo = correo.trim();
    }

    // GETTERS
    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    // SETTERS
    public void setNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El nombre no puede estar vacío.");
        }

        this.nombre = nombre.trim();
    }

    public void setTelefono(String telefono) {

        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El teléfono no puede estar vacío.");
        }

        this.telefono = telefono.trim();
    }

    public void setCorreo(String correo) {

        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El correo no puede estar vacío.");
        }

        this.correo = correo.trim();
    }

    // MOSTRAR INFORMACION DEL CLIENTE
    @Override
    public String toString() {

        return "Cédula: " + cedula
                + " | Nombre: " + nombre
                + " | Teléfono: " + telefono
                + " | Correo: " + correo;
    }
}
