public class ResolveDeadLockTest {

    //Resource A
    private class ResourceA {
        private int i = 10;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }

    // Resource B
    private class ResourceB {
        private int i = 20;

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }
    }


    public static void main(String[] args) {
        ResolveDeadLockTest test = new ResolveDeadLockTest();
        final ResourceA resourceA = test.new ResourceA();
        final ResourceB resourceB = test.new ResourceB();
// Thread-1
        Runnable block1 = new Runnable() {
            public void run() {
                synchronized (resourceB) {
                    try {
// Adding delay so that both threads can start trying to lock resources
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
// Thread-1 have ResourceA but need B also
                    synchronized (resourceA) {
                        System.out.println("In block 1");
                    }
                }
            }
        };
// Thread-2
        Runnable block2 = new Runnable() {
            public void run() {
                synchronized (resourceB) {
// Thread-2 have B but need ResourceA also
                    synchronized (resourceA) {
                        System.out.println("In block 2");
                    }
                }
            }
        };
        new Thread(block1).start();
        new Thread(block2).start();
    }
}