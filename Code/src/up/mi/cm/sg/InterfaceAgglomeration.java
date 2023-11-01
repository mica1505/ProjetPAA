package up.mi.cm.sg;
import java.util.Scanner;


public class InterfaceAgglomeration {
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("\nEntrez le nombre de villes : ");
		int nbCities = sc.nextInt();
		
		CA agg = new CA(nbCities);
		
		boolean quit = false;
		while(!quit) {
			System.out.println("\n1) Ajouter une route.\n2) Fin.");
			String choice  = sc.next();
			
			switch(choice) {
				case "1":
					agg.initRoads(sc);
					break;
				case "2":
					System.out.println("\n1) Ajouterune borne.\n2) Supprimer une borne.\n3) Fin.");
					int choiceB = sc.nextInt();
					if(choiceB == 1) {
						agg.addZone(sc);
					}
					else if(choiceB == 2) {
						agg.removeZone(sc);
					}
					else {
						quit = true;
					}
					break;
			}
		}
		
		
		sc.close();
		agg.printAgglomeration();
	}
}
