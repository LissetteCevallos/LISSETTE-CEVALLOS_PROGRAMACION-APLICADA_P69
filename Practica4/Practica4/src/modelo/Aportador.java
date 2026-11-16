package modelo;

import java.util.regex.Pattern;

public class Aportador {
    private static final Pattern CEDULA = Pattern.compile("^\\d{10}$");
    private static final Pattern CORREO = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$");

    private String cedula;
    private String correo;
    private String montoTexto;

    public Aportador(String cedula, String correo, String montoTexto) {
        this.cedula = cedula;
        this.correo = correo;
        this.montoTexto = montoTexto;
    }

    public String getCedula() { return cedula; }
    public String getCorreo() { return correo; }
    public String getMontoTexto() { return montoTexto; }

    // Convierte el texto del monto a número; si no se puede, devuelve 0
    public double getMonto() {
        if (montoTexto == null) return 0;
        try {
            return Double.parseDouble(montoTexto.replace(',', '.'));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public boolean isCedulaValida() {
        return cedula != null && CEDULA.matcher(cedula).matches();
    }

    public boolean isCorreoValido() {
        return correo != null && CORREO.matcher(correo).matches();
    }

    public boolean isMontoValido() {
        return getMonto() > 0;
    }

    public boolean isValido() {
        return isCedulaValida() && isCorreoValido() && isMontoValido();
    }

    // Devuelve los campos incorrectos, por ejemplo: "cédula, correo"
    public String getErrores() {
        StringBuilder sb = new StringBuilder();
        if (!isCedulaValida()) sb.append("cédula ");
        if (!isCorreoValido()) sb.append("correo ");
        if (!isMontoValido()) sb.append("monto ");
        return sb.toString().trim().replace(" ", ", ");
    }
}
