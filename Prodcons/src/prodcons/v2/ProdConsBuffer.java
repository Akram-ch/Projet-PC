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
		in = (in + 1) % buffSize;
		totmsg++;
		nfull++;
		nempty--;
		Consumer.nbLectures++;
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
		if (Consumer.nbLectures == 0 && Producer.nbAlive() == 0)
			System.exit(0);
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

}
