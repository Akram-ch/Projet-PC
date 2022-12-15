package prodcons.v1;

import prodcons.Message;

public class Consumer extends Thread {
	private ProdConsBuffer buffer;
	private int consTime;

	public Consumer(ProdConsBuffer b, int time) {
		buffer = b;
		consTime = time;
		this.start();
	}

	public void run() {
		Message msg = null;
		while (true) {
			try {
				msg = buffer.get();
				if (msg == null)
					continue;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(consTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
