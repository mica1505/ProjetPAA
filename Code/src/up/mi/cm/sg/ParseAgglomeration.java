package up.mi.cm.sg;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;
/**
 * Classe qui permet de lire une agglomeration depuis un fichier et en sauvegarde rune dasn un fichier
 * @author 
 *
 */
public class ParseAgglomeration {
	/**
	 * fonction qui lit une agglomeration depuis un fichier
	 * @param File le fichier ou se trouve notre agglomeration
	 * @return CA l'agglomeration lue depuis le fichier
	 */
	public static CA parseAgg(String file) throws ExeptionChangesArea{
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> data = new ArrayList<String>();
			String line = null;
			int nbCities = 0;
			boolean empty = false;
			//on recupere les lignes du fichier dans une liste
			while((line=br.readLine())!=null && !empty) {
				empty = line.equals("");
				if(!empty) {
					data.add(line);
				}
				if(line.startsWith("ville")) {
					nbCities++;
				} //on vérifie si il y a un mot qui ne correspond a rien et que la chaine n'est pas vide
				else if(!line.startsWith("route") && !line.startsWith("recharge") && !empty) {
					throw new ExeptionChangesArea("\nUn probleme dans l'ecture de notre ficher a la linge " + nbCities + "\n");
				}
			}
			//on lit les villes
			CA agg = readCities(data,nbCities);
			//on lit les voisins
			readNeighbours(data,nbCities,agg);
			//on lit les zones des villes
			readZones(data,nbCities,agg);
			br.close();
			return agg;
		}
		catch(FileNotFoundException e) {
			System.out.println(e);
		}
		catch(IOException e) {
			System.out.println(e);
		}
		return null;
	}
	/**
	 * fonction qui lit les villes depuis le fichier passee en argument
	 * @param data : liste des lignes contenues dasn le fichier
	 * @param nbCities : nombre de villes dans notre agglomeration
	 * @return CA agglomeration contenant les villes saisies dasn le fichier
	 */
	public static CA readCities(ArrayList<String> data,int nbCities){
		String line=null;
		CA agg = new CA();
		for(int i=0;i<nbCities;i++) {
			line = data.get(i);
			//on parse le nom de la ville et on l'ajoute a l'agglomeration
			City nCity = new City(line.substring(6,line.length()-2));
			//nCity.setZone(false);
			agg.addCity(nCity);
		}
		agg.setNbCities(nbCities);
		return agg;
	}
	/**
	 * fonction qui ecrit une agglomeration dans un fichier
	 * @param agg on recouper la communauté d’agglomérations courent 
	 * @param File Le chemin du ficher sur le quelle on vas écrire 
	 */
	public static void writeCA(CA agg, String File) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(File);
			Writer out = new OutputStreamWriter(fos, "UTF8");
			//on ecrit les villes
			writeCities(agg, out);
			//on ecrit les voisins de chaque ville
			writeNeighbours(agg, out);
			//on ecrit les zones de recharge des villes qui en possede une
			writeZones(agg, out);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	
	}
	/**
	 * Ecrit tout les nom de ville dans le ficher avec le format ville(nom).
	 * @param agg On recouper la communauté d’agglomérations courent 
	 * @param writer On prend l’objet Writer avec le quelle on écrit dans le ficher .ca
	 * @throws IOException On propage l’erreur a la fonctions writeCA qui vas la catch si n’essaierai 
	 */
	public static void writeCities(CA agg, Writer writer) throws IOException{
		for(City c : agg.getCA()) {
			writer.write("ville(" + c.getName() + ").\n");
		}
	}
	/**
	 * Ecrit toutes les route en vérifient que on ne la pas déjà avec une liste le format et route(nomA,nomB).
	 * @param agg on recouper la communauté d’agglomérations courent 
	 * @param writer On prend l’objet Writer avec le quelle on écrit dans le ficher .ca
	 * @throws IOException On propage l’erreur a la fonctions writeCA qui vas la catch si n’essaierai 
	 */
	public static void writeNeighbours(CA agg, Writer writer) throws IOException{
		//sauvegarde les route déjà considérer
		ArrayList<String> voisinage = new  ArrayList<String>();
		for(City c : agg.getCA()) {
			for(City v : c.getCities()) {
				if(!voisinage.contains("route("+c.getName()+","+v.getName()+").\n")) {
					writer.write("route("+c.getName()+","+v.getName()+").\n");
					voisinage.add("route("+v.getName()+","+c.getName()+").\n");
				}
			}
		}
		
	}
	/**
	 * Ecrit les villes qui ont un zone de recharge avec le format recharge(nom).
	 * @param agg on recouper la communauté d’agglomérations courent 
	 * @param writer On prend l’objet Writer avec le quelle on écrit dans le ficher .ca
	 * @throws IOException On propage l’erreur a la fonctions writeCA qui vas la catch si n’essaierai 
	 */
	public static void writeZones(CA agg, Writer writer) throws IOException{
		for(City c : agg.getCA()) {
			if(c.getZone()) {
				writer.write("recharge("+c.getName()+").\n");
			}
		}
	}
	/**
	 * fonction qui lit les voisins ,des villes de community, depuis le fichier passee en argument
	 * @param data : liste des lignes contenues dasn le fichier
	 * @param nbCities : nombre de villes dans notre agglomeration
	 * @return CA community : agglomeration qui contient les villes saisies dasn le fichier
	 * @return l'agglomeration avec des villes et ses voisins
	 */
	public static CA readNeighbours(ArrayList<String> data,int nbCities,CA community) throws ExeptionChangesArea{
		String line = null;
		City a,b;
		//System.out.println("JE SUIS DANS READ NEIGHBOURS "+nbCities);
		for(int i=nbCities;i<data.size();i++) {
			line=data.get(i); 
			if(line.startsWith("route")) {
				//on parse la ligne pour recuperer le nom des villes a et b et l chercher dans l'agglomeration
				a = community.getCity(line.split("\\(")[1].split(",")[0]);
				b = community.getCity(line.split("\\(")[1].split(",")[1].split("\\)")[0]);
				//on rajoute b aux voisins de a
				a.addNeighbour(b);
			}
		}
		//community.printAgglomeration();
		return community;
	}
	/**
	 * 
	 * fonction qui lit les zones ,des villes de community, depuis le fichier passee en argument
	 * @param data : liste des lignes contenues dasn le fichier
	 * @param nbCities : nombre de villes dans notre agglomeration
	 * @return CA community : agglomeration qui contient les villes saisies dasn le fichier
	 * @return l'agglomeration avec des villes et ses voisins et les zones de rechrge
	 */
	public static void readZones(ArrayList<String> data,int nbCities,CA community) throws ExeptionChangesArea{
		String line = null;
		boolean valide = true;
		City a,b;
		//on met otutes les zones a faux
		for(City c : community.getCA()) {
			c.setZone(false);
		}
		//on verifie si le fichier contient des zones
		for(int i=nbCities;i<data.size();i++) {
			line=data.get(i); 
			if(line.startsWith("recharge")) {
				//on parse la ligne pour recuperer le nom de la ville et la chercher dans l'agglomeration
				a = community.getCity(line.split("\\(")[1].split("\\)")[0]);
				//on lui ajoute une zone de recharge
				a.setZone(true);
			}
			
		}
		//on verifie que chaque ville respecte bien la contrainte d'accessibilite
		for(City c : community.getCA()) {
			valide &= c.hasNeighbourZone() || c.getZone();
			
		}
		//si une des villes ne respecte pas la contrainte d'accessibilite on met a chaque ville une zone de recharge
		if(!valide) {
			for(City c : community.getCA()) {
				c.setZone(true);
			}
		}
	}
}
