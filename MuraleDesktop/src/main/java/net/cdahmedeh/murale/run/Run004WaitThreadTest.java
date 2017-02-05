package net.cdahmedeh.murale.run;

import net.cdahmedeh.murale.flow.Flow;

/**
 * Created by cdahmedeh on 2/1/2017.
 */
public class Run004WaitThreadTest {
    public static void main(String[] args) {
        Flow flow = new Flow();

        flow.pause(10);

        System.out.println("Doing something");
        System.out.println("Doing something");
    }
}
