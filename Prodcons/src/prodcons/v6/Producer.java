/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v6;

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

		int k = r.nextInt(10) + 1;
		Long prodId = this.getId();

		for (int i = 0; i < generated; i++) {
			Message msg = new Message(prodId);

			try {
				buffer.put(msg, k); // TODO Auto-generated catch block
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}
