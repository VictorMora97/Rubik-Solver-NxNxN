import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

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
	private static String ubicacionJSON = "C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\test1.json";

		public static void main(String[] args) {
    	
	        stringCubo = LeerJSON(ubicacionJSON);    
			dimension = (int)Math.sqrt((stringCubo.length())/6);
	        String array[] = stringCubo.split("");
	            
		/* 
		 * Tras el proceso de conversion del JSON a un string, se asignan los valores a cada "mini-cubo" correspondiente.
		 * El cubo se representa con un array tridimensional --> cubo[a][b][c], siendo 'a' el ID correspondiente a cada cara,
		 * b el numero de fila dentro de esa cara, y c la columna dentro de esa filaa
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
    				
    				PintarMD5(cubo);
    				L(cubo,3,-90);
    				PintarMD5(cubo);
    				D(cubo,1,90);
    				PintarMD5(cubo);
    				L(cubo,1,-90);
    				PintarMD5(cubo);
    				D(cubo,0,-90);
    				PintarMD5(cubo);
    				B(cubo,0,90);
    				PintarMD5(cubo);
    				B(cubo,5,-90);
    				PintarMD5(cubo);
	}
		
		/*	-----------------------
		 * 	------ Fin main -------
		 * 	-----------------------
		 * 
		 * ROJO: 0             BACK: 0
		 * AZUL: 1			   DOWN: 1
		 * AMARILLO: 2		   FRONT: 2
		 * VERDE: 3            LEFT: 3
		 * NARANJA: 4		   RIGHT: 4
		 * BLANCO: 5		   UP: 5
		 * 
		 * Flag: 90(+90º) y -90(-90º)
		 */
		
		public static void L(String[][][] cubo,int numero, int flag) {
			
			if(flag==90) {
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
				if(numero==dimension-1) { //RIGHT (4) Gira
					String[][] a1 = ObtenerMatriz(cubo,4);
					String[][] a2 = RotarCara(a1);
					AsignarMatriz(cubo,a2,4);
				}
			}
			
			if(flag==-90) {
				c1 = ObtenerLinea(cubo,0,numero,-1);		
				c2 = ObtenerLinea(cubo,1,numero,-1);
				c3 = ObtenerLinea(cubo,2,numero,-1);
				c5 = InvertirLinea(c3);
				c4 = ObtenerLinea(cubo,5,(dimension-1)-numero,-1);
				c6 = InvertirLinea(c4);
				AsignarLinea(cubo,c5,5,(dimension-1)-numero,-1);
				AsignarLinea(cubo,c6,0,numero,-1);
				AsignarLinea(cubo,c1,1,numero,-1);
				AsignarLinea(cubo,c2,2,numero,-1);
				
				if (numero==0) { //LEFT (3) Gira
					for(int i=0; i<3; i++) { //Lo gira 3 veces = inversa	
						String[][] a1 = ObtenerMatriz(cubo,3);
						String[][] a2 = RotarCara(a1);
						AsignarMatriz(cubo,a2,3);
					}
				}
				if(numero==dimension-1) { //RIGHT (4) Gira
					for(int i=0; i<3; i++) {
						String[][] a1 = ObtenerMatriz(cubo,4);
						String[][] a2 = RotarCara(a1);
						AsignarMatriz(cubo,a2,4);
					}
				}
			}
		}
		
		public static void D(String[][][] cubo,int numero, int flag) {
			
			if(flag==90) {
				c1 = ObtenerLinea(cubo,0,-1,(dimension-1)-numero);
				c2 = ObtenerLinea(cubo,4,numero,-1);
				c3 = InvertirLinea(c2);
				c4 = ObtenerLinea(cubo,2,-1,numero);
				c5 = ObtenerLinea(cubo,3,(dimension-1)-numero,-1);
				c6 = InvertirLinea(c5);
				AsignarLinea(cubo,c1,4,numero,-1);
				AsignarLinea(cubo,c3,2,-1,numero);
				AsignarLinea(cubo,c4,3,(dimension-1)-numero,-1);
				AsignarLinea(cubo,c6,0,-1,(dimension-1)-numero);
				
				if (numero==0) { //DOWN (1) Gira
					String[][] a1 = ObtenerMatriz(cubo,1);
					String[][] a2 = RotarCara(a1);
					AsignarMatriz(cubo,a2,1);		
				}
				if(numero==dimension-1) { //UP (5) Gira		
					String[][] a1 = ObtenerMatriz(cubo,5);
					String[][] a2 = RotarCara(a1);
					AsignarMatriz(cubo,a2,5);	
				}
			}
			
			if(flag==-90) {
				c1 = ObtenerLinea(cubo,0,-1,(dimension-1)-numero);
				c2 = InvertirLinea(c1);
				c3 = ObtenerLinea(cubo,4,numero,-1);
				c4 = ObtenerLinea(cubo,2,-1,numero);
				c5 = InvertirLinea(c4);
				c6 = ObtenerLinea(cubo,3,(dimension-1)-numero,-1);
				AsignarLinea(cubo,c5,4,numero,-1);
				AsignarLinea(cubo,c6,2,-1,numero);
				AsignarLinea(cubo,c2,3,(dimension-1)-numero,-1);
				AsignarLinea(cubo,c3,0,-1,(dimension-1)-numero);
				
				if (numero==0) { //DOWN (1) Gira
					for(int i=0; i<3; i++) {
						String[][] a1 = ObtenerMatriz(cubo,1);
						String[][] a2 = RotarCara(a1);
						AsignarMatriz(cubo,a2,1);
					}
				}
				if(numero==dimension-1) { //UP (5) Gira
					for(int i=0; i<3; i++) {
						String[][] a1 = ObtenerMatriz(cubo,5);
						String[][] a2 = RotarCara(a1);
						AsignarMatriz(cubo,a2,5);
					}
				}
				
			}
		}
		
		public static void B(String[][][] cubo,int numero, int flag) {
			
			if(flag==90) {
				c1 = ObtenerLinea(cubo,3,-1,numero);
				c2 = ObtenerLinea(cubo,1,-1,numero);
				c3 = ObtenerLinea(cubo,4,-1,numero);
				c4 = ObtenerLinea(cubo,5,-1,numero);
				AsignarLinea(cubo,c1,1,-1,numero);
				AsignarLinea(cubo,c2,4,-1,numero);
				AsignarLinea(cubo,c3,5,-1,numero);
				AsignarLinea(cubo,c4,3,-1,numero);
	
				if (numero==0) { //BACK (0) Gira
					String[][] a1 = ObtenerMatriz(cubo,0);
					String[][] a2 = RotarCara(a1);
					AsignarMatriz(cubo,a2,0);		
				}
				if(numero==dimension-1) { //FRONT (2) Gira		
					String[][] a1 = ObtenerMatriz(cubo,2);
					String[][] a2 = RotarCara(a1);
					AsignarMatriz(cubo,a2,2);	
				}
			}
			
			if(flag==-90) {
				c1 = ObtenerLinea(cubo,3,-1,numero);
				c2 = ObtenerLinea(cubo,1,-1,numero);
				c3 = ObtenerLinea(cubo,4,-1,numero);
				c4 = ObtenerLinea(cubo,5,-1,numero);
				AsignarLinea(cubo,c1,5,-1,numero);
				AsignarLinea(cubo,c2,3,-1,numero);
				AsignarLinea(cubo,c3,1,-1,numero);
				AsignarLinea(cubo,c4,4,-1,numero);
				
				if (numero==0) { //BACK (0) Gira
					for(int i=0; i<3; i++) {
						String[][] a1 = ObtenerMatriz(cubo,0);
						String[][] a2 = RotarCara(a1);
						AsignarMatriz(cubo,a2,0);
					}
				}
				if(numero==dimension-1) { //FRONT (2) Gira	
					for(int i=0; i<3; i++) {
						String[][] a1 = ObtenerMatriz(cubo,2);
						String[][] a2 = RotarCara(a1);
						AsignarMatriz(cubo,a2,2);
					}
				}
				
			}
		}
		
		/*
		 *  --------------------------------
		 * 	----- FUNCIONES AUXILIARES -----
		 *  -------------------------------- 
		 */
		
		public static String LeerJSON(String ubicacion) {
	        JSONParser parser = new JSONParser();	        
	        try (Reader reader = new FileReader(ubicacion)) {

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
	      
	            String sucia;
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
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
			return stringCubo;
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
		
		public static String Limpiar(String str) { 
            String limpia = (((str.replaceAll(",","") ).replaceAll("\\[", "")).replaceAll("\\]", "").replaceAll(" ", ""));
            return limpia;
		}
		
		public static void PintarCubo(String[][][] cubo) {	
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
			System.out.println(input);
		}
		
		public static void PintarMD5 (String[][][] cubo) {
			
			int cont = 0;
			Vector vector = new Vector(500,50);	
			for(int a=0; a<6; a++) { 
				for(int b=0; b<dimension; b++) {
					for(int c=0; c<dimension; c++) { 					
						vector.addElement(cubo[a][c][b]);
        				cont = cont + 1;     				
        			}
        		}            				
        	}
			String sucia = vector.toString();
			String limpia = (Limpiar(sucia));
			Pintar(MD5(limpia));
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
}     			
				

		

		
		

		

