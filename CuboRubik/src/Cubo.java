import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Cubo {
	
    private static String[][][] cubo;
    private static String[] c1,c2,c3,c4,c5,c6;
	private static int dimension;
	private static int contador = 0;
    private static String stringCubo = "";
    private static String sucia;
	private static String ubicacionJSON = "C:\\Users\\victo\\eclipse-workspace\\Sistemas-Inteligentes\\src\\cube.json";

		public static void main(String[] args) {
    	
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
	            
	            dimension = (int)Math.sqrt((stringCubo.length())/6);
	            String array[] = stringCubo.split("");
	            
	/* 
	 * Tras el proceso de conversion del JSON a un string, se asignan los valores a cada "mini-cubo" correspondiente.
	 * El cubo se representa con un array tridimensional --> cubo[a][b][c], siendo 'a' el ID correspondiente a cada cara,
	 * b el numero de fila dentro de esa cara, y c la columna dentro de esa fila
	 */
	            
	            cubo = new String[6][dimension][dimension];  
	           
    				for(int a=0; a<6; a++) { //ID de caras (BACK=0, DOWN=1...etc)
    					for(int b=0; b<dimension; b++) { //Fila
    						for(int c=0; c<dimension; c++) { //Columna
    							
		            			cubo[a][c][b]=array[contador];
	            				contador = contador + 1;
	            				//System.out.print(cubo[a][c][b]);
	            				
	            			}
	            		}            				
	            	}
    				
    				pintarCubo(cubo);
    				Pintar("\n");
    				L(cubo,0);
    				pintarCubo(cubo);
    				Pintar("\n");
    				L(cubo,0);
    				pintarCubo(cubo);
    				Pintar("\n");
    				L(cubo,0);
    				pintarCubo(cubo);
    				Pintar("\n");
    				L(cubo,0);
    				pintarCubo(cubo);


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
		
		public static void pintarCubo(String[][][] cubo) {
			
			System.out.print("\n");
			for(int a=0; a<6; a++) { 
				for(int b=0; b<dimension; b++) { 
					for(int c=0; c<dimension; c++) { 		
						System.out.print(cubo[a][c][b]);
        			}
        		}            				
        	}
		}
		
		public static void Pintar(Object input) {
			System.out.print(input);
		}
		
		public static String MD5(String input) 
	    { 
	        try { 
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            byte[] messageDigest = md.digest(input.getBytes()); 
	            BigInteger no = new BigInteger(1, messageDigest);  
	            String hashtext = no.toString(16); 
	            while (hashtext.length() < 32) { 
	                hashtext = "0" + hashtext; 
	            } 
	            return hashtext; 
	        }  
	        catch (NoSuchAlgorithmException e) { 
	            throw new RuntimeException(e); 
	        } 
	   }
		
		/* ROJO: 0             BACK: 0
		 * AZUL: 1			   DOWN: 1
		 * AMARILLO: 2		   FRONT: 2
		 * VERDE: 3            LEFT: 3
		 * NARANJA: 4		   RIGHT: 4
		 * BLANCO: 5		   UP: 5
		 * 
		 * Flag: 0(+90º) y 1(-90º)
		 */
		
		public static void L(String[][][] cubo,int numero) {
			c1 = ObtenerLinea(cubo,0,numero,-1);
			c5 = InvertirLinea(c1);		
			c2 = ObtenerLinea(cubo,1,numero,-1);
			c3 = ObtenerLinea(cubo,2,numero,-1);
			c4 = ObtenerLinea(cubo,5,(dimension-1)-numero,-1);
			c6 = InvertirLinea(c4);
			AsignarLinea(cubo,c5,5,(dimension-1)-numero,-1);
			AsignarLinea(cubo,c2,0,numero,-1);
			AsignarLinea(cubo,c3,1,numero,-1);
			AsignarLinea(cubo,c6,2,numero,-1);
			
			if (numero==0) { //LEFT (3) Gira
				String[][] a1 = ObtenerMatriz(cubo,3);
				String[][] a2 = RotarCara(a1);
				AsignarMatriz(cubo,a2,3);
			}
			if(numero==dimension) { //RIGHT (4) Gira
				String[][] a1 = ObtenerMatriz(cubo,4);
				String[][] a2 = RotarCara(a1);
				AsignarMatriz(cubo,a2,4);
			}
		}
		
		public static String[] ObtenerLinea(String[][][] cubo, int cara, int columna, int fila) { //Si columna, fila -1
			
			String aux[] = new String[dimension];
			String temp;
			if(fila == -1) { //COLUMNAS
				for(int i=0;i<dimension;i++) {
					 temp = cubo[cara][columna][i];
					 aux[i] = temp;
				}
			}
			if(columna == -1) { //FILAS
				for(int i=0;i<dimension;i++) {
					 temp = cubo[cara][i][fila];
					 aux[i] = temp;
				}
			}
			return aux;	
		}
		
		public static void AsignarLinea(String[][][] cubo, String linea[], int cara, int columna, int fila) { //Si columna, fila -1
			
			String temp;
			if(fila == -1) { //COLUMNAS
				for(int i=0;i<dimension;i++) {
					temp = linea[i];
					cubo[cara][columna][i] = temp;			 
				}
			} 
			if(columna == -1) { //FILAS
				for(int i=0;i<dimension;i++) {
					temp = linea[i];
					cubo[cara][i][fila] = temp;			 
				}
			}
		}
		
		public static String[] InvertirLinea(String linea[]) {
			
			String aux[] = new String[dimension];
			for(int i=0; i<dimension ;i++) {
				aux[i]= linea[dimension-1-i];
			}
			return aux;
		}
		
		public static String[][] ObtenerMatriz(String[][][] cubo, int cara) { 
			
			String aux[][] = new String[dimension][dimension];
			String temp;
				for(int i=0;i<dimension;i++) {
					for(int j=0;j<dimension;j++) {
						temp = cubo[cara][j][i];
						aux[i][j] = temp;
					}
				}			
			return aux;	
		}
		
		public static String[][] RotarCara(String[][] cara){

			String[][] girada = new String[dimension][dimension];
			for(int i=0,j=dimension-1; i<dimension && j>=0;i++,j--) {
				for(int z=0;z<dimension;z++) {
					girada[z][j]= cara[i][z];
				}
			}	
			return girada;
		}
		
		public static void AsignarMatriz(String[][][] cubo, String matriz[][], int cara) { 
			
			String temp; 
			for(int b=0; b<dimension; b++) { 
				for(int c=0; c<dimension; c++) { 
					temp = matriz[b][c];
					cubo[cara][c][b]=temp;
        		}
        	}            						
		}
}     			
				

		

		
		

		

