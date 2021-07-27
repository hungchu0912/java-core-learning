package multithread;

/*
Wait and Notify example
Scenario: Given thread t1 and t2, using wait and notify to make t2 run after t1 (can use main thread to intercept)

Note: In order to invoking wait() or notify() on object, the thread must own the monitor lock of that object or else
IllegalMonitorStateException will be thrown

Reference:
- JLS java 11 page 665
- https://www.baeldung.com/java-wait-notify
 */
public class WaitAndNotifyExample {
    public static void main(String[] args) throws InterruptedException {
        var waitAndNotifyExample = new WaitAndNotifyExample();
        thread1(waitAndNotifyExample).start();
        thread2(waitAndNotifyExample).start();
    }

    public static Thread thread1(WaitAndNotifyExample waitAndNotifyExample) {
        return new Thread(() -> {
            waitAndNotifyExample.printDummy("t1");
            synchronized (waitAndNotifyExample) {
                waitAndNotifyExample.notifyAll();//notify t2
            }
        });
    }

    public static Thread thread2(WaitAndNotifyExample waitAndNotifyExample) {
        return new Thread(() -> {
            synchronized (waitAndNotifyExample) {
                try {
                    waitAndNotifyExample.wait(); //causing t2 to wait until t1 notify
                    waitAndNotifyExample.printDummy("t2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void printDummy(String name) {
        System.out.println("dummy " + name);
    }
}
