package week4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) {
        // thread
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.start();
        System.out.println("thread local");

        // runnable
        RunnableLocal runnableLocal = new RunnableLocal();
        new Thread(runnableLocal).start();
        System.out.println("runnable local");

        // callable
        CallableLocal callableLocal = new CallableLocal();
        FutureTask<String> futureTask = new FutureTask<>(callableLocal);
        new Thread(futureTask).start();
        System.out.println("callable local");
        try {
            System.out.println("callable local: " + futureTask.get());
        } catch (ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }

    }
}