
package prodcons.v4;

import prodcons.Message;

public class Consumer extends Thread {

    ProdConsBuffer buf;

    int consTime;

    public Consumer(ProdConsBuffer buf, int consTime) {
        this.buf = buf;
        this.consTime = consTime;
        this.start();
    }

    public void run() {
        while (true) {
            try {
                Message m = buf.get();
                sleep(consTime);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

}
