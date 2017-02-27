package javathread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong {

    private boolean sent = false;
    final Lock lock = new ReentrantLock();
    final Condition pingSent = lock.newCondition();
    final Condition pongSent = lock.newCondition();


    private void ping() {
        sent = true;
        System.out.println("ping");
        this.pingSent.signal();
    }

    private synchronized void pong() throws InterruptedException {
        this.lock.lock();
        try {
            while (sent == false) {
                //This makes the thread to sleep for 100 milliseconds
                //Thread.sleep(0);
                this.pingSent.await();
            } if (sent == true) {
                System.out.println("pong");
                this.pongSent.signal();
            }
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        new Thread(new Runnable() {

            @Override
            public void run() {
                pingPong.lock.lock();
                try {
                    Thread.sleep(2000);
                    pingPong.ping();
                } catch (InterruptedException e) {
                    new AssertionError(e);
                } finally {
                    pingPong.lock.unlock();
                }

            }

        }).start();
        pingPong.pong();
    }

}
