package Javathread;


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
            if(!fork2.isLocked()){
                fork1.lock();
                fork2.lock();
                System.out.println("philosopher " + index + " eat");
                fork1.unlock();
                fork2.unlock();
            }
        }
        fork1.unlock();
        fork2.unlock();
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
