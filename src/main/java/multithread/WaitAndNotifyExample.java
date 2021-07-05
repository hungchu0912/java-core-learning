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
        new Thread(() -> {
            waitAndNotifyExample.printDummy("t1");
            synchronized (waitAndNotifyExample) {
                waitAndNotifyExample.notifyAll();
            }
        }).start();
        synchronized (waitAndNotifyExample) {
            waitAndNotifyExample.wait();
        }
        new Thread(() -> waitAndNotifyExample.printDummy("t2")).start();
    }

    public void printDummy(String name) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            throw new RuntimeException();
        }
        System.out.println("dummy " + name);
    }
}
