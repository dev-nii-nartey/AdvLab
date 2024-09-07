public class ThreadInterruptDemo extends Thread {
    public void run() {
        try {
            while (true) {
// Check if it is interrupted, if so then throw InterruptedException
                if (Thread.interrupted()) {
                    throw new InterruptedException();
                }
// else continue working
                System.out.println("Continue working");
                Thread.sleep(2000L);
            }
        } catch (InterruptedException e) {
// Handling InterruptedException and Graceful shutdown of the Thread
            System.out.println("Graceful shutdown");
        }
    }

    public void main() {
        ThreadInterruptDemo t1 = new ThreadInterruptDemo();
        t1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
// main Thread decided that ThreadInterruptDemo is no longer needed, so interrupting it
        t1.interrupt();
    }

}



