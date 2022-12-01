package prodcons.v3;

import java.util.concurrent.Semaphore;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class ProdConsBuffer implements IProdConsBuffer {
	private Semaphore notFull;
	private Semaphore notEmpty;
	private Semaphore mutex;
	private Message[] messages;
	private int buffSize;
	private int nfull;
	private int nempty;
	private int in;
	private int out;
	private int totmsg;

	public ProdConsBuffer(int buffSz) {
		messages = new Message[buffSz];
		notFull = new Semaphore(buffSz);
		notEmpty = new Semaphore(0);
		mutex = new Semaphore(1);
		buffSize = buffSz;
		nfull = 0;
		nempty = buffSize;
		in = 0;
		out = 0;
		totmsg = 0;

	}

	@Override
	public void put(Message m) throws InterruptedException {
		// TODO Auto-generated method stub
		notFull.acquire();
		mutex.acquire();
		messages[in] = m;
		in = (in + 1) % buffSize;
		totmsg++;
		mutex.release();
		notEmpty.release();

	}

	@Override
	public Message get() throws InterruptedException {
		// TODO Auto-generated method stub
		notEmpty.acquire();
		mutex.acquire();
		Message result = messages[out];
		out = (out + 1) % buffSize;
		mutex.release();
		notFull.release();
		return result;
	}

	@Override
	public int nmsg() {
		// TODO Auto-generated method stub
		return notFull.availablePermits();
	}

	@Override
	public int totmsg() {
		// TODO Auto-generated method stub
		return totmsg;
	}

}