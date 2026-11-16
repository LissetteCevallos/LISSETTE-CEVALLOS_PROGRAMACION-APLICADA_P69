package modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LectorRegistro {

   
    private static final Pattern P_CEDULA =
            Pattern.compile("\\b(?:[Cc][eé]dula(?:\\s+es)?|CI)\\s+(\\d+)");

    
    private static final Pattern P_CORREO =
            Pattern.compile("\\bcorreo\\s+(?!de\\s)(\\S+)");

   
    private static final Pattern P_MONTO = Pattern.compile(
            "\\$\\s*(\\d+(?:[.,]\\d+)?)|(\\d+(?:[.,]\\d+)?)\\s*(?:\\$|[Dd][oó]lares|USD)");

    public ArrayList<Aportador> leer(String ruta) throws IOException {
        File archivo = new File(ruta);
        if (!archivo.exists()) {
            throw new FileNotFoundException("No existe el archivo: " + archivo.getAbsolutePath());
        }

        // 1. Leer todo el archivo en un solo texto (los saltos de línea pasan a espacios)
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(archivo), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea).append(' ');
            }
        }

        // 2. Separar en registros
        String[] registros = sb.toString().split("(?<=\\.)\\s+(?=\\p{Lu})");

        // 3. Extraer los datos de cada registro
        ArrayList<Aportador> lista = new ArrayList<>();
        for (String registro : registros) {
            Matcher mc = P_CEDULA.matcher(registro);
            if (!mc.find()) continue; // títulos y notas: no son aportadores
            String cedula = mc.group(1);

            String correo = null;
            Matcher mco = P_CORREO.matcher(registro);
            if (mco.find()) {
                correo = mco.group(1).replaceAll("[,.;:]+$", ""); // quita la puntuación final
            }

            String monto = null;
            Matcher mm = P_MONTO.matcher(registro);
            if (mm.find()) {
                monto = (mm.group(1) != null) ? mm.group(1) : mm.group(2);
            }

            lista.add(new Aportador(cedula, correo, monto));
        }
        return lista;
    }
}
