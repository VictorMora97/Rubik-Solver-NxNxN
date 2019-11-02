package presentacion;

import java.io.FileNotFoundException;
import java.util.Random;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import dominio.Cube;
import dominio.EspacioEstados;
import dominio.Estado;
import dominio.FronteraOrdenada;
import dominio.NodoArbol;
import persistencia.Tool;

public class PruebaInsercion {
	static String ubicacion = "C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\test1.json";
	static int f;
	

	public static void main(String[] args) throws FileNotFoundException {
		FronteraOrdenada frontera = new FronteraOrdenada();
		testOrdenacion(frontera);
		Tool.Pintar("\n"+"-----------------------------------"+"\n");
		testTiempos(frontera);	

	}
	
	
	private static void testTiempos(FronteraOrdenada frontera) throws FileNotFoundException {
		long tiempo;
		long sum=0;
		long tmax = 0;
		long tmin = 999999999;
		EspacioEstados ee;
		ee = new EspacioEstados(ubicacion);
		Estado estadoInicial = ee.getEstadoInicial();
		Cube cube = estadoInicial.getCube();
		Cube aux = cube.clone(); aux.B(2); 
		Estado estado =  new Estado("B"+2,aux,1);	
		NodoArbol inicial = new NodoArbol(null, estado,1,"B",5,10001);
		frontera.insertar(inicial);

		for(int i=0;i<100;i++) {
			NodoArbol nodo = new NodoArbol( inicial,estado,1,"B",5,10001);
			f = getRandomNumberInRange(1, 10000000);
			long startTime = System.nanoTime();
			frontera.insertar(nodo);
			long finalTime = System.nanoTime();		
			tiempo = finalTime-startTime;
			sum = sum + tiempo;
			if (tiempo < tmin) {
				tmin = tiempo; 
			}
			if (tiempo > tmax) {
				tmax = tiempo;
			}
		}
		Tool.Pintar("Tiempo Minimo: "+tmin);
		Tool.Pintar("Tiempo Medio: "+(sum/100));
		Tool.Pintar("Tiempo Maximo: "+tmax);
	}
	
	private static void testOrdenacion(FronteraOrdenada frontera) throws FileNotFoundException {
		
		EspacioEstados ee;
		ee = new EspacioEstados(ubicacion);
		Estado estadoInicial = ee.getEstadoInicial();
		Cube cube = estadoInicial.getCube();
		Cube aux = cube.clone(); aux.B(2); 
		Estado estado =  new Estado("B"+2,aux,1);	
		NodoArbol inicial = new NodoArbol(null, estado,1,"B",5,10001);
		frontera.insertar(inicial);
		Tool.Pintar("Valores aleatorios de f:");
		
		for(int i=0;i<5;i++) {
			f = getRandomNumberInRange(1, 10000);
			NodoArbol nodo = new NodoArbol(inicial, estado,1,"L",5,f);
			frontera.insertar(nodo);
			Tool.Pintar(f);
			
		}
		Tool.Pintar("");
		Tool.Pintar("Tamaño frontera: "+(frontera.size()-1));
		Tool.Pintar("Primero en la cola: "+frontera.get());
	}

	private static int getRandomNumberInRange(int min, int max) {
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
