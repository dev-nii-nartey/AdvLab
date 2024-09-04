package multithreadingDemo;

public class MultiThreadingDemo extends Thread {
    private final String programName;

    public MultiThreadingDemo(String programName) {
        this.programName = programName;
    }

    public void run() {
        int count = 0;
        while (count < 20) {
            System.out.println(programName);
            count++;
            try {
                Thread.sleep(100); // simulate some work making the program's execution more realistic and allowing us to see the interleaving of different threads more clearly
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }












    public static void main(String[] args) {
        MultiThreadingDemo programOne = new MultiThreadingDemo("program one ");
        MultiThreadingDemo programTwo = new MultiThreadingDemo(" PROGRAM TWO");
        programOne.start();
        programTwo.start();

    }
}



