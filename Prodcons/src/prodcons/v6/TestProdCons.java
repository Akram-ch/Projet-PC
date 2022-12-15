
package prodcons.v6;

import java.io.IOException;
import java.util.Properties;

public class TestProdCons {

    /* */
    public static void main(String[] args) throws IOException, InterruptedException {
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
        int l = buffer.mul.getQueueLength();
        Producer[] prods = new Producer[nProd];
        Consumer[] consos = new Consumer[nCons];
        for (int i = 0; i < nProd; i++) {
            prods[i] = new Producer(buffer, minProd, maxProd);
        }

        for (int j = 0; j < nCons; j++) {
            consos[j] = new Consumer(buffer, consTime);
        }
        for (int i = 0; i < nProd; i++) {
            prods[i] = new Producer(buffer, minProd, maxProd);
        }
        if (consos.length > 0)
            consos[0].join();

    }
}
