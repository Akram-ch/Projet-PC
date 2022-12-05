package prodcons.v3;

import prodcons.Message;

public class Consumer extends Thread {
	private ProdConsBuffer buffer;
	private int consTime;
	static int nbLectures = 0;
	private static int nbread = 0;

	public Consumer(ProdConsBuffer b, int time) {
		buffer = b;
		consTime = time;
		this.start();
	}

	public void run() {
		while (true) {

			Message msg = new Message();
			try {
				msg = buffer.get();
				nbread++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(consTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Producers alive : " + Producer.nbAlive());
			System.out.println("Nb lectures : " + nbLectures);
			System.out.println("Messages read : " + nbread);

			// System.out.println("Message Id "+ msg.getId() + " Produced by thred n: "+
			// msg.getContent());

		}
	}
}