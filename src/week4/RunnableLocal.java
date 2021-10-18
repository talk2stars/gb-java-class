package week4;

public class RunnableLocal implements Runnable {

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Runnable Local");
    }
}