import java.util.concurrent.atomic.AtomicInteger;

public class q5 {
    public static void main(String[] args) {
        AtomicInteger sum1 = new AtomicInteger(0);
        AtomicInteger sum2 = new AtomicInteger(0);

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 100; i += 2) {
                sum1.addAndGet(i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 2; i <= 100; i += 2) {
                sum2.addAndGet(i);
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

        System.out.println("Sum of all numbers: " + (sum1.get() + sum2.get())); // Correct sum calculation
    }
}
