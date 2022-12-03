package prodcons.v1;

import java.util.Random;

import prodcons.Message;

public class Producer extends Thread {
	private ProdConsBuffer buffer;
	private int minProd, maxProd;
	private int msgId;
	
	public Producer(ProdConsBuffer b, int min, int max) {
		buffer = b;
		minProd = min;
		maxProd = max;
		this.start();
	}
	
	public void run() {
		Random r = new Random();
		int generated = r.nextInt(maxProd-minProd) + minProd;
		System.out.println("Message produced "+ this.getId());
		
		Long myId = this.getId();
		String cont = Long.toString(myId);
		for (int i = 0; i < generated; i++) {
			Message msg = new Message(cont);
			try {
				buffer.put(msg);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
