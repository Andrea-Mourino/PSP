package Ej4;

public class Domino implements Runnable{
    private final String nombre;
    private Thread predecesor;

    public Domino(String nombre, Thread predecesor) {
        this.nombre = nombre;
        this.predecesor = predecesor;
    }


    @Override
    public void run() {
        if (predecesor != null) {
            try {
                System.out.println("ESPERA... " + nombre + " esta esperando a que " + predecesor.getName() + " termine");
                predecesor.join();
                System.out.println("COMPLETADO " + predecesor.getName() + " a terminado " + nombre + " va a empezar");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        } else {
            System.out.println("--COMENZANDO HILO--");
        }

        for (int i=1; i<=5; i++) {
            System.out.println(" [ " + nombre +  " ] " + "Iteracion= " + i);

            int tiempoAleatorio = (int) (Math.random() * 5) + 1;

            try {
                Thread.sleep(tiempoAleatorio*1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }


    }










}
