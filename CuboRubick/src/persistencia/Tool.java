package persistencia;

import java.util.Random;

public class Tool {

		public static void Pintar(Object input) {
			System.out.println(input);
		}
		public static int getRandomNumberInRange(int min, int max) {
			return new Random().nextInt((max - min) + 1) + min;
		}

}