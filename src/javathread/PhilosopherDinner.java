package javathread;


import java.util.concurrent.locks.ReentrantLock;

public class PhilosopherDinner {

    private final ReentrantLock[] forks;

    public PhilosopherDinner(int forkCount) {
        ReentrantLock[] forks = new ReentrantLock[forkCount];
        for (int i = 0; i < forkCount; i++) {
            forks[i] = new ReentrantLock();
        }
        this.forks = forks;
    }

    public void eat(int index) {
        ReentrantLock fork1 = forks[index];
        ReentrantLock fork2 = forks[(index + 1) % forks.length];
        if(!fork1.isLocked()){
            fork1.lock();
        }
        if(!fork2.isLocked()){
            fork2.lock();
        }
        if(fork1.isHeldByCurrentThread()&&fork2.isHeldByCurrentThread()){

            fork2.unlock();
            fork1.unlock();
            System.out.println("philosopher " + index + " eat");
        }
    }

    public static void main(String[] args) {
        PhilosopherDinner dinner = new PhilosopherDinner(5);
        for (int i = 0; i < 5; i++) {
            final int philosopher = i;
            new Thread(new Runnable() {

                @Override
                public void run() {
                    for(;;) {
                        dinner.eat(philosopher);
                    }
                }

            }).start();
        }
    }
}
