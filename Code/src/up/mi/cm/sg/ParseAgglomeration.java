package up.mi.cm.sg;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

public class ParseAgglomeration {
	/**
	 * fonction qui llit une agglomeration depuis un fichier
	 * @param File le fichier ou se trouve notre agglomeration
	 * @return CA l'agglomeration lue depuis le fichier
	 */
	public static CA parseAgg(String file) throws ExeptionChangesArea{
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> data = new ArrayList<String>();
			String line = null;
			int nbCities = 0;
			//on recupere les lignes du fichier dans une liste
			while((line=br.readLine())!=null) {
				data.add(line);
				if(line.startsWith("ville")) {
					nbCities++;
				} else if(!line.startsWith("route") && !line.startsWith("recharge")) {
					throw new ExeptionChangesArea("\nUn probleme dans la lecture de notre ficher a la linge " + nbCities + "\n");
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
	 * 
	 * @param agg : 
	 * @param File
	 */
	public static void writeCA(CA agg, String File) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(File);
			Writer out = new OutputStreamWriter(fos, "UTF8");
			writeCities(agg, out);
			writeNeighbours(agg, out);
			writeZones(agg, out);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	/**
	 * 
	 * @param agg
	 * @param writer
	 * @throws IOException
	 */
	public static void writeCities(CA agg, Writer writer) throws IOException{
		for(City c : agg.getCA()) {
			writer.write("ville(" + c.getName() + ").\n");
		}
	}
	/**
	 * 
	 * @param agg
	 * @param writer
	 * @throws IOException
	 */
	public static void writeNeighbours(CA agg, Writer writer) throws IOException{
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
	 * 
	 * @param agg
	 * @param writer
	 * @throws IOException
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
			}else if(!line.startsWith("ville") && !line.startsWith("recharge")) {
				throw new ExeptionChangesArea("\nUn probleme dans lecriture de notre ficher a la linge " + i + "\n");
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
		//on parcours la liste des lignes du fichier
		for(int i=nbCities;i<data.size();i++) {
			line=data.get(i); 
			if(line.startsWith("recharge")) {
				//on parse la ligne pour recuperer le nom de la ville et la chercher dans l'agglomeration
				a = community.getCity(line.split("\\(")[1].split("\\)")[0]);
				//on lui ajoute une zone de recharge
				a.setZone(true);
			}else if(!line.startsWith("route") && !line.startsWith("ville")) {
				throw new ExeptionChangesArea("\nUn probleme dans lecriture de notre ficher a la linge " + i + "\n");
			}
			
		}
		for(City c : community.getCA()) {
			valide &= c.hasNeighbourZone();
		}
		if(!valide) {
			for(City c : community.getCA()) {
				c.setZone(true);
			}
		}
	}
}
