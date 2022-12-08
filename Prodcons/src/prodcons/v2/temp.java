package prodcons.v1;

import java.util.Random;

import prodcons.Message;

public class Producer extends Thread {
	private ProdConsBuffer buffer;
	private int minProd, maxProd;
	static int nextId = 0;

	
	public Producer(ProdConsBuffer b, int min, int max) {
		buffer = b;
		minProd = min;
		maxProd = max;
		this.start();
	}
	
	public void run() {
		Random r = new Random();
		int generated = r.nextInt(maxProd-minProd) + minProd;
		
		Long myId = this.getId();
		for (int i = 0; i < generated; i++) {
			//nextId ++;
			Message msg = new Message(myId, nextId);
			try {
				buffer.put(msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
