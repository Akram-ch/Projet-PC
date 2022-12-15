package prodcons.v1;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class ProdConsBuffer implements IProdConsBuffer {
	private Message[] messages;
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
		totmsg = 0;
		in = 0;
		out = 0;
	}

	@Override
	public synchronized void put(Message m) throws InterruptedException {
		// TODO Auto-generated method stub
		while (nempty < 1)
			wait();
		messages[in] = m;
		in = (in + 1) % buffSize;
		m.setId(totmsg);
		totmsg++;
		nfull++;
		nempty--;
		System.out.println("Message " + m.getId() + " produced by " + Thread.currentThread().getName());
		notifyAll();
	}

	@Override
	public synchronized Message get() throws InterruptedException {
		// TODO Auto-generated method stub
		while (nfull < 1)
			wait();
		Message result = messages[out];
		System.out.println("Message " + result.getId() + " Produced by thred n: " + result.getContent()
				+ " Consumed by " + Thread.currentThread().getName() + "dans la case " + out);
		out = (out + 1) % buffSize;
		nempty++;
		nfull--;
		notifyAll();
		return result;
	}

	@Override
	public int nmsg() {
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
