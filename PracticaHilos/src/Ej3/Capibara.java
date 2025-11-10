package Ej3;

import java.util.zip.InflaterInputStream;

public class Capibara implements Runnable {
    private final String nombre;


    public Capibara(String nombre) {
        this.nombre = nombre;

    }

    @Override
    public void run() {

        for (int i=1; 1<=10; i++) {

            System.out.println(" [ " + nombre + " ] " + "Iteracion: " + i);


        int segundosAleatorios = (int) (Math.random() * 10) + 1;

        try  {
            Thread.sleep(segundosAleatorios * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }


        }


    }


}
