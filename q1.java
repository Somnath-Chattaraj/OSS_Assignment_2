class MainThread extends Thread {
    public void run() {
        System.out.println("Main Thread");
    }
}

class ChildThread extends Thread {
    public void run() {
        System.out.println("Child Thread");
    }
}

class q1 {
    public static void main(String[] args) {
        MainThread mainThread = new MainThread();
        ChildThread childThread = new ChildThread();

        mainThread.start();
        childThread.start();
    }   
}
