package javathread;

import java.util.concurrent.atomic.AtomicLong;

public class RandomNumGen {
    private AtomicLong x;
    private long longValue;

    public RandomNumGen(long seed) {
        if (seed == 0) {
            throw new IllegalArgumentException("seed == 0");
        }
        x = new AtomicLong(seed);
        longValue = x.get();
    }

    public long next() {  // Marsaglia's XorShift
        long expected = x.get();
        longValue = expected;
        longValue ^= longValue >>> 12;
        longValue ^= longValue << 25;
        longValue ^= longValue >>> 27;
        x.compareAndSet(expected, longValue);
        return x.get() * 2685821657736338717L;
    }

    public static void main(String[] args) {
        RandomNumGen rng = new RandomNumGen(1);
        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(rng.next());
                }
            }).start();

        }
    }
}
