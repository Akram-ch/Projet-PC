package prodcons.v3;

import java.util.Random;

import prodcons.Message;

public class Producer extends Thread {

	private ProdConsBuffer buffer;
	private int minProd, maxProd;
	private static int nextId = 0;
	private static int producersAlive = 0;

	public Producer(ProdConsBuffer b, int min, int max) {
		buffer = b;
		minProd = min;
		maxProd = max;
		synchronized (this) {
			producersAlive++;
		}
		this.start();
	}

	public static int nbAlive() {
		return producersAlive;
	}
	
	public void run() {
		Random r = new Random();
		int generated = r.nextInt(maxProd - minProd) + minProd;
		Long myId = this.getId();
		String cont = Long.toString(myId);
		for (int i = 0; i < generated; i++) {
			nextId++;
			Message msg = new Message(cont, nextId);
			try {
				buffer.put(msg);
				System.out.println("Message produced " + msg.getId());

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized (this) {
			producersAlive--;
		}
	}
}