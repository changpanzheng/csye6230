public class ParallelThreadsExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new MyRunnable(), "Thread_1");
        Thread thread2 = new Thread(new MyRunnable(), "Thread_2");
        Thread thread3 = new Thread(new MyRunnable(), "Thread_3");
        Thread thread4 = new Thread(new MyRunnable(), "Thread_4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}

// override the MyRunnable() for each thread
class MyRunnable implements Runnable {
    public void run() {
        System.out.println("We're currently in -> " + Thread.currentThread().getName());
    }
}
