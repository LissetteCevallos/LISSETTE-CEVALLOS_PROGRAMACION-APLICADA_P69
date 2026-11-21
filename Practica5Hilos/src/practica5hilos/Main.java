package practica5hilos;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        
        List<String> nombres = Files.readAllLines(Paths.get("mecanicos.txt"));
        List<String> lineas = Files.readAllLines(Paths.get("vehiculos.txt"));

       
        List<Vehiculo> vehiculos = new ArrayList<>();
        for (String linea : lineas) {
            if (linea.trim().isEmpty()) continue; // salto lineas vacias
            String[] partes = linea.split(",");
            vehiculos.add(new Vehiculo(partes[0].trim(), partes[1].trim()));
        }

        
        Collections.shuffle(vehiculos);

        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < nombres.size(); i++) {
            
            List<Vehiculo> lote = new ArrayList<>(vehiculos.subList(i * 5, i * 5 + 5));

            
            Thread t = new Thread(new Mecanico(nombres.get(i).trim(), lote));
            hilos.add(t);
            t.start();
        }

       
        for (Thread t : hilos) {
            t.join();
        }
        System.out.println("\nTodos los mecanicos terminaron.");
    }
}
