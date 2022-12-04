package prodcons.v1;

import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Random;

import prodcons.v2.Consumer;
import prodcons.v2.Producer;
import threads.Consommateur;
import threads.Producteur;

public class TestProdCons {
	public static void main(String args[]) throws InvalidPropertiesFormatException, IOException, InterruptedException {

		Properties properties = new Properties();
		properties.loadFromXML(TestProdCons.class.getClassLoader().getResourceAsStream("options.xml"));
		int nProd = Integer.parseInt(properties.getProperty("nProd"));
		int nCons = Integer.parseInt(properties.getProperty("nCons"));
		int consTime = Integer.parseInt(properties.getProperty("consTime"));
		int maxProd = Integer.parseInt(properties.getProperty("maxProd"));
		int minProd = Integer.parseInt(properties.getProperty("minProd"));
		int bufSz = Integer.parseInt(properties.getProperty("bufSz"));

		ProdConsBuffer buffer = new ProdConsBuffer(bufSz);
		
		prodcons.v1.Producer[] prods = new prodcons.v1.Producer[nProd];
		prodcons.v1.Consumer[] consos = new prodcons.v1.Consumer[nCons];

		
		
		int i = 0;
		int j = 0;
		boolean choice;
		while (i < nProd || j < nCons) {
			Random rand = new Random();
			choice = rand.nextBoolean();
			if (choice) {
				if (i < nProd) {
					prods[i] = new prodcons.v1.Producer(buffer, minProd, maxProd);
					i++;
				}
			} else {
				if (j < nCons) {
					consos[j] = new prodcons.v1.Consumer(buffer, consTime);
					j++;
				}
			}
		}

		/*
		 * for (int i = 0; i < nProd; i++) { prods[i] = new Producer(buffer, minProd,
		 * maxProd); }
		 * 
		 * for (int j = 0; j < nCons; j++) { consos[j] = new Consumer(buffer, consTime);
		 * }
		 * 
		 * for (int j = 0; j < nCons; j++) { consos[j].join(); }
		 */
	}
}
