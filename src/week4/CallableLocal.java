package week4;

import java.util.concurrent.Callable;

public class CallableLocal implements Callable<String> {

    @Override
    public String call() throws InterruptedException {
        Thread.sleep(500);
        System.out.println("Callable Local");
        return "Callable Local";
    }
}