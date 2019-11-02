package presentacion;

import java.io.FileNotFoundException;

import dominio.Cube;
import dominio.EspacioEstados;
import dominio.Estado;
import persistencia.Tool;

public class PruebaMovimientos {
	
	public static void main(String[] args) throws FileNotFoundException  {
		EspacioEstados ee;
		String ubicacion = "C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\test1.json";
		
			
			ee = new EspacioEstados(ubicacion);
			Estado estadoInicial = ee.getEstadoInicial();
			Cube cube = estadoInicial.getCube();
	    	
			Tool.Pintar(cube.makeId());
	    	cube.l(3);
	    	Tool.Pintar(cube.makeId());
	    	cube.D(1);
	    	Tool.Pintar(cube.makeId());
	    	cube.l(1);
	    	Tool.Pintar(cube.makeId());
	    	cube.d(0);
	    	Tool.Pintar(cube.makeId());
	    	cube.B(0);
	    	Tool.Pintar(cube.makeId());
	    	cube.b(5);
	    	Tool.Pintar(cube.makeId());
	    	cube.l(2);
	    	Tool.Pintar(cube.makeId());
	    	cube.d(1);
			
	}

}
