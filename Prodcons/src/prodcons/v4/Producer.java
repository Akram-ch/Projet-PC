
package prodcons.v4;

import java.util.Random;
import prodcons.Message;

public class Producer extends Thread {

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
        // System.out.println("Message produced " + this.getId());

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
