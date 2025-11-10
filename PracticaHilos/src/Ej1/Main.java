package Ej1;

public class Main {
    public static void main(String[] args) {
        Thread hilo1 = new Thread(new ExpresoAstral("Hilo1 - Dang Heng", null), "Dang Heng");
        Thread hilo2 = new Thread(new ExpresoAstral("Hilo2 - March 7th", hilo1), "March 7th");

        hilo1.start();
        hilo2.start();


        try {
            hilo1.join();
            hilo2.join();

        }catch (InterruptedException e) {
            // Manejo de error
            Thread.currentThread().interrupt();
        }

        System.out.println(" -- MISION COMPLETADA CON EXITO --");

    }
}
