package up.mi.cm.sg;
import java.util.ArrayList;
/***
 * 
 * @author 
 *
 */
public class City{
	/***
	 * 
	 */
	private String name;
	private ArrayList<City> cities;
	private boolean containZone;

	public City(String name) {
		this.name = name;
		cities = new ArrayList<>();
		this.containZone = true;
	}
	/***
	 * 
	 * @param name
	 * @param cities
	 */
	public City(String name, ArrayList<City> cities) {
		this(name);
		this.cities=cities;
		this.containZone = true;
	}
	/***
	 * 
	 * @return
	 */
	public ArrayList<City> getCities(){
		return this.cities;
	}
	/***
	 * 
	 * @return
	 */
	public boolean getZone() {
		return this.containZone;
	}
	/***
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}
	/***
	 * 
	 * @param b
	 */
	public void setZone(boolean b) {
		this.containZone = b;
	}
	/***
	 * 
	 * @param newCity
	 */
	public void addNeighbour(City newCity) {
		if(!this.cities.contains(newCity)) {
			(this.cities).add(newCity);
		}
		if(!newCity.getCities().contains(this)) {
			newCity.getCities().add(this);
		}
	}
	/***
	 * 
	 */
	public String toString() {
		StringBuffer sbf = new StringBuffer("\nNom de la ville: " + this.name + "\nA une station de recharge : "+(this.getZone()?"Oui\n":"Non\n")+"Les villes voisines : ");
		for(int i=0;i<this.cities.size();i++) {
			if(this.cities.size()>0) {
				sbf.append(this.cities.get(i).getName() + " + ");
			}
		}
		return sbf.toString();
	}
	/***
	 * 
	 * @return
	 */
	public boolean hasNeighbourZone() {
		boolean res = false;
		for(int i=0;i<this.cities.size() && !res;i++) {
			if(this.cities.get(i).getZone()) {
				res = true;
			}
		}
		return res;
	}
	/***
	 * 
	 * @param c
	 * @return
	 */
	public boolean hasNeighbourZone(City c) {
		boolean res = false;
		for(int i=0;i<this.cities.size() && !res;i++) {
			//si un de ses voisins a une zone et que ce voisin n'est pas C
			if( (this.cities.get(i)).getZone() && !((this.cities.get(i)).getName().equals(c.getName()))) {
				res = true;
			}
		}
		return res;
	}
	/***
	 * 
	 * @param c
	 * @return
	 */
	public boolean notLinkedToZone(City c) {
		boolean res=false;
		for(int i=0;i<this.cities.size() && !res;i++) {
			if(!(this.cities.get(i)).hasNeighbourZone(c)) {
				res = true;
			}
		}
		return res;
	}
}
