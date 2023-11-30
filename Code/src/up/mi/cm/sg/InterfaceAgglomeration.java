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
		//CA agg = readAgglomeration();
		//agg.printAgglomeration();
		initAgglomeration();
	}
	/***
	 * 
	 * @return
	 */
	public static CA readAgglomeration() {
		return ParseAgglomeration.parseAgg("C:\\Users\\pc\\Documents\\L3\\ProjetPAA\\Code\\src\\up\\mi\\cm\\sg\\test.ca");
	}
	/**
	 * 
	 */
	public static void initAgglomeration() {
		Scanner sc = new Scanner(System.in);
		int nbCities = IO.SaisieInt(sc,"\nEntrez le nombre de villes : ");
		
		CA agg = new CA(nbCities);
		
		menu1(sc,agg);
		menu2(sc,agg);
		
		sc.close();
		agg.printAgglomeration();
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
	public static void menu2(Scanner sc, CA agg) {
		boolean quit = false;
		while(!quit) {
			int choiceB = IO.SaisieInt(sc, "\n1) Ajouter une borne.\n2) Supprimer une borne.\n3) Fin.");
			if(choiceB == 1) {
				agg.addZoneUser(sc);
			}
			else if(choiceB == 2) {
				agg.removeZoneUser(sc);
			}
			else {
				quit = true;
			}
		}
	}
}
