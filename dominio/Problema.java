package dominio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

import dominio.Estado;
import dominio.NodoArbol;
import persistencia.Tool;

public class Problema {
	
	private EspacioEstados espacioEstados;
	private Estado estadoInicial;
	private NodoArbol solucion;
	private static int contadorID = 0;
	private static String ubicacion = "C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\pruebas.json";
	private static int cnt_creado;
	private int cnt_frontera;
	private int cnt_abierto;

    private static List<NodoArbol> listaPoda= new LinkedList<>();

    public static void main(String[] args) throws Exception {
    	
        Problema prob = new Problema(ubicacion);	
        prob.busqueda("profundidad acotada",6,1);
        System.out.println("Nodos -> creados:"+cnt_creado+", frontera:"+prob.cnt_frontera+", abiertos:"+prob.cnt_abierto);
        	
        }
	
    public NodoArbol getSolucion() {
		return solucion;
	}
	
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
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    		Tool.Pintar(solucion.getEstado());
	    		Tool.Pintar(solucion);
	    	case "costo uniforme":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    	case "profundidad acotada":
	    		solucion = busquedaAcotada(estrategia,Prof_Max);
	    		Tool.Pintar(solucion.getEstado());
	    		Tool.Pintar(solucion);
	    	case "voraz":
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
    		save("rubick.out",solucion.toString());
    		return true;
    	}
	  return false;
    }
	

	private NodoArbol busquedaAcotada(String estrategia,int Prof_Max) {
		//*****************************
		//System.out.println("Busqueda Acotada: "+estrategia+", ProfMax:"+Prof_Max+", EstadoInicial:"+((estadoInicial!=null)?estadoInicial.toString():"null"));
		//*****************************
		
        boolean solucion = false;
        NodoArbol n_actual = null;
        
        if(estadoInicial != null){     
        	//Frontera depende de la estrategia
        	Frontera frontera = new FronteraOrdenada(); 
        	cnt_creado = 1;
        	cnt_frontera = cnt_abierto = 0;
        	
            //Proceso de inicializacion  
        	n_actual = new NodoArbol(null, estadoInicial, "",0, 0, 0);
            frontera.insertar(n_actual);
            cnt_frontera++;
            
            //Bucle de busqueda
            while(!solucion && !frontera.esVacia()){
                n_actual = frontera.eliminar();
                cnt_abierto++;
                
                if(esObjetivo(n_actual.getEstado())) {
                    solucion = true;
                	//Tool.Pintar(n_actual);
                }    
                else
                    try{
                        List<Estado> LS = espacioEstados.sucesores(n_actual.getEstado());
                        List<NodoArbol> LN = ListaNodosArbol(LS,n_actual,Prof_Max,estrategia);
                        cnt_frontera += LN.size();
                        frontera.insertarLista(LN);
                    }catch(Exception e){ System.out.println("Problema ln83 "+e);
                }    
            }
        }
        //*************
    //    System.out.println("Nodos -> creados:"+cnt_creado+", frontera:"+cnt_frontera+", abiertos:"+cnt_abierto);
        //*************
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
		for(int i=0; i< matrix.length;i++) {
			for(int j=0; j< matrix.length;j++) {
				if(matrix[i][j] != aux ) { 
					return false;}
			}
		}
		
		return true;
	}
    

	private static List<NodoArbol> ListaNodosArbol(List<Estado> LS, NodoArbol n_padre,int Prof_Max, String estrategia){
		List<NodoArbol> result = new LinkedList<>();
		
		for(Estado estadoActual : LS) {
			//double f = calcularF(n_padre,estadoActual,estrategia);	
			
			//si es inferior a la profundidad maxima
			if(n_padre.getD() < Prof_Max) {
				double f = calcularF(n_padre,estadoActual,estrategia);
				NodoArbol nodo = new NodoArbol(n_padre, estadoActual,estadoActual.getAcci() ,n_padre.getCoste()+1, n_padre.getD()+1,f);
				//contadorID++;
				//nodo.setId_nodo(contadorID);
				nodo.setCoste( n_padre.getD()+1);
				cnt_creado++;
				nodo.setId_nodo(cnt_creado);
				//if(!poda(nodo))
					result.add(nodo);
			}
		}
		return result;
	}

	private static double calcularF(NodoArbol padre,Estado estado,String estrategia){
        double g=0, h=0;
           
        switch(estrategia){
            case "anchura":
                g = padre.getD()+1; //profundidad positiva
            break;
            case "costo uniforme":
            	g = padre.getCoste()+1;  //coste positivo
            break;  
            
            
            case "profundidad acotada":
            	g = 1/(padre.getD()+1);
            	
            break;	
            case "profundidad iterativa":
                g = 1/(padre.getD()+1); // profundidad negativa
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
	}
	
	
	
	
	
	private static void save(String fileName, String text) {
		try{
			PrintWriter fileOut=new PrintWriter(new FileWriter(new File(fileName)));
	    	   fileOut.print(text);
	        fileOut.close();
	   }catch(Exception e){}
	}

}