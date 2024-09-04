package concurrencyDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyDemo {

    static class ProgramTask implements Runnable {
        private final String name;

        public ProgramTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                System.out.println("Executing " + name);
                try {
                    Thread.sleep(100); // simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }





    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new ProgramTask("CONCURRENCY PROGRAM ONE"));
        executor.submit(new ProgramTask("concurrency program two"));

        executor.shutdown();
    }
}


