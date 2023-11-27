package up.mi.cm.sg;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/***
 * Represente une agglomeration avec sa liste de villes et le nombre de villes qu'elle contient
 * @author 
 * @version 1
 */
public class CA{
/***
 * Initialisation des attributs de la classe 
 * @agglomeration : liste des villes qui constituent l'agglomeration
 * @nbCities : nombre de villes dans l'agglomeration
 */
	private List<City> agglomeration;
	private int nbCities;
/***
 * Construit une agglomeration avec : 
 * 		- Une ArrayList de type City qui contient le nom des villes
 * 		- Nombre de villes a nbCities
 * 
 * @param nbCities : le nombre de villes de l'agglomeration
 */
	public CA(int nbCities) {
		this.agglomeration = new ArrayList<City>();
		setNbCities(nbCities);
		initCity();
	}
	public CA() {
		this.agglomeration = new ArrayList<City>();
	}
/***
 * Initialise le nom des villes de l'agglomeration :
 * 		- Si nbCities<=26 le nom des villes c'est une lettre de l'alphabet
 * 		- Sinon elles seront numerotes de 1 jusqu'a nbCities
 * 
 * @param nbCities : le nombre de villes de l'agglomeration
 */
	private void initCity() {
		int letter = 65;
		for(int i= 0; i<this.nbCities ; i++) {
			if(nbCities<=26) {
				agglomeration.add(new City(String.valueOf((char) letter++)));
			}else {
				agglomeration.add(new City(String.valueOf(i)));
			}
		}
	}
/***
 *Permet de modifier le nombre de villes de l'agglomeration
 *
 * @param nbCities
 */
	public void setNbCities(int nbCities) {
		if(nbCities<2) {
			throw new IllegalArgumentException("Le nombre de villes doit etre >= 2");
		}else {
			this.nbCities = nbCities;
		}
	}
/***
 * Permet d'acceder a une ville de l'agglomeration en partant de son nom 
 * @param name : nom de la ville
 * @return City : la ville recherchee
 */
	public City getCity(String name){
		City res = null;
		for(City c : this.agglomeration) {
			if(name.equals(c.getName())) {
				res = c;
			}
		}
		return res;
	}
	public void addCity(City newCity) {
		agglomeration.add(newCity);
	}
/***
 * Fonction pour initialiser une route entre 2 villes saisies au clavier
 * @param sc : Scanner pour effectuer une lecture clavier
 */
	public void initRoads(Scanner sc) {
		City c1,c2;
		String cr1,cr2;
		
		do {
		cr1 = IO.SaisieString(sc, "\nEntrez le nom de la premiere ville : ");
		}while(nbCities <= 26 && (cr1.charAt(0)<'A'||cr1.charAt(0)>='A'+nbCities)|| nbCities > 26 && (Integer.parseInt(cr1)<0 || Integer.parseInt(cr1)>nbCities));
		do {
		cr2 = IO.SaisieString(sc, "\nEntrez le nom de la seconde ville : ");
		}while(nbCities <= 26 && (cr2.charAt(0)<'A'|| cr2.charAt(0)>='A'+nbCities)|| nbCities > 26 && (Integer.parseInt(cr2)<0 || Integer.parseInt(cr2)>nbCities)||cr1.equals(cr2));
		c1 = getCity(cr1.toUpperCase());
		c2 =  getCity(cr2.toUpperCase());
		
		c1.addNeighbour(c2);
		
	}
/***
 * Fonction qui demande a l'utilisateur de saisir une ville dans laquelle il veut ajouter une borne de recharge :
 * 		- Si la ville ne possede pas de zone de recharge, on en rajoute une. Sinon on ne fait rien
 * @param sc : Scanner pour effectuer une lecture clavier
 */
	public void addZone(Scanner sc) {
		City c;
		String cName;
		
		System.out.print("\nEntrez le nom de la ville : ");
		cName = sc.next();
		
		c = getCity(cName.toUpperCase());
		if(c.getZone()) {
			System.out.print("\nLa ville est deja en possession d'une zone de recharge\n");
		}else {
			c.setZone(true);
		}
		allZones();
		return;
	}
/***
 * Fonction qui demande a l'utilisateur de saisir le nom de la ville d'ou il veut supprimer une zone de recharge.
 * La zone est supprimee si et seulement si : 
 * 		- Les villes qui sont reliees a cette ville sont en relation direct avec une autre ville possedant une zone de recharge
 * 		- La ville en question est en relation direct avec une autre ville qui possede une zone de recharge
 * 		- Si la ville possede une zone de recharge
 * @param sc : Scanner pour effectuer une lecture clavier
 */
	public void removeZone(Scanner sc) {
		City c;
		String cName;
		
		System.out.print("\nEntrez le nom de la ville : ");
		cName = sc.next();
		
		c = getCity(cName.toUpperCase());
		if(!c.getZone()) {
			System.out.print("\nLa ville ne possede pas de zone de recharge.\n");
		}else {
			if(c.notLinkedToZone(c)) {
				System.out.println("On ne peut pas supprimer la zone, car une des villes frontalieres ne sera pas desservie.");
			}
			else if(!c.hasNeighbourZone()) {
				System.out.println("On ne peut pas supprimer la zone de recharge, car la ville n'est pas reliee directement a une ville qui en possede une.");
			}
			else if(c.hasNeighbourZone()) {
				c.setZone(false);
			}
		}
		//afficher les villes possedant des zones de recharge
		allZones();
		return;
	}
/***
 * Fonction qui affiche les villes qui possedent une zone de recharge
 */
	public void allZones() {
		System.out.print("\nLes villes qui possedent des zones de recharge sont : ");
		for(int i=0;i<nbCities;i++) {
			if(agglomeration.get(i).getZone()) {
				System.out.print(agglomeration.get(i).getName()+"  ");
			}
		}
	}
/***
* 	Fonction qui affiche les villes de l'agglomeration
*/
	public void printAgglomeration() {
		for(int i=0;i<nbCities;i++) {
			System.out.println(agglomeration.get(i));
		}
	}
}
