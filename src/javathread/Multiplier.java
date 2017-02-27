package javathread;

import java.util.concurrent.*;

/**
 * Created by jyang on 27/02/2017.
 */
public class Multiplier {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<Integer>[] futures = new Future[20];
        for(int i =0; i<20; i++) {
            int T = i*10;
            int finalI = i;
            futures[i] = executorService.submit(
                    new Callable<Integer>() {
                        @Override
                        public Integer call(){
                            return finalI*10;
                        }
                    }
            );
        }
        for (int i = 0; i<20; i++) {
            System.out.println(futures[i].get());
        }
    }
}
