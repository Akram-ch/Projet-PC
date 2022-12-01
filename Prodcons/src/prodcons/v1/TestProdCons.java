package prodcons.v1;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import threads.Consommateur;
import threads.Producteur;

public class TestProdCons {
	public static void main(String args[]) throws InvalidPropertiesFormatException, IOException {
		
		Properties properties = new Properties();
		properties.loadFromXML(TestProdCons.class.getClassLoader().getResourceAsStream("options.xml"));
		int nProd = Integer.parseInt(properties.getProperty("nProd"));
		int nCons = Integer.parseInt(properties.getProperty("nCons"));
		int consTime = Integer.parseInt(properties.getProperty("consTime"));
		int maxProd = Integer.parseInt(properties.getProperty("maxProd"));
		int minProd = Integer.parseInt(properties.getProperty("minProd"));
		int bufSz = Integer.parseInt(properties.getProperty("bufSz"));
				
		ProdConsBuffer buffer = new ProdConsBuffer(bufSz);
		
		Producteur[] prods = new Producteur[nProd];
		Consommateur[] consos = new Consommateur[nCons];
		
		for (int i = 0; i < nProd; i++) {
			prods[i] = new Producteur(buffer, minProd, maxProd);
		}
		
		for (int j = 0; j < nCons; j++) {
			consos[j] = new Consommateur(buffer, consTime);
		}
	}
}
