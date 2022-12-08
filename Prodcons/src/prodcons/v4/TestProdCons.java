/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v4;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class TestProdCons {


    public static void main(String[] args) throws IOException {
        // TODO code application logic here

        Properties properties = new Properties();
        properties.loadFromXML(TestProdCons.class.getClassLoader().getResourceAsStream("options.xml"));
        int nProd = Integer.parseInt(properties.getProperty("nProd"));
        int nCons = Integer.parseInt(properties.getProperty("nCons"));
        int consTime = Integer.parseInt(properties.getProperty("consTime"));
        int maxProd = Integer.parseInt(properties.getProperty("maxProd"));
        int minProd = Integer.parseInt(properties.getProperty("minProd"));
        int bufSz = Integer.parseInt(properties.getProperty("bufSz"));

        ProdConsBuffer buffer = new ProdConsBuffer(bufSz);

        Producer[] prods = new Producer[nProd];
        Consumer[] consos = new Consumer[nCons];
        
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
    }
}
