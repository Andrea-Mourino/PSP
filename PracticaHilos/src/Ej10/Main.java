package Ej10;

public class Main {
    public static void main(String[] args) {

        Almacen almacenUnico = new Almacen();
        Thread hilo1 = new Thread(new OperacionSintesis(almacenUnico, "Dr.Ratio - Agrega", 2, true), "hilo1");
        Thread hilo2 = new Thread(new OperacionSintesis(almacenUnico, "Herta - Consume", 1, false), "hilo1");

        hilo1.start();
        hilo2.start();

        try {
           hilo1.join();
           hilo2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("--HILO TERMINADO--");
    }
}
