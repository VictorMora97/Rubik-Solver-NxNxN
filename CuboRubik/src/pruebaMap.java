import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class pruebaMap {

	public static void main (String [] args) {
		int f;
		TreeMap <Integer, NodoArbol> tmap = new TreeMap<Integer, NodoArbol>();
		Cubo.main(args);
		for (int i=0; i<5; i++) {
			NodoArbol nodo = new NodoArbol();
			f = getRandomNumberInRange(1, 10000);
			nodo.crearNodo(Cubo.getCubo(), Cubo.getCopiaCubo(), "D", 3, -90, 1, f);
			
			tmap.put(f, nodo);
			
			Cubo.Pintar(tmap);
		}

		
	}
	private static int getRandomNumberInRange(int min, int max) {

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
