package up.mi.cm.sg;
import java.util.Scanner;

public class IO {
	/**
	 * On demande un entier et on vérifie s’il a correctement été entre sinon on le re demande
	 * @param sc Objet scanner que on recouper pour la lecture 
	 * @param message Un message que on veux afficher avant de demander un entier 
	 * @return tmp Donne un entier correcte 
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
	 * On fais une fonctions qui s’assure que la chaine de carter que l’on demande et correct
	 * @param sc Objet scanner que on recouper pour la lecture 
	 * @param message Un message que on veux afficher avant de demander la chaine de carter
	 * @return s Donne une chaine de caractère correcte 
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
