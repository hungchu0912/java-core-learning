package multithread.escape;

/*
Source: https://www.javaspecialists.eu/archive/Issue192-Implicit-Escape-of-this.html
This example demonstrate an example of implicit escape of "this"
ThisEscape class register an anonymous inner class, even though that the num would assign to 42 in the constructor,
however the pointer of ThisEscape still leaked, therefore the condition `num != 42` sometimes true
- Main thread: creating ThisEscape
- EventSource thread: running with ThisEscape instance
 */
public class ThisEscapeTest {
    public static void main(String[] args) {
        EventSource es = new EventSource();
        es.start();
        while(true) {
            new ThisEscape(es);
        }
    }
}
