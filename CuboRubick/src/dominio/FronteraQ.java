package dominio;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class FronteraQ extends Frontera {
    
    private LinkedBlockingQueue<NodoArbol> q;
    public FronteraQ(){
    	q = new LinkedBlockingQueue<>();
    }
    
    public boolean insertar(NodoArbol element){return q.offer(element);}
    public boolean insertar(List<NodoArbol> lista){
        boolean out = true;
        for(int i=0;i<lista.size();i++)
            out &= q.offer(lista.get(i));
        return out;
    }

    public NodoArbol get(){return q.peek();}

    public NodoArbol eliminar(){return q.poll();}

    public boolean esVacia(){return q.isEmpty();}
    public int size() {return q.size();}
}