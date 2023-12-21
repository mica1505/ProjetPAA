package up.mi.cm.sg;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayList;

public class ParseAgglomeration {
	/**
	 * 
	 * @param file
	 * @return
	 */
	public static CA parseAgg(String file) throws ExeptionChangesArea{
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> data = new ArrayList<String>();
			String line = null;
			int nbCities = 0;
			while((line=br.readLine())!=null) {
				data.add(line);
				if(line.startsWith("ville")) {
					nbCities++;
				} else if(!line.startsWith("route") && !line.startsWith("recharge")) {
					throw new ExeptionChangesArea("\nUn probleme dans lecriture de notre ficher a la linge " + nbCities+1 + "\n");
				}
			}
			//System.out.println(nbCities);
			CA agg = readCities(data,nbCities);
			readNeighbours(data,nbCities,agg);
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
	 * 
	 * @param data
	 * @param nbCities
	 * @return
	 */
	public static CA readCities(ArrayList<String> data,int nbCities){
		String line=null;
		CA agg = new CA();
		for(int i=0;i<nbCities;i++) {
			line = data.get(i);
			//System.out.print(line.substring(6,line.length()-2));
			City nCity = new City(line.substring(6,line.length()-2));
			nCity.setZone(false);
			agg.addCity(nCity);
		}
		agg.setNbCities(nbCities);
		return agg;
	}
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
	public static void writeCities(CA agg, Writer writer) throws IOException{
		for(City c : agg.getCA()) {
			writer.write("ville(" + c.getName() + ").\n");
		}
	}
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
	public static void writeZones(CA agg, Writer writer) throws IOException{
		for(City c : agg.getCA()) {
			if(c.getZone()) {
				writer.write("recharge("+c.getName()+").\n");
			}
		}
	}
	/**
	 * 
	 * @param data
	 * @param nbCities
	 * @param community
	 * @return
	 */
	public static CA readNeighbours(ArrayList<String> data,int nbCities,CA community) throws ExeptionChangesArea{
		String line = null;
		City a,b;
		//System.out.println("JE SUIS DANS READ NEIGHBOURS "+nbCities);
		for(int i=nbCities;i<data.size();i++) {
			line=data.get(i); 
			if(line.startsWith("route")) {
				//System.out.println("HAHA");
				a = community.getCity(line.split("\\(")[1].split(",")[0]);
				b = community.getCity(line.split("\\(")[1].split(",")[1].split("\\)")[0]);
				//System.out.println(line.split("\\(")[1].split(",")[0]+"--"+line.split("\\(")[1].split(",")[1].split("\\)")[0]);
				a.addNeighbour(b);
			}else if(!line.startsWith("ville") && !line.startsWith("recharge")) {
				throw new ExeptionChangesArea("\nUn probleme dans lecriture de notre ficher a la linge " + i+1 + "\n");
			}
		}
		//community.printAgglomeration();
		return community;
	}
	/**
	 * On lit les zone de recharge.
	 * Si elle ne repecte pas les nom corecte on renvois une erreur
	 * @param data
	 * @param nbCities
	 * @param community
	 */
	public static void readZones(ArrayList<String> data,int nbCities,CA community) throws ExeptionChangesArea{
		String line = null;
		boolean valide = true;
		City a,b;
		for(City c : community.getCA()) {
			c.setZone(false);
		}
		for(int i=nbCities;i<data.size();i++) {
			line=data.get(i); 
			if(line.startsWith("recharge")) {
				a = community.getCity(line.split("\\(")[1].split("\\)")[0]);
				a.setZone(true);
			}else if(!line.startsWith("route") && !line.startsWith("ville")) {
				throw new ExeptionChangesArea("\nUn probleme dans lecriture de notre ficher a la linge " + i+1 + "\n");
			}
			
		}
		for(City c : community.getCA()) {
			valide &= c.hasNeighbourZone() || c.getZone();
			
		}
		if(!valide) {
			for(City c : community.getCA()) {
				c.setZone(true);
			}
		}
	}
}
