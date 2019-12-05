package presentacion;

import java.util.Scanner;

import dominio.Problema;



public class Main {

	private static final Scanner TECLADO = new Scanner(System.in);
	
	public static void main(String[] args) throws Exception {
		
	inicializar();	
		
	}
	
	private static void inicializar() throws Exception {
		boolean fin=true;
		int estrategia,Prof_Max, Inc_Prof;
		
		
		//System.out.println("Introduzca la direccion del json");
		//String archivo = TECLADO.nextLine();
		String archivo ="3x3.json";
		Problema p= new Problema(archivo);
		
		while(fin) {
		
		System.out.println("Ahora seleccione la estrategia a usar");
		System.out.println("1. Anchura\n2. Costo uniforme\n3. Profundidad acotada\n4. Profundidad iterativa\n5. Voraz\n6. A*\n0.Finalizar");
		estrategia=TECLADO.nextInt();
		System.out.println("Introduzca la Profundidad maxima");
		Prof_Max=TECLADO.nextInt();
		switch (estrategia) {
		case 0:
			fin=false;
			break;
		case 1:
			llamarArchivo(p,"anchura",Prof_Max,Prof_Max);
			break;
		case 2:
			llamarArchivo(p,"costo uniforme",Prof_Max,Prof_Max);
			break;

		case 3:
			llamarArchivo(p,"profundidad acotada",Prof_Max,Prof_Max);
			break;
		case 4:
			System.out.println("Introduzca el incremento de la profundidad");
			Inc_Prof=TECLADO.nextInt();
			llamarArchivo(p,"profundidad iterativa",Prof_Max,Inc_Prof);
			break;
		case 5:
			llamarArchivo(p,"voraz",Prof_Max,Prof_Max);
			break;
		case 6:
			llamarArchivo(p,"A",Prof_Max,Prof_Max);
			break;
		default:
			System.out.println("Error al introducir estrategia");
			break;
		}
		}
		
		TECLADO.close();
		System.out.println("Fin del programa");
	}
	
	private static void llamarArchivo(Problema p,String estrategia,int Prof_Max,int Inc_Prof) throws Exception {
		 boolean sol = p.busqueda(estrategia, Prof_Max, Inc_Prof);
		 if(sol)
			 System.out.println(p.getSolucion().toString()+"\n"
					 +p.getSolucion().getEstado().getCube().toString());
		 else
			 System.out.println("No hay solucion");

	}
	
	
}