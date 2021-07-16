package multithread.escape;

import java.util.*;

public class ThisEscape {
    private final int num;

    public ThisEscape(EventSource source) {
        source.registerListener(
                new EventListener() {
                    public void onEvent() {
                        doSomething();
                    }
                });
        num = 42;
    }

    private void doSomething() {
        if (num != 42) {
            System.out.println("Race condition detected at " + new Date());
        }
    }
}