/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v6;

import java.util.Random;
import java.util.concurrent.Semaphore;
import prodcons.Message;

/**
 *
 * @author zmefo
 */
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
		// System.out.println("Message produced " + this.getId());
		int k = r.nextInt(10);
		Long myId = this.getId();
		String cont = Long.toString(myId);
		for (int i = 0; i < generated; i++) {
			Message msg = new Message(myId);
			// System.out.println("Le producteur :" + this.getName() + ", produit le Message
			// : " + msg.getAuthor());
			try {
				buffer.put(msg, k); // TODO Auto-generated catch block
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
