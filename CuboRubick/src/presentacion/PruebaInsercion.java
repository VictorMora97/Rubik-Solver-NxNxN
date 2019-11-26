package presentacion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import dominio.Cube;
import dominio.EspacioEstados;
import dominio.Estado;

public class PruebaInsercion {
	
	public static void main(String[] args) {
		
		/*short[][] back = {{1,2,3},{0,0,0},{0,0,0}};	
		short[][] down = {{10,10,10},{11,11,11},{12,12,12}};	
		short[][] front = {{20,20,20},{21,21,21},{22,22,22}};	
		short[][] left = {{30,30,30},{31,31,31},{32,32,32}};	
		short[][] right = {{40,40,40},{41,41,41},{42,42,42}};	
		short[][] up = {{501,502,503},{51,51,51},{52,52,52}};*/
		
		EspacioEstados ee;
		try {
			
			ee = new EspacioEstados("C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\test1.json");
			Estado estadoInicial = ee.getEstadoInicial();
			
			String result=estadoInicial.toString();
			for(Estado e : ee.sucesores(estadoInicial))
				result += e.toString();
			System.out.println(result);
			save("rubick.out",result);
			
			
		} catch (FileNotFoundException e1) {e1.printStackTrace();}
		

	}
	private static void save(String fileName, String text) {
		try{
			PrintWriter fileOut=new PrintWriter(new FileWriter(new File(fileName)));
	    	   fileOut.print(text);
	        fileOut.close();
	   }catch(Exception e){}
	}

}