package prodcons.v3;

import java.util.concurrent.Semaphore;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class ProdConsBuffer implements IProdConsBuffer {

	Message[] messages;
	private int buffSize;
	private int in;
	private int out;
	private int totmsg;
	private Semaphore notFull;
	private Semaphore notEmpty;
	private Semaphore mutexIn;
	private Semaphore mutexOut;

	ProdConsBuffer(int bsize) {
		buffSize = bsize;
		messages = new Message[buffSize];
		in = 0;
		out = 0;
		totmsg = 0;
		notFull = new Semaphore(buffSize, true);
		notEmpty = new Semaphore(0, true);
		mutexIn = new Semaphore(1, true);
		mutexOut = new Semaphore(1, true);
	}

	public void put(Message m) throws InterruptedException {
		try {
			notFull.acquire();
			mutexIn.acquire();
			messages[in] = m;
			in = (in + 1) % buffSize;
			totmsg++;
			Producer.nextId++;
			System.out.println("Message" + m.getId() + " produced by " + Thread.currentThread().getName());
			//Consumer.nbLectures++;
		} finally {
			//printBuffer();
			mutexIn.release();
			notEmpty.release();
		}

	}

	@Override
	public Message get() throws InterruptedException {
		Message result = new Message();
		try {
			notEmpty.acquire();
			mutexOut.acquire();
			result = messages[out];
			//Consumer.nbLectures--;
			Consumer.nbread++;
			out = (out + 1) % buffSize;
		} finally {
			//printBuffer();
			System.out.println("Message Id " + result.getId() + " Produced by thred n: " + result.getContent() + " Consumed by " + Thread.currentThread().getName());
			mutexOut.release();
			notFull.release();
		}
		return result;
	}

	@Override
	public int nmsg() {
		return notEmpty.availablePermits();
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
