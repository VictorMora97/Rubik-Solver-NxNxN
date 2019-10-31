package dominio;

import java.io.FileNotFoundException;

public class Problema {
	
	private EspacioEstados espacioEstados;
	private Estado estadoInicial;
	private Frontera frontera;
	
	
	public Problema(String ruta) throws FileNotFoundException {
		espacioEstados = new EspacioEstados(ruta);
		estadoInicial = espacioEstados.getEstadoInicial();
	}
	
	public boolean esObjetivo(Estado e) {return false;}

}
