import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedTest2 {
    private static final ReentrantLock lockA = new ReentrantLock();
    private static final ReentrantLock lockB = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                lockB.lock();
                System.out.println("Thread 1: Holding lock B...");
                Thread.sleep(100);
                System.out.println("Thread 1: Waiting for lock A...");
                lockA.lock();
                System.out.println("Thread 1: Holding lock A and B...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockB.unlock();
                lockA.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lockA.lock();
                System.out.println("Thread 2: Holding lock A...");
                Thread.sleep(100);
                System.out.println("Thread 2: Waiting for lock B...");
                lockB.lock();
                System.out.println("Thread 2: Holding lock A and B...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lockA.unlock();
                lockB.unlock();
            }
        });

        thread1.start();
        thread2.start();
    }
}
