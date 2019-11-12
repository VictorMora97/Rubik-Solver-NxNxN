package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import dominio.NodoArbol;

public class Problema {
	
	private EspacioEstados espacioEstados;
	private Estado estadoInicial;
	private Frontera frontera;
	private NodoArbol solucion;
    List<Estado> LS= new LinkedList<>();
    List<NodoArbol> LN= new LinkedList<>();
    private List<NodoArbol> listaPoda= new LinkedList<>();
    private NodoArbol n_actual;
	
	
	public Problema(String ruta) throws FileNotFoundException {
		espacioEstados = new EspacioEstados(ruta);
		estadoInicial = espacioEstados.getEstadoInicial();
	}
	
	public boolean busqueda(String estrategia,int Prof_Max,int Inc_Prof) throws Exception {
		//inicializo las variables
		solucion = null;
    	int Prof_Actual = Inc_Prof;
    	
    	switch(estrategia) {
	    	case "anchura":
	    	case "costo uniforme":
	    	case "profundidad acotada":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    	break;
	    		
	    	case "profundidad iterativa":
	    		while (solucion == null && Prof_Actual <= Prof_Max){
	                solucion = busquedaAcotada(estrategia,Prof_Actual);
	                Prof_Actual += Inc_Prof;
	            }
	    	break;
    	}

    	if(solucion!=null) {
    		System.out.println(solucion.toString());
    		save("rubick.out",solucion.toString());
    		return true;
    	}
	  return false;
    }
	

	private NodoArbol busquedaAcotada(String estrategia,int Prof_Max) {
        boolean solucion = false;
        Estado estadoInicial = EspacioEstados.getEstadoInicial();
        if(estadoInicial != null){
            
        	//Frontera depende de la estrategia
        	frontera = new FronteraOrdenada(); 
        	
            //Proceso de inicializacion  
            n_actual = new NodoArbol(null, estadoInicial, 0 , null, 0, 0);
            frontera.insertar(n_actual);
            
            //Bucle de busqueda
            while(!solucion && !frontera.esVacia()){
                n_actual = frontera.eliminar();
                
                if(esObjetivo(n_actual.getEstado()))
                    solucion = true;
                else
                    try{
                        LS = espacioEstados.sucesores(n_actual.getEstado());
                        LN = ListaNodosArbol(LS,n_actual,Prof_Max,estrategia);
                        frontera.insertar(LN);
                    }catch(Exception e){ System.out.println("Problema ln83 "+e);
                }    
            }
        }
        
        return (solucion)? n_actual : null;
	}
	public boolean esObjetivo(Estado e) {
		Cube cube = e.getCube();
		return isEqual(cube.getBack())
			&& isEqual(cube.getDown())
			&& isEqual(cube.getFront())
			&& isEqual(cube.getLeft())
			&& isEqual(cube.getRight())
			&& isEqual(cube.getUp());
	}
	private boolean isEqual(byte[][] matrix) {
		byte aux = matrix[0][0];
		for(int i=0; i< matrix.length;i++)
			for(int j=0; j< matrix.length;j++)
				if(matrix[i][j] != aux)
					return false;
		return true;
	}
    
	//////// Hay que hacerlo
	private List<NodoArbol> ListaNodosArbol(List<Estado> LS, NodoArbol n_actual,int Prof_Max, String estrategia){
		return null;
	}
	
	private static void save(String fileName, String text) {
		try{
			PrintWriter fileOut=new PrintWriter(new FileWriter(new File(fileName)));
	    	   fileOut.print(text);
	        fileOut.close();
	   }catch(Exception e){}
	}
}