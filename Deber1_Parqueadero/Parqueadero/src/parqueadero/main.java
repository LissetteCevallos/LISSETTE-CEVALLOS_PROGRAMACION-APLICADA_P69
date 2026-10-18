package parqueadero;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc =new Scanner (System.in);
		
		Parqueadero parqueadero =new Parqueadero(); //crea objeto principal
		int op;
		
		do {
			System.out.println("\n--- PARQUEADERO UPS ---");
			System.out.println("1. Registrar entrada");
			System.out.println("2. Registrar salida");
			System.out.println("3. Listar vehículos");
			System.out.println("4. Total a Pagar");
			System.out.println("0. Salir");
			System.out.print("Opción: ");
			op = sc.nextInt();
			
			sc.nextLine();
			
			switch (op) {
			
			case 1:
				System.out.print("Placa : ");
				String placa =sc.nextLine(); //lee placa escrita
				if(parqueadero.registrarEntrada(placa))
					System.out.println ("Entrada Registrada exitosamente");
				else 
					System.out.println ("El vehiculo ya esta dentro del parqueadero");
				
				break;
				
			case 2:
				System.out.print ("Placa : ");
				double valor =parqueadero.registarSalida(sc.nextLine());
				
				if (valor < 0)System.out.println("Vehiculo no encontrado ");
				else
					System.out.println( "Valor a cancelar : $"+valor);
				break;
				
			case 3:
				parqueadero.listar_Vehiculos();
				break;
				
			case 4:
				System.out.println("Total a Canelar : $"+parqueadero.TOTAL());
				break;
			}
		} while (op !=0);
		
		sc.close ();
	}

}
