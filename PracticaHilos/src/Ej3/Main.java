package Ej3;

public class Main {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new Capibara("Hilo 1"), "hilo1");
        Thread hilo2 = new Thread(new Capibara("Hilo 2"), "hilo2");
        Thread hilo3 = new Thread(new Capibara("Hilo 3"), "hilo3");
        Thread hilo4 = new Thread(new Capibara("Hilo 4"), "hilo4");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
}
