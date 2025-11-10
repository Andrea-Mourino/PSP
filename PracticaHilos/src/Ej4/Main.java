package Ej4;

import Ej3.Capibara;

public class Main {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new Domino("Andrea", null), "Andrea");
        Thread hilo2 = new Thread(new Domino("Manuel", hilo1), "Manuel");
        Thread hilo3 = new Thread(new Domino("Roberto", hilo2), "Roberto");
        Thread hilo4 = new Thread(new Domino("Noa", hilo3), "Noa");
        Thread hilo5 = new Thread(new Domino("Ivan", hilo4), "Ivan");


        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
            hilo4.join();
            hilo5.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("--HILO TEMRINADO--");
    }
}
