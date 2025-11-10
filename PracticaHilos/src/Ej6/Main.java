package Ej6;

public class Main {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new ProcesadorDeCurios("Alpha", 10), "Herta");
        Thread hilo2 = new Thread(new ProcesadorDeCurios("Beta", 15), "Herta");
        Thread hilo3 = new Thread(new ProcesadorDeCurios("Gamma", 7), "Herta");


        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("--HILO TERMINADO--");

    }
}
