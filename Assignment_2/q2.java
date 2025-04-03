
class PrintNumbers {
    int num;
    Object lock = new Object();
    public PrintNumbers() {
        num = 1;
    }
    public void printEvenNumbers() {
        synchronized(lock) {
            if (num%2 == 0) {
                System.out.print(num + " ");
                num++;
                lock.notify();
            } else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void printOddNumber() {
        synchronized(lock) {
            if (num%2 == 1) {
                System.out.print(num + " ");
                num++;
                lock.notify();
            } else {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

public class q2 {
    public static void main(String[] args) {
        PrintNumbers printNumbers = new PrintNumbers();
        Thread EvenThread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    printNumbers.printEvenNumbers();
                }
            }
        });
        Thread OddThread = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    printNumbers.printOddNumber();
                }
            }
        });
        EvenThread.start();
        OddThread.start();
    }
}
