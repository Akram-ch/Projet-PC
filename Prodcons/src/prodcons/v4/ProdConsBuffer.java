/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v4;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import prodcons.IProdConsBuffer;
import prodcons.Message;

public class ProdConsBuffer implements IProdConsBuffer {

	private Message[] buffer;
	private int nfull;
	private int nempty;
	private int in;
	private int out;
	private int totmsg;
	private int buffSize;
	final Lock lock = new ReentrantLock();
	final Condition cond = lock.newCondition();

	public ProdConsBuffer(int bufsize) {
		this.buffer = new Message[bufsize];
		this.in = 0;
		this.out = 0;
		this.nempty = bufsize;
		this.buffSize = bufsize;
		this.nfull = 0;
		this.totmsg = 0;
	}

	public void put(Message m) throws InterruptedException {
        lock.lock();
        while (nempty < 1) {
            cond.await();
        }
        
        buffer[in] = m;
        in = (in + 1) % buffSize;
        nfull++;
        m.setId(totmsg);
        System.out.println("Le producteur : " + Thread.currentThread().getName() + " , produit le Message : "+m.getId());
        totmsg++;
        nempty--;
        cond.signalAll();
        lock.unlock();
    }

	@Override
	public Message get() throws InterruptedException {
		lock.lock();
		while (nfull < 1) {
			// notFull.await();
			cond.await();
		}
		Message m = buffer[out];
		out = (out + 1) % buffSize;
		System.out.println(
				"Le consommateur : " + Thread.currentThread().getName() + " , consomme le Message : " + m.getId());
		nempty++;
		nfull--;
		cond.signalAll();
		lock.unlock();
		return m;
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

	@Override
	public int nmsg() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int totmsg() {
		// TODO Auto-generated method stub
		return 0;
	}


}
