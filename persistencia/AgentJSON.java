package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dominio.Cube;

public class AgentJSON {
	

	public static Cube getCube(String ruta) throws FileNotFoundException {
		
		short[][] back, down, front, left,right,up;

		String file = readFile(ruta);
		JsonParser parser = new JsonParser();
	    JsonObject obj = parser.parse(file).getAsJsonObject();
	    
	    back = getMatrix(obj,"BACK");
	    down = getMatrix(obj,"DOWN");
	    front = getMatrix(obj,"FRONT");
	    left = getMatrix(obj,"LEFT");
	    right = getMatrix(obj,"RIGHT");
	    up = getMatrix(obj,"UP");
	    
	    return new Cube(back,down,front,left,right,up);

	}
	private static String readFile(String ruta) throws FileNotFoundException {
		String result ="";
		Scanner scanner = new Scanner(new File(ruta));
		while(scanner.hasNext())
			result +=scanner.nextLine();
		scanner.close();
		return result;
	}
	private static short[][] getMatrix(JsonObject obj,String name){
		List<List<Short>> lsI;
	    List<Short> lsJ;
	    JsonArray matrix, submatrix;
	    
		matrix = obj.get(name).getAsJsonArray();
	    lsI = new LinkedList<>();
	    for (JsonElement element : matrix) {
	    	submatrix = element.getAsJsonArray();
	    	lsJ = new LinkedList<>();
	    	for(JsonElement subElement : submatrix)
	    		lsJ.add(subElement.getAsShort());
	    		
	    	lsI.add(lsJ);
	    }
	    return toMatrix(lsI);
		
	}
	private static short[][] toMatrix(List<List<Short>> ls){
		int n = ls.size();
		short[][] result = new short[n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				result[i][j] = ls.get(i).get(j);
		return result;
	}

}
