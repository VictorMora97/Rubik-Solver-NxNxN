package presentacion;

import java.io.FileNotFoundException;

import dominio.Cube;
import dominio.EspacioEstados;
import dominio.Estado;
import dominio.FronteraOrdenada;
import dominio.NodoArbol;
import persistencia.Tool;

public class Stress {
	static String ubicacion = "C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\test1.json";
	static int f;

	public static void main(String[] args) throws FileNotFoundException {
		FronteraOrdenada frontera = new FronteraOrdenada();
		EspacioEstados ee;
		ee = new EspacioEstados(ubicacion);
		Estado estadoInicial = ee.getEstadoInicial();
		Cube cube = estadoInicial.getCube();
		Cube aux = cube.clone(); aux.B(2); 
		Estado estado =  new Estado("B"+2,aux,1);	
		NodoArbol inicial = new NodoArbol(null, estado,1,"B",5,10001);
		frontera.insertar(inicial);

		while(true) {
			f = Tool.getRandomNumberInRange(1, 10000000);
			NodoArbol nodo = new NodoArbol( inicial,estado,1,"B",5,f);
			frontera.insertar(nodo);
			Tool.Pintar(frontera.size());
		}
		
	}
}
