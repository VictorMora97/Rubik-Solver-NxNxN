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
			
			int dimension;
			int contador = 0;
            String stringCubo = "";
            String sucia;
            String ubicacionJSON = "C:\\Users\\victo\\eclipse-workspace\\Sistemas-Inteligentes\\src\\cube.json";

	        JSONParser parser = new JSONParser();
	        
	        try (Reader reader = new FileReader(ubicacionJSON)) {

	            JSONObject jsonObject = (JSONObject) parser.parse(reader);

	            JSONArray back = (JSONArray) jsonObject.get("BACK"); //0
	            System.out.println(back);
	            
	            JSONArray down = (JSONArray) jsonObject.get("DOWN"); //1
	            System.out.println(down);
	            
	            JSONArray front = (JSONArray) jsonObject.get("FRONT"); //2
	            System.out.println(front);
	            
	            JSONArray left = (JSONArray) jsonObject.get("LEFT"); //3
	            System.out.println(left);
	            
	            JSONArray right = (JSONArray) jsonObject.get("RIGHT"); //4
	            System.out.println(right);

	            JSONArray up = (JSONArray) jsonObject.get("UP"); //5
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
	            
	            dimension = (stringCubo.length())/18;
	            String array[] = stringCubo.split("");
	            
	/* 
	 * Tras el proceso de conversion del JSON a un string, se asignan los valores a cada "mini-cubo" correspondiente.
	 * El cubo se representa con un array tridimensional --> cubo[a][b][c], siendo 'a' el ID correspondiente a cada cara,
	 * b el numero de fila dentro de esa cara, y c la columna dentro de esa fila
	 */
	            
	            String[][][] cubo = new String[6][dimension][dimension];  
	           
    				for(int a=0; a<6; a++) { //ID de caras (BACK=0, DOWN=1...etc)
    					for(int b=0; b<dimension; b++) { //Fila
    						for(int c=0; c<dimension; c++) { //Columna
    							
		            			cubo[a][b][c]=array[contador];
	            				contador = contador + 1;
	            				
	            			}
	            		}            				
	            	}	
    				
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
