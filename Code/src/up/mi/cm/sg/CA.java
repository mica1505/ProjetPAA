package up.mi.cm.sg;
import java.util.ArrayList;
import java.util.Scanner;
/***
 * 
 * @author 
 *
 */
public class CA{
/***
 * 
 */
	private ArrayList <City> agglomeration;
	private int nbCities;
/***
 * 
 * @param nbCities
 */
	public CA(int nbCities) {
		this.agglomeration = new ArrayList<City>();
		setNbCities(nbCities);
		initCity(nbCities);
	}
/***
 * 
 * @param nbCities
 */
	private void initCity(int nbCities) {
		int letter = 65;
		for(int i= 0; i<this.nbCities ; i++) {
			if(nbCities<26) {
				agglomeration.add(new City(String.valueOf((char) letter++)));
			}else {
				agglomeration.add(new City(String.valueOf(i)));
			}
		}
	}
/***
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
 * 
 * @param name
 * @return
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
/***
 * 
 * @param sc
 */
	public void initRoads(Scanner sc) {
		City c1,c2;
		String cr1,cr2;
		
		System.out.print("\nEntrez le nom de la premiere ville : ");
		cr1 = sc.next();
		
		System.out.print("\nEntrez le nom de la seconde ville : ");
		cr2 = sc.next();
		
		c1 = getCity(cr1);
		c2 =  getCity(cr2);
		
		c1.addNeighbour(c2);
		
	}
/***
 * 
 * @param sc
 */
	public void addZone(Scanner sc) {
		City c;
		String cName;
		
		System.out.print("\nEntrez le nom de la ville : ");
		cName = sc.next();
		
		c = getCity(cName);
		if(c.getZone()) {
			System.out.print("\nLa ville est deja en possession d'une zone de recharge\n");
		}else {
			c.setZone(true);
		}
		allZones();
		return;
	}
/***
 * 
 * @param sc
 */
	public void removeZone(Scanner sc) {
		City c;
		String cName;
		
		System.out.print("\nEntrez le nom de la ville : ");
		cName = sc.next();
		
		c = getCity(cName);
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
		allZones();
		return;
	}
/***
 * 
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
	 * 	
	 */
	public void printAgglomeration() {
		for(int i=0;i<nbCities;i++) {
			System.out.println(agglomeration.get(i));
		}
	}
}
