package persistencia;

import java.util.Random;

public class Tool {

		public static void Pintar(Object input) {
			System.out.println(input);
		}
		public static int getRandomNumberInRange(int min, int max) {
			Random r = new Random();
			return r.nextInt((max - min) + 1) + min;
		}

}
