package up.mi.cm.sg;
import java.util.ArrayList;
/***
 * Represente une ville avec son nom, sa liste de villes voisines, sa zone de recharge
 * @author 
 * @version 1
 * @implNote Comparable Pour pouvoir trier les villes
 */
public class City implements Comparable<City>{
	/***
	 * @name nom de la ville
	 * @cities liste des villes voisines
	 * @containZone boolean mis a true si la ville contient une zone, faux sinon
	 */
	private String name;
	private ArrayList<City> cities;
	private boolean containZone;
	/***
	 * Constructeur : 
	 * 		- Construit un Objet City et lui attribue un nom
	 * @param name
	 */
	public City(String name) {
		this.name = name;
		cities = new ArrayList<>();
		this.containZone = true;
	}
	/***
	 * Constructeur : 
	 * 		- Construit un Objet City et lui attribue un nom et une liste de villes voisines
	 * @param name
	 * @param cities
	 */
	public City(String name, ArrayList<City> cities) {
		this(name);
		this.cities=cities;
		this.containZone = true;
	}
	/***
	 * Permet d'acceder a la liste des villes voisines 
	 * @return ArrayList<City> : liste des villes voisines
	 */
	public ArrayList<City> getCities(){
		return this.cities;
	}
	/***
	 * Fonction qui permet de savoir si une ville contient une zone ou pas
	 * @retur boolean : True si la ville contient une zone,False sinon
	 */
	public boolean getZone() {
		return this.containZone;
	}
	/***
	 * Permet d'acceder au nom de la ville
	 * @return String : le nom de la ville 
	 */
	public String getName() {
		return this.name;
	}
	/***
	 * Permet de modifier l'attribut containZone
	 * @param b : boolean 
	 */
	public void setZone(boolean b) {
		this.containZone = b;
	}
	/***
	 * Fonction qui Ajoute une ville voisine à la ville actuelle. 
	 * Si la ville spécifiée n'est pas déjà une voisine,
	 * elle est ajoutée à la liste des villes voisines tant pour la ville actuelle que pour la ville spécifiée
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
	 * Renvoie une représentation textuelle de l'objet Ville 
	 * @return un String contenant le nom de la ville actuelle, indique la presence station de recharge et ses villes voisines
	 */
	public String toString() {
		StringBuffer sbf = new StringBuffer("\n"+this.name + " : "+(this.getZone()?"Oui ":"Non ")+"[");
		for(int i=0;i<this.cities.size();i++) {
			if(this.cities.size()>0) {
				sbf.append(this.cities.get(i).getName() + " ");
				sbf.append(this.cities.get(i).getZone()?": Oui ":": Non ");
			}
			
		}
		sbf.append("]");
		return sbf.toString();
	}
	/***
	 * Verifie si la ville actuelle est reliee directement a une une ville qui contient une zone de recharge
	 * @return boolean : true si la ville est reliee directement a une ville qui contient une zone de recharge, faux sinon
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
	 * Verifie si une ville voisine est reliee directement a une une ville qui contient une zone de recharge hormis la ville actuelle
	 * @param c
	 * @return boolean : true si la ville est reliee directement a une ville qui contient une zone de recharge, faux sinon
	 */
	public boolean hasNeighbourZone(City c) {
		boolean res = false;
		for(int i=0;i<this.cities.size() && !res;i++) {
			//si un de ses voisins a une zone et que ce voisin n'est pas C
			if((this.cities.get(i)).getZone() && !((this.cities.get(i)).getName().equals(c.getName()))) {
				res = true;
			}
		}
		return res;
	}
	/***
	 * Verifie si tous les voisins de la ville actuelle sont relies a une autre ville contenant une zone de recharge ou non
	 * @param c
	 * @return boolean : true si un des voisins n'est pas relie a une zone
	 */
	public boolean notLinkedToZone(City c) {
		boolean res=false;
		//pour chaque voisin de la ville actuelle on verifie si il a une zone ou si il est relie directement a une ville qui en contient
		for(int i=0;i<this.cities.size() && !res;i++) {
			if((!(this.cities.get(i)).hasNeighbourZone(c)) && !this.cities.get(i).getZone()) {
				res = true;
			}
		}
		return res;
	}
	@Override
	/***
	 * Compare deux ville parapode a leur nombre de ville voisine 
	 * Une fonctions qui redéfini la méthode compareTo de l’interface Comparable pour permettre de trier les liste de City
	 * @param Prend une autre ville 
	 * @return sa envois 1 on a moin de voisint que lautre -1 si on en a plus 0 si il y en a autent
	 * */
	public int compareTo(City c) {
		if (cities.size() < c.cities.size()){
			return 1;
		}else if (cities.size() > c.cities.size()) {
			return -1;
		}else {
			return 0;
		}
	}
}
