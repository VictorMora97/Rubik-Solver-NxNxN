package dominio;


import java.util.List;
import java.util.PriorityQueue;


public class FronteraOrdenada extends Frontera {

	private PriorityQueue<NodoArbol> colaFrontera;
	
	public FronteraOrdenada(){
		colaFrontera= new PriorityQueue<NodoArbol>();
	}

	public boolean insertar(NodoArbol nA) {	
		return colaFrontera.offer(nA);
	}
	public boolean insertar(List<NodoArbol> lista){
        boolean out = true;
        for(int i=0;i<lista.size();i++)
            out &= insertar(lista.get(i));
        return out;
    }
	public NodoArbol get(){return colaFrontera.peek();}

	public NodoArbol eliminar() {
		return colaFrontera.poll();
	}


	public boolean esVacia() {
		return colaFrontera.isEmpty();
	}

	public int size() {return colaFrontera.size();}

	
}
