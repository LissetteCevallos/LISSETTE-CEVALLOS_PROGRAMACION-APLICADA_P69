package practica5hilos;

import java.util.Random;

public class Vehiculo {
    String marca;
    String placa;
    double lambda;
    double o2;
    double n2o;
    double co2;

    public Vehiculo(String marca, String placa) {
        this.marca = marca;
        this.placa = placa;

        
        Random r = new Random();
        this.lambda = 0.8 + r.nextDouble() * 0.5;  
        this.o2 = r.nextDouble() * 4;             
        this.n2o = 20 + r.nextDouble() * 40;       
        this.co2 = 5 + r.nextDouble() * 20;        
    }
}
