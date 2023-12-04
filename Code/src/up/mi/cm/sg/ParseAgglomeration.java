package up.mi.cm.sg;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

public class ParseAgglomeration {
	
	public static CA parseAgg(String file) {
		
		try{
			BufferedReader br = new BufferedReader(new FileReader(file));
			ArrayList<String> data = new ArrayList<String>();
			String line = null;
			int nbCities = 0;
			while((line=br.readLine())!=null) {
				data.add(line);
				if(line.startsWith("ville")) {
					nbCities++;
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
	
	public static CA readCities(ArrayList<String> data,int nbCities){
		String line=null;
		CA agg = new CA();
		for(int i=0;i<nbCities;i++) {
			line = data.get(i);
			City nCity = new City(line.substring(6,7));
			nCity.setZone(false);
			agg.addCity(nCity);
		}
		agg.setNbCities(nbCities);
		return agg;
	}
	
	public static CA readNeighbours(ArrayList<String> data,int nbCities,CA community){
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
			}
		}
		//community.printAgglomeration();
		return community;
	}
	
	public static void readZones(ArrayList<String> data,int nbCities,CA community) {
		String line = null;
		City a,b;
		for(int i=nbCities;i<data.size();i++) {
			line=data.get(i); 
			if(line.startsWith("recharge")) {
				a = community.getCity(line.split("\\(")[1].split("\\)")[0]);
				a.setZone(true);
			}
		}
	}
}
