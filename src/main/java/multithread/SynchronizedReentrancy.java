package multithread;

/*
Reentrancy: Reentrancy means that locks are acquired on a per-thread rather than per-invocation basis
synchronize keyword use intrinsic lock that is reentrancy

If the synchronize keyword is not reentrant, the below code would be deadlock
 */
public class SynchronizedReentrancy {

    public static void main(String args[]) {
        new Child().doSomething();
    }

    public static class Parent {
        public synchronized void doSomething() {
            System.out.println("parent dummy");
        }
    }

    public static class Child extends Parent{
        @Override
        public synchronized void doSomething() {
            System.out.println("child dummy");
            super.doSomething();
        }
    }
}
