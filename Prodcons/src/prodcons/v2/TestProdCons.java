package prodcons.v2;

import java.io.IOException;
import java.util.Arrays;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.Random;

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

		System.out.println("maxProd = " + maxProd);
		System.out.println("minProd = " + minProd);
		Producer[] prods = new Producer[nProd];
		Consumer[] consos = new Consumer[nCons];
		System.out.println("nProds = " + nProd);
		System.out.println("nCons = " + nCons);
		System.out.println("buffer Size = " + bufSz);

		Producer.producersAlive = nProd;

		Random rand = new Random();
		int i = 0;
		int j = 0;
		boolean choice;
		while (i < nProd || j < nCons) {
			choice = rand.nextBoolean();
			if (choice) {
				if (i < nProd) {
					prods[i] = new Producer(buffer, minProd, maxProd);
					i++;
				}
			} else {
				if (j < nCons) {
					consos[j] = new Consumer(buffer, consTime);
					j++;
				}
			}
		}

		for (int k = 0; k < nCons; k++) {
			consos[k].join();
		}

		System.out.println("Traitement des messages terminé");
	}
}
