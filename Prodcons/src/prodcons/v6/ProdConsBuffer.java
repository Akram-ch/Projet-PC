/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v6;

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
        mul = new Semaphore(1, true);

    }

    public synchronized void put(Message m) throws InterruptedException {

        while (nempty < 1) {
            wait();
        }

        buffer[in] = m;

        nfull++;
        m.setId(totmsg);
        System.out.println("Le producteur : " + Thread.currentThread().getName() + " , produit le Message : " + m.getId() + " dans la case " + in);
        in = (in + 1) % buffSize;
        totmsg++;
        nempty--;
        notifyAll();
        while (m.getNbCopy() > 0) {
            wait();
        }
    }

    public synchronized void put(Message m, int n) throws InterruptedException {
        m.setNbCopy(n);
        while (nempty < 1) {
            wait();
        }

        buffer[in] = m;

        nfull++;
        m.setId(totmsg);
        System.out.println("Le producteur : " + Thread.currentThread().getName() + " , produit " + n + " copies du Message : " + m.getId() + " dans la case " + in);
        in = (in + 1) % buffSize;
        totmsg++;
        nempty--;
        while (m.getNbCopy() > 0) {
            wait();
        }

        notifyAll();
    }

    public Message get() throws InterruptedException {
        mul.acquire();
        Message m;
        synchronized (this) {
            while (nfull < 1) {
                wait();
            }
            m = buffer[out];
            System.out.println("Le consommateur : " + Thread.currentThread().getName() + " , consomme (1 à la fois ) la " + m.getNbCopy() + "e copie du Message : " + m.getId() + " dans la case " + out);

            if (buffer[out].getNbCopy() <= 1) {
                nempty++;
                nfull--;
                out = (out + 1) % buffSize;
            }
            m.consCopy();
            mul.release();
            while (m.getNbCopy() > 0) {
                wait();
            }

            notifyAll();
        }
        // mul.release();
        return m;
    }

    public Message[] get(int k) throws InterruptedException {
        mul.acquire();
        Message[] msgs = new Message[k];
        synchronized (this) {

            for (int i = 0; i < k; i++) {
                while (nfull < 1) {
                    wait();
                }
                msgs[i] = buffer[out];
                System.out.println("Le consommateur : " + Thread.currentThread().getName() + " , consomme (" + k + " successifs) le Message : " + buffer[out].getId() + " dans la case " + out);

                if (buffer[out].getNbCopy() <= 1) {
                    nempty++;
                    nfull--;
                    out = (out + 1) % buffSize;
                }
                buffer[out].consCopy();
                

                notifyAll();
            }
            
            mul.release();
            while (buffer[out].getNbCopy() > 0) {
                    wait();
                }
            
        }

        
        return msgs;
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
