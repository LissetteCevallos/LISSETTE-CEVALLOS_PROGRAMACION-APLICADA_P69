package practica5hilos;

import java.util.List;
import java.util.Random;

public class Mecanico implements Runnable {
    String nombre;
    List<Vehiculo> autos;

    public Mecanico(String nombre, List<Vehiculo> autos) {
        this.nombre = nombre;
        this.autos = autos;
    }

    @Override
    public void run() {
        
        try {
            Thread.sleep(new Random().nextInt(3001));
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }

        
        int fLambda = 0, fO2 = 0, fN2O = 0, fCO2 = 0;

        String linea = "+------------+------------+----------+----------+----------+----------+";
        StringBuilder sb = new StringBuilder();
        sb.append("\nMecanico asignado: " + nombre + "\n");
        sb.append(linea + "\n");
        sb.append(String.format("| %-10s | %-10s | %-8s | %-8s | %-8s | %-8s |\n",
                "Marca", "Placa", "Lambda", "O2", "N2O", "CO2"));
        sb.append(linea + "\n");

        for (Vehiculo v : autos) {
            
            String mL = "", mO = "", mN = "", mC = "";
            if (v.lambda > 1) { fLambda++; mL = "*"; }
            if (v.o2 >= 2)    { fO2++;     mO = "*"; }
            if (v.n2o >= 40)  { fN2O++;    mN = "*"; }
            if (v.co2 >= 15)  { fCO2++;    mC = "*"; }

            sb.append(String.format("| %-10s | %-10s | %-8s | %-8s | %-8s | %-8s |\n",
                    v.marca, v.placa,
                    String.format("%.2f", v.lambda) + mL,
                    String.format("%.2f", v.o2) + mO,
                    String.format("%.2f", v.n2o) + mN,
                    String.format("%.2f", v.co2) + mC));
        }

        
        double pLambda = fLambda * 100.0 / autos.size();
        double pO2 = fO2 * 100.0 / autos.size();
        double pN2O = fN2O * 100.0 / autos.size();
        double pCO2 = fCO2 * 100.0 / autos.size();

       
        int totalFallas = fLambda + fO2 + fN2O + fCO2;
        double pGeneral = totalFallas * 100.0 / (autos.size() * 4);

       
        boolean okL = pLambda <= 70;
        boolean okO = pO2 <= 30;
        boolean okN = pN2O <= 80;
        boolean okC = pCO2 <= 50;

        
        sb.append(linea + "\n");
        sb.append(String.format("| %-10s | %-10s | %-8s | %-8s | %-8s | %-8s |\n",
                "% ERROR", "",
                String.format("%.0f%%", pLambda), String.format("%.0f%%", pO2),
                String.format("%.0f%%", pN2O), String.format("%.0f%%", pCO2)));
        sb.append(String.format("| %-10s | %-10s | %-8s | %-8s | %-8s | %-8s |\n",
                "TOLERANCIA", "", "70%", "30%", "80%", "50%"));
        sb.append(String.format("| %-10s | %-10s | %-8s | %-8s | %-8s | %-8s |\n",
                "RESULTADO", "",
                okL ? "PASA" : "FALLA", okO ? "PASA" : "FALLA",
                okN ? "PASA" : "FALLA", okC ? "PASA" : "FALLA"));
        sb.append(linea + "\n");
        sb.append("(* = valor en falla)\n");

     
        sb.append("Lambda: " + (okL ? "Pasa en Primera Revision" : "Segunda Revision Asignada") + "\n");
        sb.append("O2:     " + (okO ? "Pasa en Primera Revision" : "Segunda Revision Asignada") + "\n");
        sb.append("N2O:    " + (okN ? "Pasa en Primera Revision" : "Segunda Revision Asignada") + "\n");
        sb.append("CO2:    " + (okC ? "Pasa en Primera Revision" : "Segunda Revision Asignada") + "\n");

        
        sb.append(String.format("%% de errores a arreglar en el lote: %.1f%% (%d de %d valores)\n",
                pGeneral, totalFallas, autos.size() * 4));
        boolean aprobado = okL && okO && okN && okC;
        sb.append("Condiciona la aprobacion del lote: "
                + (aprobado ? "NO, el lote APRUEBA la revision vehicular"
                            : "SI, el lote NO aprueba, queda en Segunda Revision") + "\n");

       
        synchronized (System.out) {
            System.out.println(sb);
        }
    }
}