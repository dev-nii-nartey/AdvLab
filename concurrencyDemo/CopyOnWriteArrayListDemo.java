package concurrencyDemo;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {
    public static void main(String[] args) throws InterruptedException {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // Thread 1: Writes to the list
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                list.add("Element" + i);
                System.out.println("Writer: Added Element" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Thread 2: Reads from the list
        Thread reader = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                if (i < list.size()) {
                    System.out.println("Reader: Value at index " + i + " is " + list.get(i));
                } else {
                    System.out.println("Reader: Index " + i + " not available yet");
                }
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

        System.out.println("Final list contents: " + list);
    }
}
