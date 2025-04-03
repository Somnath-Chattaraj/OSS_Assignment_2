import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Sum {
    private int num;
    private final Object lock = new Object();
    private int sum = 0;
    private boolean hasNewNumber = false;
    private boolean isFileEnd = false; 

    public void readNum(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                synchronized (lock) {
                    while (hasNewNumber) {  
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    num = Integer.parseInt(line.trim());
                    System.out.println("Produced: " + num);
                    hasNewNumber = true;
                    lock.notifyAll();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            isFileEnd = true;
            lock.notifyAll();
        }
    }

    public void printSum() {
        while (true) {
            synchronized (lock) {
                while (!hasNewNumber && !isFileEnd) { 
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                
                if (isFileEnd && !hasNewNumber) break; 

                sum += num;
                System.out.println("Consumed, Current Sum: " + sum);
                hasNewNumber = false;
                lock.notifyAll();
            }
        }
        System.out.println("Final Sum: " + sum);
    }
}

public class q4 {
    public static void main(String[] args) {
        String filePath = "numbers.txt"; 
        Sum sharedSum = new Sum();

        Thread producer = new Thread(() -> sharedSum.readNum(filePath));
        Thread consumer = new Thread(sharedSum::printSum);

        producer.start();
        consumer.start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
