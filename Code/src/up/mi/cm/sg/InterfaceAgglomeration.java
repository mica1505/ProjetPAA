package up.mi.cm.sg;
import java.util.Scanner;

/***
 * Represente le menu de demarrage et initialisation de l'agglomeration
 * @author 
 * @version 1
 *
 */
public class InterfaceAgglomeration {
	/***
	 * Fonction pour Creation de l'agglomeration
	 * @param args
	 */
	public static void main(String [] args) {
		initAgglomeration(args[0]);
	}
	/***
	 * 
	 * @return
	 */
	public static CA readAgglomeration(String path) {
		return ParseAgglomeration.parseAgg(path);
	}
	/**
	 * 
	 */
	public static void initAgglomeration(String path) {
		Scanner sc = new Scanner(System.in);

		
		CA agg = readAgglomeration(path);
		
		menu1(sc,agg);
		menuHumanOrComputer(sc,agg);
		sc.close();
		System.out.print(agg);
	}
	/***
	 * 
	 * @param sc
	 * @param agg
	 */
	public static void menu1(Scanner sc,CA agg) {
		boolean quit = false;
	
		while(!quit) {
			String choice  = IO.SaisieString(sc, "\n1) Ajouter une route.\n2) Fin.");
			
			switch(choice) {
				case "1":
					agg.initRoads(sc);
					break;
				case "2":
					quit=true;
					break;
			}
		}
	}
	/**
	 * 
	 * @param sc
	 * @param agg
	 */
	public static void menuHumanOrComputer(Scanner sc, CA agg) {
		boolean quit = false;
		while(!quit) {
			String choiceB = IO.SaisieString(sc, "\nComment veut tu resoudre se le probleme en tans que hummain (H) ou ordinateur (O) : ");
			if(choiceB.equals("H") || choiceB.equals("h")) {
				menuHuman(sc, agg);
				quit = true;
			}
			else if(choiceB.equals("O") || choiceB.equals("o")) {
				menuComputer(sc, agg);
				quit = true;
			}
		}
	}
	/**
	 * 
	 * @param sc
	 * @param agg
	 */
	public static void menuHuman(Scanner sc, CA agg) {
		boolean quit = false;
		while(!quit) {
			int choice = IO.SaisieInt(sc, "\n1) Ajouter une borne.\n2) Supprimer une borne.\n3) Fin.");
			if(choice == 1) {
				agg.addZoneUser(sc);
			}
			else if(choice == 2) {
				agg.removeZoneUser(sc);
			}
			else if(choice == 3){
				quit = true;
			}
		}
	}
	/**
	 * 
	 * @param sc
	 * @param agg
	 */
	public static void menuComputer(Scanner sc, CA agg) {
		boolean quit = false;
		int k;
		while(!quit) {
			int choice = IO.SaisieInt(sc, "\n1) Premier algorithme naïf.\n2) Second une borne\n3) Algorithme glouton.");
			switch(choice){
				case 1:
					k = IO.SaisieInt(sc, "\nLe nombre de itetrations de la simulations.");
					agg.naiveSolutions(k);
					quit = true;
					break;
				case 2:
					k = IO.SaisieInt(sc, "\nLe nombre de itetrations de la simulations.");
					agg.naiveSolutions2(k);
					quit = true;
					break;
				case 3:
					agg.algorithmeGlouton();
					quit = true;
					break;
			}
		}
	}
}
