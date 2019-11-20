package dominio;

public class NodoArbol implements Comparable<Object> {

	private NodoArbol padre;
	private Estado est;
	private double coste;
	private String accion = "None";
	private double d;
	private double f;

	
	public NodoArbol(){
		
	}
	
	public NodoArbol(NodoArbol padre, Estado est, double coste,String accion, double d, double f){
		this.padre = padre;
		this.est = est;
		this.coste = coste;
		this.accion = accion;
		this.d = d;
		this.f = f;
	}

	public NodoArbol getPadre() {
		return padre;
	}

	public void setPadre(NodoArbol padre) {
		this.padre = padre;
	}

	public Estado getEstado() {
		return est;
	}

	public void setEstado(Estado est) {
		this.est = est;
	}

	public double getCoste() {
		return coste;
	}

	public void setCoste(double coste) {
		this.coste = coste;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public double getD() {
		return d;
	}

	public void setD(double d) {
		this.d = d;
	}

	public double getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}
	public boolean equals(Object t){
        //comparo los vertices de lestado actual y el introducido
        return est.equals( ((NodoArbol)t).getEstado() );
    }
	@Override
	public int compareTo(Object na){
		NodoArbol nodo= ((NodoArbol)na);
        double valor = nodo.getF();

        if(this.f == valor) return 0;
        else if(this.f < valor) return -1;
        else return 1;
    }
	public String toString() {
		return "f: "+f;//"accion: "+accion+" coste: "+coste+" profudidad: "+d+" f(): "+f+"\n"+est.toString()+"\n"; 
	}
	
}