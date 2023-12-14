package up.mi.cm.sg;
import java.util.Scanner;

public class IO {
	/**
	 * 
	 * @param sc
	 * @param message
	 * @return
	 */
	public static int SaisieInt(Scanner sc, String message) {
		int tmp = 0;
		boolean mauvaiseSaisie = false;
		do {
			mauvaiseSaisie = false;
			try {
				System.out.println(message);
				tmp = sc.nextInt();
			}catch(Exception e) {
				mauvaiseSaisie = true;
				sc.next();
			}
		}while(mauvaiseSaisie);
		return tmp;
	}
	/**
	 * 
	 * @param sc
	 * @param message
	 * @return
	 */
	public static String SaisieString(Scanner sc, String message) {
		String s = "";
		boolean mauvaiseSaisie = false;
		do {
			try {
				System.out.println(message);
				s = sc.next();
				mauvaiseSaisie = false;
			}catch(Exception e) {
				mauvaiseSaisie = true;
			}
		}while(mauvaiseSaisie);
		return s;
	}
}
