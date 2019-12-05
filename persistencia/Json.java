package persistencia;
import org.json.JSONArray;
import org.json.JSONML;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import dominio.EspacioEstados;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressWarnings("deprecation")
public class Json {
	
	public Json() {}
	private static List<String> Nodos = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public EspacioEstados leer(String archivo) {		
	
		JSONParser parser = new JSONParser();
		try{
			
			Object obj = parser.parse(new FileReader(archivo));				
			JSONObject jsonObject = (JSONObject) obj;
			String grafo = (String) jsonObject.get("Cube");		
			
			JSONObject JsonEstado =  (JSONObject) jsonObject.get("IntSt");			
			String NodoInicial = (String) JsonEstado.get("node");
			
		
			JSONArray lista= (JSONArray) JsonEstado.get("listNodes");
			Iterator<Object> iterator =lista.iterator();
			while (iterator.hasNext()) {
				Nodos.add((String) iterator.next());
			}						
		}catch(Exception ex){
			System.err.println("Error: "+ex.toString());
		}
		return null;
}
}


