package dominio;

import java.io.FileNotFoundException;

public class Problema {
	
	private EspacioEstados espacioEstados;
	private Estado estadoInicial;
	private Frontera frontera;
	
	
	public Problema(String ruta) throws FileNotFoundException {
		espacioEstados = new EspacioEstados(ruta);
		estadoInicial = espacioEstados.getEstadoInicial();
	}
	
	/*public boolean esObjetivo(Estado e) {
		Cube cube = e.getCube();
		return isEqual(cube.getBack())
			&& isEqual(cube.getDown())
			&& isEqual(cube.getFront())
			&& isEqual(cube.getLeft())
			&& isEqual(cube.getRight())
			&& isEqual(cube.getUp());
	}*/
	
	private boolean isEqual(byte[][] matrix) {
		byte aux = matrix[0][0];
		for(int i=0; i< matrix.length;i++)
			for(int j=0; j< matrix.length;j++)
				if(matrix[i][j] != aux)
					return false;
		return true;
	}
    
	
}