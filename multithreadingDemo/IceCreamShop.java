package multithreadingDemo;

public class IceCreamShop {
    public static void main(String[] args) {
        System.out.println("Welcome to the Ice Cream Shop!");

        // Shared resource
        IceCreamMachine machine = new IceCreamMachine();

        // Create two threads
        Thread maker1 = new Thread(new IceCreamMaker("Alice", machine));
        Thread maker2 = new Thread(new IceCreamMaker("Bob", machine));

        // Start the threads
        maker1.start();
        maker2.start();

        try {
            // Wait for both threads to finish
            maker1.join();
            maker2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Ice cream shop is closing!");
    }
}



class IceCreamMachine {
    public synchronized void makeIceCream(String makerName) {
        System.out.println(makerName + " is starting to make ice cream.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(makerName + " has finished making ice cream.");
    }
}




class IceCreamMaker implements Runnable {
    private final String name;
    private final IceCreamMachine machine;

    public IceCreamMaker(String name, IceCreamMachine machine) {
        this.name = name;
        this.machine = machine;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            machine.makeIceCream(name);
        }
    }
}
