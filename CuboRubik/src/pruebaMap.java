import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class pruebaMap {

	public static void main (String [] args) {
		int f;
		long tiempo;
		long tmax = 0;
		long tmin = 1000000000;
		long tmedio = 0;
		long tmedio20 = 0;
		TreeMap <Integer, NodoArbol> tmap = new TreeMap<Integer, NodoArbol>();
		Cubo.main(args);
		for (int j = 0; j < 50; j++) {
		for (int i = 0; i < 1000; i++) {
			NodoArbol nodo = new NodoArbol();
			f = getRandomNumberInRange(1, 10000);
			nodo.crearNodo(Cubo.getCubo(), Cubo.getCopiaCubo(), "D", 3, -90, 1, f);
			
			long startTime = System.nanoTime();
			tmap.put(f, nodo);
			long finalTime = System.nanoTime();
			
			tiempo = finalTime-startTime;
			
			if (tiempo < tmin) {
				tmin = tiempo;
			}
			if (tiempo > tmax) {
				tmax = tiempo;
			}
			//Cubo.Pintar(tiempo);
		}
		tmedio = (tmax + tmin)/2;
		tmedio20 = (tmedio + tmedio20);
		}
	
			Cubo.Pintar(tmax);
			Cubo.Pintar(tmin);
			Cubo.Pintar(tmedio);
			Cubo.Pintar(tmedio20/19);
	
		
	}
	private static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
