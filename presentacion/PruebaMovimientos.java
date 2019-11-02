package presentacion;

import java.io.FileNotFoundException;

import dominio.Cube;
import dominio.EspacioEstados;
import dominio.Estado;
import persistencia.Tool;

public class PruebaMovimientos {
	
	public static void main(String[] args) throws FileNotFoundException  {
		EspacioEstados ee;
		String ubicacion = "C:\\Users\\victo\\OneDrive\\Desktop\\Sistemas Inteligentes\\JSON\\test1.json";
		
			
			ee = new EspacioEstados(ubicacion);
			Estado estadoInicial = ee.getEstadoInicial();
			Cube cube = estadoInicial.getCube();
	    	
			Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"69db38e2ce96d3044adc00e612a810b0  \n");  
	    	cube.l(3);
	    	Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"130d0d212b8cc15f375b1b0f2cdf42ad  \n");
	    	cube.D(1);
	    	Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"d83b0f604f0fbdd646497bcc400cb701 \n");
	    	cube.l(1);
	    	Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"3072cd153434334e62487aa2c52d0b1c  \n");
	    	cube.d(0);
	    	Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"dab05f73e4ed15c2394f1712f9dc4fca  \n");
	    	cube.B(0);
	    	Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"ff8a8cd7a7af5da72edfad5d0a801a97  \n");
	    	cube.b(5);
	    	Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"8aef8f1a6b6d427fb55581dee01e2557  \n");
	    	cube.l(2);
	    	Tool.Pintar(cube.makeId()+"  <-- ¿es igual? -->  "+"151faa80eb7b01fa8db7e8129778de10  \n");
	    	cube.d(1);
	    	Tool.Pintar(cube.makeId()); Tool.Pintar("  <-- ¿es igual? -->  "); Tool.Pintar("e8682bbb2e6fabf5971e4b471ae2d46d  \n");
			
	}

}
