package Ej5;

public class Main {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new Preparacion("Himeko"), "Himeko");
        Thread hilo2 = new Thread(new Preparacion("Welt"), "Welt");


        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        System.out.println("--HILO TERMINADO--");
    }
}
