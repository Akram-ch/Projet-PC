/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v6;

import java.util.Random;

import prodcons.Message;

public class Consumer extends Thread {

	ProdConsBuffer buf;
	Message[] msgs;

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
