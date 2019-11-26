package dominio;

import java.util.List;
import java.util.Stack;

public class FronteraS extends Frontera {
    
    private Stack<NodoArbol> q;
    public FronteraS(){
    	q = new Stack<>();
    }
    
    public boolean insertar(NodoArbol element){return q.push(element)!=null;}
    public boolean insertar(List<NodoArbol> lista){
        boolean out = true;
        for(int i=0;i<lista.size();i++)
            out &= q.push(lista.get(i))!=null;
        return out;
    }
    public NodoArbol get(){return q.peek();}
    public NodoArbol eliminar(){return q.pop();}
    public boolean esVacia(){return q.isEmpty();}
    public int size() {return q.size();}
}
