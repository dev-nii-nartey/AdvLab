package concurrencyDemo;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Thread 1: Writes to the map
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                map.put("Key" + i, i);
                System.out.println("Writer: Added Key" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2: Reads from the map
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("Reader: Value for Key" + i + " is " + map.get("Key" + i));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.start();
        reader.start();

        writer.join();
        reader.join();

        System.out.println("Final map contents: " + map);
    }
}
