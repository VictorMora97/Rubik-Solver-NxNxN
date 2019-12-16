package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import dominio.Estado;
import dominio.NodoArbol;
import persistencia.Tool;

public class Problema {
	
	private EspacioEstados espacioEstados;
	private Estado estadoInicial;
	private NodoArbol solucion;
	private static int cnt_creado;
	private int cnt_frontera;
	private int cnt_abierto;
	
	
    private static List<NodoArbol> listaPoda= new LinkedList<>();
    static Frontera frontera = new FronteraOrdenada();
    
    public NodoArbol getSolucion() {
		return solucion;
	}
	
	public Problema(String ruta) throws FileNotFoundException {
		espacioEstados = new EspacioEstados(ruta);
		estadoInicial = EspacioEstados.getEstadoInicial();
	}
	
	public boolean busqueda(String estrategia,int Prof_Max,int Inc_Prof) throws Exception {
		solucion = null;
	
		switch(estrategia) {
	    	case "anchura":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    	break;
	    	case "costo uniforme":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    	break;
	    	case "profundidad":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    	break;
	    	case "voraz":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    	break;    	
	    	case "A":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    	break;  	    		    	
		}
		if(solucion!=null) {
			save("rubick.out",solucion.toString());
			return true;
		}
	  return false;
	}
	
	private NodoArbol busquedaAcotada(String estrategia,int Prof_Max) {
	    boolean solucion = false;
	    NodoArbol n_actual = null;
	    
	    if(estadoInicial != null){     
	    	Frontera frontera = new FronteraOrdenada(); 
	    	cnt_creado = 0;
	    	cnt_frontera = cnt_abierto = 0;
	    	n_actual = new NodoArbol(null, estadoInicial, "",-1, 0, 0);
	    	double f = calcularF(n_actual,estadoInicial,estrategia);
	    	n_actual = new NodoArbol(null, estadoInicial, "",0, 0, f);
	        frontera.insertar(n_actual);
	        cnt_frontera++;
	        
	        while(!solucion && !frontera.esVacia()){
	            n_actual = frontera.eliminar();
	            cnt_abierto++;
	            
	            if(esObjetivo(n_actual.getEstado())) {
	                solucion = true;    
	        	} else
	                try{
	                    List<Estado> LS = espacioEstados.sucesores(n_actual.getEstado());
	                    List<NodoArbol> LN = ListaNodosArbol(LS,n_actual,Prof_Max,estrategia);
	                    cnt_frontera += LN.size();
	                    frontera.insertarLista(LN);
	                }catch(Exception e){ 
	            }    
	        }
	    }
	    return n_actual;
	}

    
	private static List<NodoArbol> ListaNodosArbol(List<Estado> LS, NodoArbol n_padre,int Prof_Max, String estrategia){
		List<NodoArbol> result = new LinkedList<>();
		
		for(Estado estadoActual : LS) {
			double f = calcularF(n_padre,estadoActual,estrategia);	
			
			if(n_padre.getD() < Prof_Max) {		
				NodoArbol nodo = new NodoArbol(n_padre, estadoActual,estadoActual.getAcci() ,n_padre.getCoste()+1, n_padre.getD()+1,f);
				nodo.setCoste( n_padre.getCoste()+1);
				cnt_creado++;
				nodo.setId_nodo(cnt_creado);
				result.add(nodo);
			}
		}
		return result;
	}

	private static double calcularF(NodoArbol padre,Estado estado,String estrategia){
	    double g=0, h=0;
	       
	    switch(estrategia){
	        case "anchura":
	            g = padre.getD()+1; 
	        break;
	        case "costo uniforme":
	        	g = padre.getCoste()+1;  
	        break;                        
	        case "profundidad":
	        	g = -(padre.getD()+1); 	
	        break;	
	        case "profundidad iterativa":
	            g = -(padre.getD()+1); 
	        break;
	        case "voraz":
	        	h = estado.getCube().getH();
	         break;
	        case "A":
	        	g = padre.getCoste()+1;
	            h = estado.getCube().getH();
	        break;
	    }
	   return Math.round((g+h)*100d)/100d;
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
			for(Byte b :matrix[i])
				if(!b.equals(aux))
					return false;		
		return true;
	}
		
	private static void save(String fileName, String text) {
		try{
			PrintWriter fileOut=new PrintWriter(new FileWriter(new File(fileName)));
	    	   fileOut.print(text);
	        fileOut.close();
	   }catch(Exception e){}
	}
	
	/*private static boolean poda(NodoArbol nodo) {
    	boolean result = false; // por defecto no se poda
    	int index = listaPoda.indexOf(nodo);
    	
    	//si no ha sido visitado, no se poda
    	if(index == -1)
    		listaPoda.add(nodo);
    	else {
    		NodoArbol aux = listaPoda.get(index);
    		//Si el que habia es igual o mejor, se poda
    		if(aux.getCoste() <= nodo.getCoste())
    			result = true;
    		else //Si el que habia es peor, no se poda
    			listaPoda.set(index, nodo); //sustituir
    	}
		return result;
	} */
}