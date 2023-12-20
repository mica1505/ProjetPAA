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
			int choiceB;
			do {
				choiceB = IO.SaisieInt(sc, "\n1) Resoudre manuellement.\n2) Resoudre Automatiquement.\n3) Sauvegarder.\n4) Fin ");
				if(choiceB<0 || choiceB>4) {
					System.out.println("Vous devez saisir un chiffre entre 1 et 4.");
				}
			}while(choiceB<0 || choiceB>4);
			
			if(choiceB == 1) {
				menuHuman(sc, agg);
				quit = true;
			}
			else if(choiceB==2) {
				menuComputer(sc, agg);
				quit = true;
			}
			else if(choiceB == 3){
				String chemin = IO.SaisieString(sc, "\nVeuillez saisir le chemin du fichier dans le quel vous voulez enregistrer votre agglomeration : ");
				ParseAgglomeration.writeCA(agg,chemin);
			}
			else if(choiceB == 4) {
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
		agg.algorithmeGlouton();
		System.out.println(agg);
	}
}