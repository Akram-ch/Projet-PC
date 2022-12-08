/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prodcons.v5;

import java.util.Random;

import prodcons.Message;



/**
 *
 * @author zmefo
 */
public class Consumer extends Thread {
    
    ProdConsBuffer buf;
    Message[] msgs;
    
    int consTime;
    
    public Consumer( ProdConsBuffer buf, int consTime){
        this.buf = buf;
        this.consTime = consTime;
        this.start();
    }
    
    public void run(){
        Random r = new Random();
        int k = r.nextInt(10);
        while(true){
            try {
                if(k==1){
                Message m = buf.get();
                }else{
                    msgs = buf.get(k);
                }
                sleep(consTime);
               
            } catch (InterruptedException ex) {
            	ex.printStackTrace();
            }
        }
    }
    
}
