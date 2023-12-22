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
		if(args[0].length() == 0) {
			System.out.println("Chemin passe invalide");
			System.exit(0);
		}
		initAgglomeration(args[0]);
	}
	/***
	 * Cree une agglomérations ou affiche une erreur et quitte le programme si le ficher nais pas correcte
	 * @param path Le chemin du ficher qui contins l’agglomérations
	 * @return Renvois l’agglomérations crée a partir du ficher indiquer
	 */
	public static CA readAgglomeration(String path) {
		try {
			return ParseAgglomeration.parseAgg(path);
		} catch (ExeptionChangesArea e) {
			e.printStackTrace();
			System.exit(0);
		}
		return null;
	}
	/**
	 * Récupère l’agglomérations et lance le menus textuelle 
	 * @param  Le chemin du ficher qui contins l’agglomérations
	 */
	public static void initAgglomeration(String path) {
		Scanner sc = new Scanner(System.in);
		
		CA agg = readAgglomeration(path);
		if(agg == null) {
			System.exit(0);
		}
		System.out.println(agg.allCities());
		menuHumanOrComputer(sc,agg);
		sc.close();
		System.out.print(agg);
		System.out.print(agg.allZones());
	}
	/***
	 * Le menu d’interface textuelle pour ajouter des route
	 * @param sc Le Scaner pour saisir une route
	 * @param agg La communauté d’agglomérations a modifier
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
	 * Le menu pour choisir si on fais une résolutions manuelle ou automatique
	 * @param sc Le Scaner pour saisir le type de resolutions
	 * @param agg La communauté d’agglomérations a modifier
	 */
	public static void menuHumanOrComputer(Scanner sc, CA agg) {
		boolean quit = false;
		while(!quit) {
			int choiceB;
			do {
				choiceB = IO.SaisieInt(sc, "\n1) Resoudre manuellement.\n2) Resoudre Automatiquement.\n3) Sauvegarder.\n4) Fin ");
				if(choiceB<0 || choiceB>4) {
					System.out.println("Veuillez saisir un chiffre entre 1 et 4.");
				}
			}while(choiceB<0 || choiceB>4);
			
			if(choiceB == 1) {
				menuHuman(sc, agg);
			}
			else if(choiceB==2) {
				menuComputer(agg);
			}
			else if(choiceB == 3){
				String chemin;
				do {
					chemin = IO.SaisieString(sc, "\nVeuillez saisir le chemin du fichier dans le quel vous voulez enregistrer votre agglomeration : ");
				}while(chemin == "" || chemin == null);
				ParseAgglomeration.writeCA(agg,chemin);
			}
			else if(choiceB == 4) {
				quit = true;
			}
		}
	}
	/**
	 * Le menus pour ajouter ou supprimer une borne de recharge
	 * @param sc Le Scaner pour saisir l'ajout ou la supretions de borne
	 * @param agg La communauté d’agglomérations a modifier
	 */
	public static void menuHuman(Scanner sc, CA agg) {
		boolean quit = false;
		while(!quit) {
			int choice;
			do {
				choice = IO.SaisieInt(sc, "\n1) Ajouter une borne.\n2) Supprimer une borne.\n3) Fin.");
				if (choice<0 || choice>3) {
					System.out.println("Veuillez saisir un chiffre entre 1 et 4.");
				}
			}while(choice<0 || choice>3);
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
	 * Résous le problème avec un algorithme gloutons 
	 * @param agg La communauté d’agglomérations a modifier
	 */
	public static void menuComputer(CA agg) {
		agg.algorithmeGlouton();
		System.out.println(agg.allZones());
	}
}