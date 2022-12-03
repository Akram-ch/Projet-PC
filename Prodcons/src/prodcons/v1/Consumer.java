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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Message Id "+ msg.getId() + " Produced by thred n: "+ msg.getContent());
		}
	}
}
