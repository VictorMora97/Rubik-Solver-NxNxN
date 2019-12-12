package dominio;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import persistencia.AgentJSON;
import persistencia.Tool;

public class EspacioEstados {
    private static Estado estadoInicial;
    

    public EspacioEstados(String ruta) throws FileNotFoundException {

			estadoInicial = new Estado("inicio",AgentJSON.getCube(ruta),0);

    }
    public static Estado getEstadoInicial() {
		return estadoInicial;
	}

	public List<Estado> sucesores(Estado e){
    	List<Estado> result = new LinkedList<>();
    	Cube cube = e.getCube();
    	Cube aux;

    	/*for(int i = 0;i<cube.getN();i++) {    	
   			aux = cube.clone(); aux.B(i); result.add(new Estado("B"+i,aux,1));
    		aux = cube.clone(); aux.b(i); result.add(new Estado("b"+i,aux,1));
    		aux = cube.clone(); aux.D(i); result.add(new Estado("D"+i,aux,1));
    		aux = cube.clone(); aux.d(i); result.add(new Estado("d"+i,aux,1));
    		aux = cube.clone(); aux.L(i); result.add(new Estado("L"+i,aux,1));
    		aux = cube.clone(); aux.l(i); result.add(new Estado("l"+i,aux,1));
    	}  */
    	
    	for(int i = 0;i<cube.getN();i++) {
    		aux = cube.clone(); aux.B(i); result.add(new Estado("B"+i,aux,1));
    	}
    	for(int i = 0;i<cube.getN();i++) {
    		aux = cube.clone(); aux.b(i); result.add(new Estado("b"+i,aux,1));
    	}
    	for(int i = 0;i<cube.getN();i++) {
    		aux = cube.clone(); aux.D(i); result.add(new Estado("D"+i,aux,1));
    	}
    	for(int i = 0;i<cube.getN();i++) {
    		aux = cube.clone(); aux.d(i); result.add(new Estado("d"+i,aux,1));
    	}
    	for(int i = 0;i<cube.getN();i++) {
    		aux = cube.clone(); aux.L(i); result.add(new Estado("L"+i,aux,1));
    	}
    	for(int i = 0;i<cube.getN();i++) {
    		aux = cube.clone(); aux.l(i); result.add(new Estado("l"+i,aux,1));
    	} 
    	
    	return result;
    }
    
}
    	
    
    	




