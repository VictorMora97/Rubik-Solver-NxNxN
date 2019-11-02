package dominio;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Cube {
	private short[][] back;	
	private short[][] down;
	private short[][] front;
	private short[][] left;
	private short[][] right;
	private short[][] up;
	private int n;

	public Cube(short[][] back, short[][] down, short[][] front, short[][] left, short[][] right, short[][] up){
		this.back = back;	
		this.down = down;
		this.front = front;
		this.left = left;
		this.right = right;
		this.up = up;
		
		this.n = up[0].length;
	}

	public int getN() {
		return n;
	}	
	public String getId() {
		return makeId();
	}
	
	public void B(int i){
		short[] actual, siguiente;
		
		actual = up[i];
		siguiente = left[i];
		left[i] = actual;

		actual = siguiente;
		siguiente = down[i];
		down[i] = actual;
					
		actual = siguiente;
		siguiente = right[i]; 
		right[i] = actual;

		actual = siguiente;
		up[i] = actual;
					
		if(i==0)
			girar(back,true);
		else if(i == n-1)
			girar(front,true);

	}
	public void b(int i){
		short[] actual, siguiente;
		
		actual = left[i];
		siguiente = up[i];
		up[i] = actual;

		actual = siguiente;
		siguiente = right[i];
		right[i] = actual;
					
		actual = siguiente;
		siguiente = down[i]; 
		down[i] = actual;

		actual = siguiente;
		left[i] = actual;
					
		if(i==0)
			girar(back,false);
		else if(i == n-1)
			girar(front,false);
	}
	public void D(int i){
		short[] actual, siguiente;
		
		actual = front[i];
		siguiente =invertir(left[i]);
		left[i] = actual;

		actual = siguiente;
		siguiente = invertir(back[i]);
		back[i] = actual;
					
		actual = siguiente;
		siguiente = invertir(right[i]); 
		right[i] = actual;

		actual = siguiente;
		front[i] = actual;
					
		if(i==0)
			girar(down,true);
		else if(i == n-1)
			girar(up,true);
		
	}
	public void d(int i){
		short[] actual, siguiente;
		
		actual = invertir(front[i]);
		siguiente = right[i];
		right[i] = actual;

		actual = siguiente;
		siguiente = invertir(back[i]);
		back[i] = actual;
					
		actual = siguiente;
		siguiente = left[i]; 
		left[i] = actual;

		actual = siguiente;
		front[i] = actual;
					
		if(i==0)
			girar(down,false);
		else if(i == n-1)
			girar(up,false);
	}
	public void L(int i){
		short[] actual, siguiente;
		
		actual = front[i];
		siguiente = down[i];
		down[i] = actual;

		actual = siguiente;
		siguiente = back[i];
		back[i] = actual;
					
		actual = siguiente;
		siguiente = up[i]; 
		up[i] = actual;

		actual = invertir(siguiente);
		front[i] = actual;
					
		if(i==0)
			girar(left,true);
		else if(i == n-1)
			girar(right,true);

	}
	public void l(int i){
		short[] actual, siguiente;
		
		actual = invertir(front[i]);
		siguiente =invertir( up[i]);
		up[i] = actual;

		actual = siguiente;
		siguiente = back[i];
		back[i] = actual;
					
		actual = siguiente;
		siguiente = down[i]; 
		down[i] = actual;

		actual = siguiente;
		front[i] = actual;
					
		if(i==0)
			girar(left,false);
		else if(i == n-1)
			girar(right,false);
	}
			
	public String toString(){
		
		return "back"+print(back)+"\n"	
		+"down"+print(down)+"\n"
		+"front"+print(front)+"\n"	
		+"left"+print(left)+"\n"	
		+"right"+print(right)+"\n"	
		+"up"+print(up)+"\n";
		
	}
	public Cube clone() {
		return new Cube(clone(back),clone(down),clone(front),clone(left),clone(right),clone(up));
	}
	
	private void girar(short[][] matrix, boolean right){
		short[][] aux = clone(matrix);
		if(right)
			for(int i=0,j=matrix.length-1 ; j>-1; i++,j--)
				setCol(matrix, j, aux[i]);
		else
			for(int i=0; i<matrix.length;i++)
				setCol(matrix, i, invertir(aux[i]));
		
	}
	private short[] invertir(short[] matrix){
		short[] result = new short[matrix.length];
		for(int i=0,j=matrix.length-1;i<matrix.length;i++,j--)
			result[i] = matrix[j];
		return result;
	}
	private void setCol(short[][] matrix, int i, short[] col ) {
		for(int j = 0;j<matrix.length;j++)
			matrix[j][i] = col[j];
		
	}
	private String print(short[][] matrix){
		String result ="[";
		for(int i=0;i<matrix.length;i++) {
			result+="[";
			for(int j=0;j<matrix.length;j++) {
				result+=" "+matrix[i][j];
			}
			result+="]";
		}
		return result +"]";
	}
	private short[][] clone(short[][] matrix){
		short[][] result = new short[matrix.length][matrix.length];
		for(int i=0;i<matrix.length;i++)
			result[i] = matrix[i].clone();
		return result;
	}
	public String makeId(){
		String sback="", sdown="", sfront ="", sleft="", sright="", sup="";
		int n = back[0].length;
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) {
				sback += back[i][j];
				sdown += down[i][j];
				sfront += front[i][j];
				sleft += left[i][j];
				sright += right[i][j];
				sup += up[i][j];
		}
		return md5(sback+sdown+sfront+sleft+sright+sup);
				
	}
	private static String md5(String password){
        
	        try { 
	            MessageDigest md = MessageDigest.getInstance("MD5");  
	            byte[] messageDigest = md.digest(password.getBytes()); 
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
