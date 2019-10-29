import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class pruebaTreeMap {

	public static void main (String [] args) {

		Cubo.main(args);
		Cubo.Pintar("\n");

	}
	
	private static void pruebaOrdenacion() {
		int f;
		TreeMap <Integer, NodoArbol> frontera = new TreeMap<Integer, NodoArbol>();
		for(int i=0;i<5;i++) {
			NodoArbol nodo = new NodoArbol();
			f = getRandomNumberInRange(1, 10000);
			nodo.crearNodo(Cubo.getCubo(), Cubo.getCopiaCubo(), "D", 3, -90, 1, f);
			frontera.put(f, nodo);
		}
		Cubo.Pintar(frontera);
	}
	
	private static void pruebaTiempoMedio(){
		int f;
		long tiempo;
		long sum = 0;
		TreeMap <Integer, NodoArbol> frontera = new TreeMap<Integer, NodoArbol>();
		NodoArbol nodo1 = new NodoArbol();
		f = getRandomNumberInRange(1, 10000000);
		nodo1.crearNodo(Cubo.getCubo(), Cubo.getCopiaCubo(), "D", 3, -90, 1, f);
		frontera.put(f, nodo1);		
		
		for(int i=0;i<100;i++) {
			NodoArbol nodo = new NodoArbol();
			f = getRandomNumberInRange(1, 10000000);
			nodo.crearNodo(Cubo.getCubo(), Cubo.getCopiaCubo(), "D", 3, -90, 1, f);
			long startTime = System.nanoTime();
			frontera.put(f, nodo);
			long finalTime = System.nanoTime();		
			tiempo = finalTime-startTime;
			sum = sum + tiempo;
		}
		System.out.println("Tiempo Medio: "+(sum/100));	
	}
	
	private static void pruebaRangoTiempo() {
		int f;
		long tiempo;
		long tmax = 0;
		long tmin = 999999999;
		TreeMap <Integer, NodoArbol> frontera = new TreeMap<Integer, NodoArbol>();
		NodoArbol nodo1 = new NodoArbol();
		f = getRandomNumberInRange(1, 10000000);
		nodo1.crearNodo(Cubo.getCubo(), Cubo.getCopiaCubo(), "D", 3, -90, 1, f);
		frontera.put(f, nodo1);
		
		for(int i=0;i<10;i++) {
			NodoArbol nodo = new NodoArbol();
			f = getRandomNumberInRange(1, 10000000);
			nodo.crearNodo(Cubo.getCubo(), Cubo.getCopiaCubo(), "D", 3, -90, 1, f);
			long startTime = System.nanoTime();
			frontera.put(f, nodo);
			long finalTime = System.nanoTime();		
			tiempo = finalTime-startTime;	
			if (tiempo < tmin) {
				tmin = tiempo; 
			}
			if (tiempo > tmax) {
				tmax = tiempo;
			}
			System.out.println("Tiempo Nodo "+i+": "+tiempo);
		}
		Cubo.Pintar("\n");
		System.out.println("Tiempo Minimo: "+tmin);
		System.out.println("Tiempo Maximo: "+tmax);
		
	}
	private static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}

