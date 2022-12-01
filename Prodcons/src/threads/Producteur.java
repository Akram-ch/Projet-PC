package threads;

import java.util.Random;
import java.util.concurrent.Semaphore;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class Producteur extends Thread {
	Semaphore mutex;
	IProdConsBuffer buffer;
	int minProd, maxProd;
	public Producteur(IProdConsBuffer b, int min, int max) {
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
