package net.cdahmedeh.murale.run;

/**
 * Created by cdahmedeh on 2/26/2017.
 */
public class Run009ClassHierarchyTest {
    public abstract static class UpperClass {
        protected void doSomething() {
            System.out.println("doSomething in UpperClass");
        }

        protected void call() {
            doSomething();
        }
    }

    public static class LowerClass extends UpperClass {
        protected void doSomething() {
            System.out.println("doSomething in LowerClass");
        }
    }

    public static void main(String[] args) {
        UpperClass lowerClass = new LowerClass();
        lowerClass.doSomething();
    }
}
