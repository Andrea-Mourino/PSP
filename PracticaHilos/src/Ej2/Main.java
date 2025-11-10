package Ej2;

public class Main {
    public static void main(String[] args) {

        Thread hilo1 = new Thread(new Profes(3, "Hilo 1 - Damian"), "Damian");
        Thread hilo2 = new Thread(new Profes(4, "Hilo 2 - Diego"), "Diego");
        Thread hilo3 = new Thread(new Profes(5, "Hilo 3 - Araujo"), "Araujo");
        Thread hilo4 = new Thread(new Profes(5, "Hilo 4 - Manuel"), "Manuel");

        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();


        System.out.println("--HILO FINALIZADO--");


    }
}
