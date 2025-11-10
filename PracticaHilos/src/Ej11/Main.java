package Ej11;

public class Main {
    public static void main(String[] args) {

        Thread hilo3 = new Thread(new Hilos("Soy el hilo 3", null),"hilo3");
        Thread hilo2 = new Thread(new Hilos("Soy el hilo 2", hilo3),"hilo2");
        Thread hilo1 = new Thread(new Hilos("Soy el hilo 1", hilo2),"hilo1");

        hilo3.start();
        hilo2.start();
        hilo1.start();

        try {
            hilo3.join();
            hilo2.join();
            hilo1.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("--HILO TERMINADO--");

    }
}
