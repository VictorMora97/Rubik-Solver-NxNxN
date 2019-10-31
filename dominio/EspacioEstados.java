package dominio;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

import persistencia.AgentJSON;

public class EspacioEstados {
    private Estado estadoInicial;
    
    public EspacioEstados(String ruta) throws FileNotFoundException {

			estadoInicial = new Estado("inicio",AgentJSON.getCube(ruta),0);

    }
    public Estado getEstadoInicial() {
		return estadoInicial;
	}


	public List<Estado> sucesores(Estado e){
    	List<Estado> result = new LinkedList<>();
    	Cube cube = e.getCube();
    	Cube aux;
    	//cube.B(0), cube.b(0), cube.D(0),cube.d(0), cube.L(0), cube.l(0)
    	// ...
    	//cube.B(N-1), cube.b(N-1), cube.D(N-1),cube.d(N-1), cube.L(N-1), cube.l(N-1)
    	for(int i = 0;i<cube.getN();i++) {
    		aux = cube.clone(); aux.B(i); result.add(new Estado("B"+i,aux,1));
    		aux = cube.clone(); aux.b(i); result.add(new Estado("b"+i,aux,1));
    		
    		aux = cube.clone(); aux.D(i); result.add(new Estado("D"+i,aux,1));
    		aux = cube.clone(); aux.d(i); result.add(new Estado("d"+i,aux,1));
    		
    		aux = cube.clone(); aux.L(i); result.add(new Estado("L"+i,aux,1));
    		aux = cube.clone(); aux.l(i); result.add(new Estado("l"+i,aux,1));	
    	}
    	
    	/*
    	//cube.B(0), cube.B(1)... cube.B(N-1)
    	// ...
    	//cube.l(0), cube.l(1)... cube.l(N-1)
    	
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
    	*/  	
    	return result;
    }
    
}
    	
    
    	




