package prodcons.v3;

import java.util.Random;

import prodcons.Message;

public class Producer extends Thread {

	private ProdConsBuffer buffer;
	private int minProd, maxProd;
	static int nextId = 0;
	static int producersAlive = 0;

	public Producer(ProdConsBuffer b, int min, int max) {
		buffer = b;
		minProd = min;
		maxProd = max;
		this.start();
	}

	public static int nbAlive() {
		return producersAlive;
	}

	public void run() {
		Random r = new Random();
		int generated = r.nextInt(maxProd - minProd) + minProd;
		Long prodId = this.getId();
		for (int i = 0; i < generated; i++) {

			Message msg = new Message(prodId);
			try {
				buffer.put(msg);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}