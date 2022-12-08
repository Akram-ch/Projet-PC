package prodcons.v2;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class ProdConsBuffer implements IProdConsBuffer {
	Message[] messages;
	private int buffSize;
	private int nfull;
	private int nempty;
	private int in;
	private int out;

	private int totmsg;

	public ProdConsBuffer(int bsize) {
		buffSize = bsize;
		messages = new Message[buffSize];
		nfull = 0;
		nempty = buffSize;
		in = 0;
		out = 0;
	}

	@Override
	public synchronized void put(Message m) throws InterruptedException {
		// TODO Auto-generated method stub
		while (nfull > buffSize)
			wait();
		messages[in] = m;
		System.out.println("Message Id " + m.getId() + " Produced by thred n: " + m.getContent());
		in = (in + 1) % buffSize;
		totmsg++;
		nfull++;
		nempty--;
		Consumer.nbLectures++;
		Producer.nextId++;
		notifyAll();
	}

	@Override
	public synchronized Message get() throws InterruptedException {
		// TODO Auto-generated method stub
		while (nempty == buffSize)
			wait();
		Message result = messages[out];

		Consumer.nbLectures--;
		nempty++;
		nfull--;

		out = (out + 1) % buffSize;
		System.out.println("Message Id " + result.getId() + " Produced by thred n: " + result.getContent() + " Consumed by " + Thread.currentThread().getName());
		System.out.println("Messages read : " + Consumer.nbread);
		System.out.println("Producers alive : " + Producer.nbAlive());
		System.out.println("Nb lectures : " + Consumer.nbLectures);
		if (Consumer.nbLectures == 0 && Producer.nbAlive() == 0) {
			System.out.println("Program exiting...");
			System.exit(0);
		}

		Consumer.nbread++;
		notifyAll();
		return result;
	}

	@Override
	public synchronized int nmsg() {
		// TODO Auto-generated method stub
		return nfull;
	}

	@Override
	public int totmsg() {
		// TODO Auto-generated method stub
		return totmsg;
	}

	@Override
	public void put(Message m, int nb) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Message[] get(int nb) throws InterruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
