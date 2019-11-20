package dominio;


import java.util.List;

public abstract class Frontera {

	public abstract boolean insertar(NodoArbol nA);
	public abstract boolean insertarLista(List<NodoArbol> lnA);
	
	public abstract NodoArbol get();
	public abstract NodoArbol eliminar();
	
	public abstract boolean esVacia();
	
	public abstract int size();
	
}