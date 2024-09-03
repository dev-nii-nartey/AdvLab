package concurrencyDemo;

import java.util.concurrent.*;
import java.util.List;

public class ConcurrentCollectionsDemo {

    private static final ConcurrentHashMap<String, Integer> programCounts = new ConcurrentHashMap<>();
    private static final List<String> executionLog = new CopyOnWriteArrayList<>();
    private static final BlockingQueue<String> taskQueue = new LinkedBlockingQueue<>();

    static class ProgramTask implements Runnable {
        private final String name;

        public ProgramTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                // Update count in ConcurrentHashMap
                programCounts.compute(name, (k, v) -> (v == null) ? 1 : v + 1);

                // Add to CopyOnWriteArrayList
                executionLog.add(name + " executed " + (i + 1) + " times");

                // Add task to BlockingQueue
                try {
                    taskQueue.put(name + " task " + (i + 1));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                try {
                    Thread.sleep(100); // simulate some work
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new ProgramTask("Program One"));
        executor.submit(new ProgramTask("Program Two"));

        // Start a consumer thread for the BlockingQueue
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    String task = taskQueue.take();
                    System.out.println("Processed: " + task);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        // Print results
        System.out.println("\nProgram Counts: " + programCounts);
        System.out.println("\nExecution Log (last 10 entries):");
        int logSize = executionLog.size();
        for (int i = Math.max(0, logSize - 10); i < logSize; i++) {
            System.out.println(executionLog.get(i));
        }
    }
}
