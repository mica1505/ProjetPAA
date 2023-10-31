package up.mi.cm.sg;
import java.util.Scanner;

public class InterfaceAgglomeration {
	public static void main(String [] args) {
		Scanner inputNbCities = new Scanner(System.in);
		System.out.println("Entrez le nombre de villes : ");
		int nbCities = inputNbCities.nextInt();
		CA agg = new CA(nbCities);
		
		boolean quit = false;
		Scanner inputChoice = new Scanner(System.in);
		while(!quit) {
			System.out.println("\n1) Ajouter une route.\n2) Fin.");
			
			String choice = inputChoice.next();
			switch(choice) {
				case "1":
					agg.initRoads();
					break;
				case "2":
					quit = true;
					
					break;
			}
		}
		inputNbCities.close();
		inputChoice.close();
		agg.printAgglomeration();
	}
}
