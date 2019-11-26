package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import dominio.Estado;
import dominio.NodoArbol;

public class Problema {
	
	private EspacioEstados espacioEstados;
	private Estado estadoInicial;
	private NodoArbol solucion;

    private List<NodoArbol> listaPoda= new LinkedList<>();

	
	
	public Problema(String ruta) throws FileNotFoundException {
		espacioEstados = new EspacioEstados(ruta);
		estadoInicial = EspacioEstados.getEstadoInicial();
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
	    		while (solucion==null && Prof_Actual <= Prof_Max){
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
        NodoArbol n_actual = null;
        
        if(estadoInicial != null){     
        	//Frontera depende de la estrategia
        	Frontera frontera = new FronteraOrdenada(); 
        	
            //Proceso de inicializacion  
        	n_actual = new NodoArbol(null, estadoInicial, "",0, 0, 0);
            frontera.insertar(n_actual);
            
            //Bucle de busqueda
            while(!solucion && !frontera.esVacia()){
                n_actual = frontera.eliminar();
                
                if(esObjetivo(n_actual.getEstado()))
                    solucion = true;
                else
                    try{
                        List<Estado> LS = espacioEstados.sucesores(n_actual.getEstado());
                        List<NodoArbol> LN = ListaNodosArbol(LS,n_actual,Prof_Max,estrategia);
                        frontera.insertar(LN);
                    }catch(Exception e){ System.out.println("Problema ln83 "+e);
                }    
            }
        }
        
        return n_actual;
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

	private static List<NodoArbol> ListaNodosArbol(List<Estado> LS, NodoArbol n_padre,int Prof_Max, String estrategia){
		List<NodoArbol> result = new LinkedList<>();
		
		for(Estado estadoActual : LS) {
			double f = calcularF(n_padre,estadoActual,estrategia,Prof_Max);
			NodoArbol nodo = new NodoArbol(n_padre, estadoActual,estadoActual.getAcci() ,1, n_padre.getD()+1, f);
			if(!poda(nodo))
				result.add(nodo);
		}
		
		return result;
	}
	
	private static double calcularF(NodoArbol padre,Estado estado,String estrategia, int Prof_Max){
        double g=0, h=0;
           
        switch(estrategia){
            case "anchura":
                g = padre.getD()+1; //profundidad positiva
            break;
            case "costo uniforme":
            	g = padre.getD()+1;  //coste positivo
            break;     
            case "profundidad acotada":  
            case "profundidad iterativa":
                g = -(padre.getD()+1); // profundidad negativa
            break;
            case "voraz":
            	h = estado.getCube().getH();
            	break;
            case "A":
            	g = padre.getD()+1;
                h = estado.getCube().getH();
            break;
        }
        return g+h;
    }
	private static boolean poda(NodoArbol nodo) {
    	boolean out = false;
        Estado actual = nodo.getEstado();
        String id = nodo.getEstado().getCube().getId();
        
        
        

		return false;
		}
	
	private static void save(String fileName, String text) {
		try{
			PrintWriter fileOut=new PrintWriter(new FileWriter(new File(fileName)));
	    	   fileOut.print(text);
	        fileOut.close();
	   }catch(Exception e){}
	}

}
