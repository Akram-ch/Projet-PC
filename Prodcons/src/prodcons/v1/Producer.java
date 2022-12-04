package prodcons.v1;

import java.util.Random;

import prodcons.Message;

public class Producer extends Thread {
	private ProdConsBuffer buffer;
	private int minProd, maxProd;
	private static int nextId = 0;

	
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
		String cont = Long.toString(myId);
		for (int i = 0; i < generated; i++) {
			nextId ++;
			Message msg = new Message(cont, nextId);
			try {
				buffer.put(msg);
				System.out.println(msg.getId());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
