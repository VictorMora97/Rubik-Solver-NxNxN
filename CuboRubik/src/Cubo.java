import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Cubo {

		public static void main(String[] args) {
			
            String stringCubo = "";
            String sucia;
            String ubicacionJSON = "C:\\Users\\victo\\eclipse-workspace\\Sistemas-Inteligentes\\src\\cube.json";

	        JSONParser parser = new JSONParser();
	        
	        try (Reader reader = new FileReader(ubicacionJSON)) {

	            JSONObject jsonObject = (JSONObject) parser.parse(reader);

	            JSONArray back = (JSONArray) jsonObject.get("BACK");
	            System.out.println(back);
	            
	            JSONArray down = (JSONArray) jsonObject.get("DOWN");
	            System.out.println(down);
	            
	            JSONArray front = (JSONArray) jsonObject.get("FRONT");
	            System.out.println(front);
	            
	            JSONArray left = (JSONArray) jsonObject.get("LEFT");
	            System.out.println(left);
	            
	            JSONArray right = (JSONArray) jsonObject.get("RIGHT");
	            System.out.println(right);

	            JSONArray up = (JSONArray) jsonObject.get("UP");
	            System.out.println(up);                        
	      
	            for(int i=0; i<back.size(); i++){    	
	                sucia = (back.get(i)).toString();
	                stringCubo = stringCubo + Limpiar(sucia);
	            }   
	            
	            for(int i=0; i<down.size(); i++){    	
	                sucia = (down.get(i)).toString();
	                stringCubo = stringCubo + Limpiar(sucia);
	            }
	            
	            for(int i=0; i<front.size(); i++){    	
	                sucia = (front.get(i)).toString();
	                stringCubo = stringCubo + Limpiar(sucia);
	            }
	            
	            for(int i=0; i<left.size(); i++){    	
	                sucia = (left.get(i)).toString();
	                stringCubo = stringCubo + Limpiar(sucia);
	            }
	            
	            for(int i=0; i<right.size(); i++){    	
	                sucia = (right.get(i)).toString();
	                stringCubo = stringCubo + Limpiar(sucia);
	            }
	            
	            for(int i=0; i<up.size(); i++){    	
	                sucia = (up.get(i)).toString();
	                stringCubo = stringCubo + Limpiar(sucia);
	            }
	            
	            System.out.println(stringCubo);

	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }

	}

		public static String Limpiar(String str) {
            String limpia = ((str.replaceAll(",","") ).replaceAll("\\[", "")).replaceAll("\\]", "");
            return limpia;
		}

}
