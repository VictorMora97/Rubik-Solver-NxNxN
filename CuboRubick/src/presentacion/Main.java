package presentacion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import dominio.Cube;
import dominio.EspacioEstados;
import dominio.Estado;
import dominio.Problema;

public class Main {
	
	public static void main(String[] args) throws Exception {
		EspacioEstados ee;
		Cube cube,aux;
		Problema prob;
		
		prob = new Problema("ejemplo.json");
		

		
		private static void llamarArchivo (Problema prob,String estrategia,int Prof_Max,int Inc_Prof) throws Exception {
			 boolean sol = p.busqueda(estrategia, Prof_Max, Inc_Prof);
			 
		
			if(!sol)
				System.out.println("No hay solucion");
		}
		/*try {
			
			ee = new EspacioEstados("ejemplo.json");
			Estado estadoInicial = ee.getEstadoInicial();
			cube = estadoInicial.getCube();
			String result = cube.toString();
			
			aux = cube.clone(); aux.l(3); result += "l3\n"+aux.toString();
			cube = aux;
			
			aux = cube.clone(); aux.D(1); result += "D1\n"+aux.toString();
			cube = aux;
			
			aux = cube.clone(); aux.l(1); result += "l1\n"+aux.toString();
			cube = aux;
			
			aux = cube.clone(); aux.d(0); result += "d0\n"+aux.toString();
			cube = aux;
			
			aux = cube.clone(); aux.B(0); result += "B0\n"+aux.toString();
			cube = aux;
			
			aux = cube.clone(); aux.b(5); result += "b5\n"+aux.toString();
			cube = aux;
			
			aux = cube.clone(); aux.l(2); result += "l2\n"+aux.toString();
			cube = aux;
			
			aux = cube.clone(); aux.d(1); result += "d1\n"+aux.toString();
			
			
			System.out.println(result);
			save("rubick.out",result);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		*/
		
		/*short[][] back = {{1,2,3},{0,0,0},{0,0,0}};	
		short[][] down = {{10,10,10},{11,11,11},{12,12,12}};	
		short[][] front = {{20,20,20},{21,21,21},{22,22,22}};	
		short[][] left = {{30,30,30},{31,31,31},{32,32,32}};	
		short[][] right = {{40,40,40},{41,41,41},{42,42,42}};	
		short[][] up = {{501,502,503},{51,51,51},{52,52,52}};
		
		
		try {
			
			ee = new EspacioEstados("ejemplo.json");
			Estado estadoInicial = ee.getEstadoInicial();
			
			String result=estadoInicial.toString();
			
		 for(Estado e : ee.sucesores(estadoInicial))
				result += e.toString();
			System.out.println(result);
			save("rubick.out",result);
			
		
		} catch (FileNotFoundException e1) {e1.printStackTrace();}
		*/

	}
	private static void save(String fileName, String text) {
		try{
			PrintWriter fileOut=new PrintWriter(new FileWriter(new File(fileName)));
	    	   fileOut.print(text);
	        fileOut.close();
	   }catch(Exception e){}
	}

}
