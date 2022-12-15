package prodcons.v3;

import prodcons.Message;

public class Consumer extends Thread {
	private ProdConsBuffer buffer;
	private int consTime;
	static int nbLectures = 0;
	static int nbread = 0;

	public Consumer(ProdConsBuffer b, int time) {
		this.setDaemon(true);
		buffer = b;
		consTime = time;
		this.start();
	}

	public void run() {
		while (true) {

			Message msg = new Message();
			try {
				msg = buffer.get();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(consTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}