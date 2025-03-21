class X {
    int deno = 1;
    int num = 1;
    int factorial = 1;
    double totalSum = 0;
    int set = 0;
    Object lock = new Object();
    public X() {
        num = 1;
    }
    public void computeDeno() {
        synchronized (lock) {
            while (set == 1) { 
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); 
                    e.printStackTrace();
                }
            }

            factorial = 1; 
            for (int i = 1; i <= deno; i++) {
                factorial *= i;
            }
            deno++;
            set = 1;

            lock.notifyAll(); 
        }
    }

    public void computeSum() {
        synchronized (lock) {
            while (set == 0) { 
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }

            totalSum += (double) num / factorial;
            System.out.print(num + "/" + factorial + " + ");

            set = 0;
            lock.notifyAll(); 
        }
    }

    public void printResult() {
        System.out.println(" = " + totalSum);
    }

}

public class q3 {
    public static void main(String[] args) {
        X x = new X();
        Thread t1 = new Thread( new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    x.computeDeno();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    x.computeSum();
                }
            }
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        x.printResult();
    }
}
