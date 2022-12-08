/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v5;

import java.util.concurrent.Semaphore;

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
     Semaphore mul;

    public ProdConsBuffer(int bufsize) {
        this.buffer = new Message[bufsize];
        this.in = 0;
        this.out = 0;
        this.nempty = bufsize;
        this.buffSize = bufsize;
        this.nfull = 0;
        this.totmsg = 0;
        mul = new Semaphore(1,true);
        
    }

    public synchronized void put(Message m) throws InterruptedException {

        while (nempty < 1) {
            wait();
        }

        buffer[in] = m;
        in = (in + 1) % buffSize;
        nfull++;
        m.setId(totmsg);
        System.out.println("Le producteur : " + Thread.currentThread().getName() + " , produit le Message : "+m.getId()+" dans la case "+ in);
        totmsg++;
        nempty--;
        notifyAll();
    }

    public Message get() throws InterruptedException {
        mul.acquire();
        Message m;
        synchronized(this){
        while (nfull < 1) {
            wait();
        }
         m = buffer[out];
         System.out.println("Le consommateur : " + Thread.currentThread().getName() + " , consomme (1) le Message : " + m.getId()+" dans la case "+ out);
        out = (out + 1) % buffSize;
        
        nempty++;
        nfull--;
        notifyAll();
        }
        mul.release();
        return m;
    }

    public Message[] get(int k) throws InterruptedException {
        mul.acquire();
        Message[] msgs = new Message[k];
        synchronized(this){
        
        for (int i = 0; i < k; i++) {
            while (nfull < 1) {
                wait();
            }
            msgs[i] = buffer[out];
            System.out.println("Le consommateur : " + Thread.currentThread().getName() + " , consomme ("+k+") le Message : " + buffer[out].getId()+" dans la case "+ out);
            out = (out + 1) % buffSize;
            
            nempty++;
            nfull--;
            notifyAll();
        }

        }

        mul.release();
        return msgs;
    }

	@Override
	public void put(Message m, int nb) throws InterruptedException {
		// TODO Auto-generated method stub
		
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
