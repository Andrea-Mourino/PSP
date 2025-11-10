package Ej8;

public class Main {
    public static void main(String[] args) {

        Marcador marcadorUnico = new Marcador();

        Thread hilo1 = new Thread(new Jugador(marcadorUnico, "Painon"), "painon");
        Thread hilo2 = new Thread(new Jugador(marcadorUnico, "Mydei"), "mydei");


        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("-- HILO TERMINADO --");
    }
}
