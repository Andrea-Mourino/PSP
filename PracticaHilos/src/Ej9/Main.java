package Ej9;

public class Main {
    public static void main(String[] args) {

        String numeros = "010101010";


        Thread hilo1 = new Thread(new NumBinarios("Jugador1", numeros, '1'), "nose1");
        Thread hilo2 = new Thread(new NumBinarios("Jugador2", numeros, '0'), "nose2");

        hilo1.start();
        hilo2.start();

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("--HILO TERMINADO--");

    }
}
