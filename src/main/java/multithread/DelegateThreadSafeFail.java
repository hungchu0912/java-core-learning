package multithread;

import java.util.concurrent.atomic.AtomicInteger;

/*
This class uses two AtomicIntegers to manage its state, but imposes an additional constraint — that the first number be less than or equal to the second.
However, it is not thread safe, the attempt check-then-act and do not have sufficient locking to make them atomic
 */
public class DelegateThreadSafeFail {
    private final AtomicInteger lower = new AtomicInteger(0);
    private final AtomicInteger upper = new AtomicInteger(10);

    public void setLower(int i) {
        if (i > upper.get()) {
            System.out.println("Can't set lower to " + i);
        }
        else {
            lower.set(i);
            terminateIfViolate();
        }
    }

    public void setUpper(int i) {
        if (i < lower.get()) {
            System.out.println("Can't set upper to " + i);
        }
        else {
            upper.set(i);
            terminateIfViolate();
        }
    }

    private void terminateIfViolate() {
        if (lower.get() > upper.get()) {
            System.out.println("Violated");
            System.out.println("lower: " + lower.get() + ", upper: " + upper.get());
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        while (true) {
            DelegateThreadSafeFail delegateThreadSafeFail = new DelegateThreadSafeFail();
            new Thread(() -> delegateThreadSafeFail.setLower(5)).start();
            new Thread(() -> delegateThreadSafeFail.setUpper(4)).start();
        }
    }
}
