package parqueadero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class GestorArchivos {
	private static final String ARCHIVO_VEHICULOS = "vehiculos.txt";
	private static final String ARCHIVO_CAJA = "caja.txt";
	
public static void guardarVehiculos(ArrayList<Vehiculo> lista) {
	
	try (PrintWriter pw =new PrintWriter(new FileWriter (ARCHIVO_VEHICULOS))){  //ABRE EL ARCHIVO PARA ESRIBIR  (lo crea si no existe y borra lo anterior)
		
		for (Vehiculo v :lista) {
			pw.println (v.get_placa() + ";"+ v.get_hora_entrada() +";"+ v.get_hora_salida());
	
		}
	} catch (IOException e ) {
		System.out.println ("Error al guardar : "+e.getMessage());
	}
}

public static ArrayList <Vehiculo > cargarVehiculos(){
	ArrayList<Vehiculo >lista = new ArrayList <>();
	
	File f = new File(ARCHIVO_VEHICULOS); //REPRESENTA EL ARCHIVO SIN ABRIR 
	if (!f.exists()) return lista; //si no existe devuelve lista vacia 
	
	try (BufferedReader br =new BufferedReader (new FileReader (f))){
		String linea;
		
		while ((linea =br.readLine())!=null) {
			
			String [] p=linea.split(";");
			LocalDateTime entrada = LocalDateTime.parse(p[1]);
			LocalDateTime salida = p[2].equals("null")? null :LocalDateTime.parse(p[2]);
			
			lista.add(new Vehiculo (p[0],entrada,salida));
		}
		
	}catch (IOException e) {
		System.out.println ("Error al leer :"+e.getMessage());
	}
	
	return lista;
	}
public static void guardarCaja(ArrayList<String> caja) {
	try (PrintWriter pw = new PrintWriter(new FileWriter(ARCHIVO_CAJA))) {
		for (String linea : caja) {
			pw.println(linea);
		}
	} catch (IOException e) {
		System.out.println("Error al guardar caja: " + e.getMessage());
	}
}

public static ArrayList<String> cargarCaja() {
	ArrayList<String> caja = new ArrayList<>();
	File f = new File(ARCHIVO_CAJA);
	if (!f.exists()) return caja;
	try (BufferedReader br = new BufferedReader(new FileReader(f))) {
		String linea;
		while ((linea = br.readLine()) != null) {
			caja.add(linea);
		}
	} catch (IOException e) {
		System.out.println("Error al leer caja: " + e.getMessage());
	}
	return caja;
}
}



