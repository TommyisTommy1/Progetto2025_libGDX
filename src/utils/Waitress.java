package utils;

public class Waitress implements Runnable {

    public int waitTime;

    public Waitress(int waitTime) {
        this.waitTime = waitTime;
    }
    @Override
    public void run() {
        try{
            System.out.println("La waitress è in attesa");
            Thread.sleep(waitTime);
            System.out.println("La waitress è pronta");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("La waitress è stata interrotta");
        }
    }
}
