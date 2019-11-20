package dominio;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import persistencia.Tool;

public class Cube {
	private byte[][] back;	
	private byte[][] down;
	private byte[][] front;
	private byte[][] left;
	private byte[][] right;
	private byte[][] up;
	private int n;

	public Cube(byte[][] back, byte[][] down, byte[][] front, byte[][] left, byte[][] right, byte[][] up){
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
		byte[] actual, siguiente;
		
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
		byte[] actual, siguiente;
		
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
		byte[] actual, siguiente;
		
		actual = front[i];
		siguiente =invertir(getCol(left, n-1-i));
		setCol(left, n-1-i, actual);
	
		actual = siguiente;
		siguiente =back[n-1-i];
		back[n-1-i] = actual;
					
		actual = siguiente;
		siguiente = invertir(getCol(right, i)); 
		setCol(right, i, actual);
	
		actual = siguiente;
		front[i] = actual;
					
		if(i==0)
			girar(down,true);
		else if(i == n-1)
			girar(up,true);
		
	}
	public void d(int i){
		byte[] actual, siguiente;
		
		actual = invertir(front[i]);
		siguiente = getCol(right, i);
		setCol(right, i, actual);

		actual = siguiente;
		siguiente = invertir(back[n-1-i]);
		back[n-1-i] = actual;
					
		actual = siguiente;
		siguiente = getCol(left, n-1-i); 
		setCol(left, n-1-i, actual);

		actual = siguiente;
		front[i] = actual;
					
		if(i==0)
			girar(down,false);
		else if(i == n-1)
			girar(up,false);
	}
	public void L(int i){
		byte[] actual, siguiente;
		
		actual = front[i];
		siguiente = getCol(down, i);
		setCol(down, i, actual);

		actual = siguiente;
		siguiente = invertir(getCol(back, i));
		setCol(back, i, actual);
					
		actual = siguiente;
		siguiente = invertir( getCol(up, n-1-i)); 
		setCol(up, n-1-i, actual);

		actual = siguiente;
		front[i] = actual;
					
		if(i==0)
			girar(left,true);
		else if(i == n-1)
			girar(right,true);

	}
	public void l(int i){
		byte[] actual, siguiente;
		
		
		actual = invertir(getCol(front,i));
		siguiente =invertir(getCol(up, n-1-i));
		setCol(up, n-1-i, actual);

		actual = siguiente;
		siguiente = getCol(back, i);
		setCol(back, i, actual);
					
		actual = siguiente;
		siguiente = getCol(down, i); 
		setCol(down, i, actual);

		actual = siguiente;
		setCol(front, i, actual);
					
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
		+"up"+print(up)+"\n"
		+getId()+"\n";
		
	}
	public Cube clone() {
		return new Cube(clone(back),clone(down),clone(front),clone(left),clone(right),clone(up));
	}
	
	private void girar(byte[][] matrix, boolean right){
		byte[][] aux = clone(matrix);
		if(right)
			for(int i=0,j=matrix.length-1 ; j>-1; i++,j--)
				setCol(matrix, j, aux[i]);
		else
			for(int i=0; i<matrix.length;i++)
				setCol(matrix, i, invertir(aux[i]));
		
	}
	private byte[] invertir(byte[] matrix){
		byte[] result = new byte[matrix.length];
		for(int i=0,j=matrix.length-1;i<matrix.length;i++,j--)
			result[i] = matrix[j];
		return result;
	}
	private byte[] getCol(byte[][] matrix, int i) {
		byte[] result = new byte[matrix.length];
		for(int j = 0;j<matrix.length;j++)
			result[j] = matrix[j][i] ;
		return result;
	}
	private void setCol(byte[][] matrix, int i, byte[] col ) {
		for(int j = 0;j<matrix.length;j++)
			matrix[j][i] = col[j];
		
	}
	private String print(byte[][] matrix){
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
	private byte[][] clone(byte[][] matrix){
		byte[][] result = new byte[matrix.length][matrix.length];
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

	public byte[][] getBack() {
		return back;
	}
	public byte[][] getDown() {
		return down;
	}
	public byte[][] getLeft() {
		return left;
	}
	public byte[][] getRight() {
		return right;
	}
	public byte[][] getFront() {
		return front;
	}
	public byte[][] getUp() {
		return up;
	}

}