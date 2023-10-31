package up.mi.cm.sg;
import java.util.ArrayList;
import java.util.Scanner;
/***
 * 
 * @author pc
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

	private void initCity(int nbCities) {
		int letter = 97;
		for(int i= 0; i<this.nbCities ; i++) {
			if(nbCities<26) {
				agglomeration.add(new City(String.valueOf((char) letter++)));
			}else {
				agglomeration.add(new City(String.valueOf(i)));
			}
		}
	}

	public void setNbCities(int nbCities) {
		if(nbCities<2) {
			throw new IllegalArgumentException("Le nombre de villes doit etre >= 2");
		}else {
			this.nbCities = nbCities;
		}
	}

	public City getCity(String name){
		City res = null;
		for(City c : this.agglomeration) {
			if(name.equals(c.getName())) {
				res = c;
			}
		}
		return res;
	}

	public void initRoads() {
		Scanner city1 = new Scanner(System.in);
		Scanner city2 = new Scanner(System.in);
		City c1,c2;
		String cr1,cr2;
		
		System.out.print("\nEntrez le nom de la premiere ville : ");
		cr1 = city1.next();
		
		System.out.print("\nEntrez le nom de la seconde ville : ");
		cr2 = city2.next();
		
		c1 = getCity(cr1);
		c2 =  getCity(cr2);
		
		c1.addNeighbour(c2);
		
		city1.close();
		city2.close();
	}

	public void initZones() {
		return;
	}

	public void printAgglomeration() {
		for(int i=0;i<nbCities;i++) {
			System.out.println(agglomeration.get(i));
		}
	}
}
