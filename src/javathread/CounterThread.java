/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javathread;

import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class CounterThread implements Runnable {

    private Thread th1,th2,th3,th4;
    private int counter;
    private ArrayList list;
    
    public CounterThread(){
        th1 = new Thread(this);
        th2 = new Thread(this);
        th3 = new Thread(this);
        th4 = new Thread(this);
        list = new ArrayList();
        th1.start();
        th2.start();
        th3.start();
        th4.start();
    }
    @Override
    public synchronized void run() {
        for(counter=0;counter<100;counter++){
            list.add(counter);
            //System.out.println(counter+" : "+Thread.currentThread().getId());
        }
    }
    public static void main(String[] args) {
        CounterThread test = new CounterThread();
        while(test.th1.getState()!=Thread.State.TERMINATED&&test.th2.getState()!=Thread.State.TERMINATED&&test.th3.getState()!=Thread.State.TERMINATED&&test.th4.getState()!=Thread.State.TERMINATED){
    
        }
        System.out.println("Programm finished");
               
    }

    
}
