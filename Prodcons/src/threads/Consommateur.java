package threads;


import prodcons.IProdConsBuffer;
import prodcons.Message;

public class Consommateur extends Thread{
	IProdConsBuffer buffer;
	int consTime;
	public Consommateur(IProdConsBuffer b, int time) {
		buffer = b;
		consTime = time;
		this.start();
	}
	
	public void run() {
		
		Message msg = null;
		try {
			msg = buffer.get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Thread.sleep(consTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(msg.getContent());
		
	}
}
