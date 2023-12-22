package up.mi.cm.sg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Random;
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
 * @return name : la ville recherchee
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
	public List<City> getCA(){
		return agglomeration;
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
			if(getCity(cr1)==null) {
				System.out.println("Ville introuvable. Veuillez saisir un nom de ville valide.");
			}
		}while(getCity(cr1)==null);
		do {

			cr2 = IO.SaisieString(sc, "\nEntrez le nom de la seconde ville : ");
			if(getCity(cr2)==null) {
				System.out.println("Ville introuvable. Veuillez saisir un nom de ville valide.");
			}
			if(cr1.equals(cr2)) {
				System.out.println("Une ville ne peut pas etre voisine d'elle meme.");
			}
		}while(getCity(cr2)==null || cr1.equals(cr2));
		c1 = getCity(cr1);
		c2 =  getCity(cr2);
		
		c1.addNeighbour(c2);
		
	}
/***
 * Fonction qui demande a l'utilisateur de saisir une ville dans laquelle il veut ajouter une borne de recharge :
 * @param sc : Scanner pour effectuer une lecture clavier
 */
	public void addZoneUser(Scanner sc) {
		City c;
		String cName;
		
		do {
			System.out.print("\nEntrez le nom de la ville : ");
			cName = sc.next();
			c = getCity(cName);
			if(c == null) {
				System.out.println("Ville introuvable. Veuillez saisir un nom de ville valide.");
			}
		}while(c == null);
		
		try {
			addZone(c);
		} catch (ExeptionChangesArea e) {
			System.out.println(e.getMessage());
		}
		System.out.println(allZones());
	}
	
/***
 * Si la ville ne possede pas de zone de recharge, on en rajoute une. Sinon on ne fait rien
 * @param c : prend la vile a modifier
 */
	public void addZone(City c) throws ExeptionChangesArea{
		if(c.getZone()) {
			throw new ExeptionChangesArea("\nLa ville est deja en possession d'une zone de recharge\n");
		}else {
			c.setZone(true);
			System.out.println("La ville de "+c.getName()+" est mainteneant en possession d'une zone de recharge.");
		}
	}
/***
 * Fonction qui demande a l'utilisateur de saisir le nom de la ville d'ou il veut supprimer une zone de recharge.
 * @param sc : Scanner pour effectuer une lecture clavier 
 */
 	public void removeZoneUser(Scanner sc) {
 		City c;
		String cName;
		
		do {
			System.out.print("\nEntrez le nom de la ville : ");
			cName = sc.next();
			
			c = getCity(cName);
			if(c == null) {
				System.out.println("Ville introuvable. Veuillez saisir un nom de ville valide.");
			}
		}while(c == null);
		
		try {
			removeZone(c);
		} catch (ExeptionChangesArea e) {
			System.out.println(e.getMessage());
		}
		System.out.println(allZones());
 	}
/***
 * La zone est supprimee si et seulement si : 
 * 		- Les villes qui sont reliees a cette ville sont en relation direct avec une autre ville possedant une zone de recharge
 * 		- La ville en question est en relation direct avec une autre ville qui possede une zone de recharge
 * 		- Si la ville possede une zone de recharge
 * @param c : prent la ville a modifier
 */
	public void removeZone(City c) throws ExeptionChangesArea{
		if(!c.getZone()) {
			throw new ExeptionChangesArea("\nLa ville ne possede pas de zone de recharge.\n");
		}else {
			if(c.notLinkedToZone(c)) {
				throw new ExeptionChangesArea("On ne peut pas supprimer la zone, car une des villes frontalieres ne sera pas desservie.");
			}
			else if(!c.hasNeighbourZone()) {
				throw new ExeptionChangesArea("On ne peut pas supprimer la zone de recharge, car la ville n'est pas reliee directement a une ville qui en possede une.");
			}
			else if(c.hasNeighbourZone()) {
				System.out.println("La ville de "+c.getName()+" ne contient plus de zone de recharge.");
				c.setZone(false);
			}
		}
	}
/***
 * Fonction qui affiche les villes qui possedent une zone de recharge
 */
	public String allZones() {
		StringBuilder sb = new StringBuilder("\nLes villes qui possedent des zones de recharge sont : \n");
		for(int i=0;i<nbCities;i++) {
			if(agglomeration.get(i).getZone()) {
				sb.append(agglomeration.get(i).getName()+"  ");
			}
		}
		return sb.toString();
	}
/***
* 	Fonction qui affiche les villes de l'agglomeration
*/
	public String toString() {
		StringBuilder sb = new StringBuilder("");
		for(int i=0;i<nbCities;i++) {
			sb.append(agglomeration.get(i)+"\n");
		}
		return sb.toString();
	}
	public String allCities() {
		StringBuilder sb = new StringBuilder("L'agglomeration est composee des villes suivantes : \n");
		for(int i=0;i<nbCities;i++) {
			sb.append(agglomeration.get(i).getName()+"  ");
		}
		return sb.toString();
	}
	
/***
 * Fonction qui renvois une ville choisie aleatoirement
 * @return City : une ville de l'agglomeration
 */
	public City choise() {
		Random random = new Random();
		int i = random.nextInt(agglomeration.size());
		return agglomeration.get(i);
	}
	/***
	 * Fonction naive qui permet de diminuer le nombre de borne dans une CA
	 * @param int : nombre d'iterations que vas fair le programe avens de sareter 
	 */
	public void naiveSolutions(int iterations) {
		int i = 0;
		while(i < iterations) {
			City c = choise();
			try {
				if(c.getZone()) {
					removeZone(c);
					i++;
				}else {
					addZone(c);
					i++;
				}
			}catch(ExeptionChangesArea e){}
		}
	}
	/**
	 * 
	 * @return
	 */
	public int score() {
		int nbBorne = 0;
		for(City c : agglomeration) {
			if(c.getZone()) {
				nbBorne++;
			}
		}
		return nbBorne;
	}
	/**
	 * 
	 * @param iterations
	 */
	public void naiveSolutions2(int iterations) {
		int i = 0; 
		int scoreCourant = score();
		while(i < iterations) {
			City c = choise();
			try {
				if(c.getZone()) {
					removeZone(c);
					i++;
				}else {
					addZone(c);
					i++;
				}
			}catch(ExeptionChangesArea e){}
			if(score() < scoreCourant) {
				scoreCourant = score();
				i = 0;
			}
		}
	}
	/**
	 * 
	 */
	public void algorithmeGlouton() {
		Collections.sort(agglomeration);
		for(City c : agglomeration) {
			c.setZone(false);
		}
		
		for(City c : agglomeration) {
			if(!c.hasNeighbourZone()) {
				c.setZone(true);
			}
		}
	}
}


