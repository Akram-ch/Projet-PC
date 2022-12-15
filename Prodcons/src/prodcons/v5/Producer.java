
package prodcons.v5;

import java.util.Random;
import java.util.concurrent.Semaphore;

import prodcons.Message;

public class Producer extends Thread {

    Semaphore mutex;
    ProdConsBuffer buffer;
    int minProd, maxProd;

    public Producer(ProdConsBuffer b, int min, int max) {
        buffer = b;
        minProd = min;
        maxProd = max;
        this.start();
    }

    public void run() {
        Random r = new Random();
        int generated = r.nextInt(maxProd - minProd) + minProd;

        Long myId = this.getId();

        for (int i = 0; i < generated; i++) {
            Message msg = new Message(myId);

            try {
                buffer.put(msg); // TODO Auto-generated catch block
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
