package dominio;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;

import java.util.List;

import persistencia.Tool;

public class Problema {
	
	private static EspacioEstados espacioEstados;
	private static String ubicacion = "C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\pruebas.json";
	private static Estado estadoInicial;
	private static Estado estadoLS;
	private static Frontera frontera;
	private static NodoArbol solucion;
    static List<Estado> LS= new LinkedList<>();
    static List<NodoArbol> LN= new LinkedList<>();
    private List<NodoArbol> listaPoda= new LinkedList<>();
    private static NodoArbol n_actual;
    private static NodoArbol n_inicial;
    private static int Prof_act;
    private static double f ;
	
	
    public static void main(String[] args) throws Exception {
    	
    Problema prob = new Problema(ubicacion);	
    busquedaAcotada(prob,"anchura",6);	
    	
    }
    
	public Problema(String ruta) throws FileNotFoundException {
		espacioEstados = new EspacioEstados(ruta);
		estadoInicial = espacioEstados.getEstadoInicial();
	}
	
	public static boolean busqueda(String estrategia,int Prof_Max,int Inc_Prof) throws Exception {
		solucion = null;
    	int Prof_Actual = Inc_Prof;
    	
    	switch(estrategia) {
	    	case "anchura":
	    	case "costo uniforme":
	    	case "profundidad acotada":
	    		//solucion = busquedaAcotada(estrategia,Prof_Max);
	    	break;
	    		
	    	case "profundidad iterativa":
	    		
	    		while (solucion == null && Prof_Actual <= Prof_Max){
	               // solucion = busquedaAcotada(prob,estrategia,Prof_Actual);             
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
	

	private static NodoArbol busquedaAcotada(Problema problema, String estrategia,int Prof_Max) throws FileNotFoundException {
        
		boolean solucion = false;
        if(estadoInicial != null){
        	
        	//Frontera depende de la estrategia ---> ¡¡OJO!!, Usar siempre la misma (PriorityQueue)
        	frontera = new FronteraOrdenada(); 
        	
            //Proceso de inicializacion  
            n_inicial = new NodoArbol(null, estadoInicial, 0 , null, 0, 0);
            frontera.insertar(n_inicial);
            Prof_act = 0;
            //Bucle de busqueda
            while(!solucion && !frontera.esVacia()){
                n_actual = frontera.eliminar();  
                
                if(esObjetivo(n_actual.getEstado())) {
                	Tool.Pintar("ES OBJETIVO");
            		Tool.Pintar(n_actual.getEstado());
                    solucion = true; }
                	
                else
                    try{
                    	Prof_act = Prof_act +1;
                        LS = espacioEstados.sucesores(n_actual.getEstado());
                        LN = ListaNodosArbol(LS,n_actual,Prof_Max,estrategia,Prof_act);
                        frontera.insertarLista(LN);
                    }catch(Exception e){ System.out.println("Problema ln83 "+e);
                }    
            }
        }
        
        return (solucion)? n_actual : null;
	}
	public static boolean esObjetivo(Estado e) {
		Cube cube = e.getCube();
		return isEqual(cube.getBack())
			&& isEqual(cube.getDown())
			&& isEqual(cube.getFront())
			&& isEqual(cube.getLeft())
			&& isEqual(cube.getRight())
			&& isEqual(cube.getUp());
	}
	private static boolean isEqual(byte[][] matrix) {
		byte aux = matrix[0][0];
		for(int i=0; i< matrix.length;i++)
			for(int j=0; j< matrix.length;j++)
				if(matrix[i][j] != aux)
					return false;
		return true;
	}
    
	private static List<NodoArbol> ListaNodosArbol(List<Estado> LS, NodoArbol n_actual,int Prof_Max, String estrategia, int profundidad_nodo){
		
		LN.clear();
		while(LS.size()>0) {
			estadoLS = ((LinkedList<Estado>) LS).remove();
			f = profundidad_nodo;
			NodoArbol nodoSucesores = new NodoArbol(n_actual, estadoLS, 0 , null, Prof_Max, f);
			LN.add(nodoSucesores);
		}
		
		return LN;
	}
	
	private static void save(String fileName, String text) {
		try{
			/*PrintWriter fileOut=new PrintWriter(new FileWriter(new File(fileName)));
	    	   fileOut.print(text);
	        fileOut.close();*/
	   }catch(Exception e){}
	}
}