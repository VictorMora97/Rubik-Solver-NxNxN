import java.util.Random;

public class NodoArbol {
	
	static String[][][] cuboPadre;
	static String[][][] cuboActual;
	static String accion;
	static int numAccion;
	static int flagAccion;
	static int costeAccion;
	static int f;
	static int profundidad;
	
	public static void main(String[] args) {
		
		//Cubo.PintarCubo(cuboPadre);
		//Cubo.PintarCubo(cuboActual);
		
	}
	
	public static int crearNodo(String[][][] cubo, String[][][] nuevoEstado, String movimiento, int numero, int flag, int coste) {
		
		cuboPadre = (String[][][])cubo.clone();
		cuboActual = (String[][][])nuevoEstado.clone();
		accion = movimiento;
		numAccion = numero;
		flagAccion = flag;
		costeAccion = coste;
		f = getRandomNumberInRange(1, 10000);
		Cubo.Pintar(accion+"-"+numAccion+"-"+flagAccion+"-"+costeAccion+"-"+f);
		return f;
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
