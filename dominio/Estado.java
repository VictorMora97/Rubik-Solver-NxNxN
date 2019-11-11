package dominio;


public class Estado {
	private Cube cubo;
	
	//Estas variables no tienen xq estar aqui pero las he añadido para evitar crear una clase nueva para los sucesores
	private String acci;
	private int costAcci;
 
	public Estado(String acci,Cube cubo,int costAcci) {
		this.cubo=cubo;
		this.acci = acci;
		this.costAcci = costAcci;
	}
	
	public Cube getCube() {
		return cubo;
	}
	
	
	public String getAcci() {
		return acci;
	}

	public int getCostAcci() {
		return costAcci;
	}

	public boolean equals(Object obj){
        Estado e = (Estado)obj;
        return cubo.getId().equals( e.getCube().getId() );
    }
	public String toString() {
		return acci+"\n"+cubo.toString();
	}
	
}
