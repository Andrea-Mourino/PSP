package Ej11;

public class Hilos implements Runnable {

    private final String nombre;
    private Thread predecesor;

    public Hilos(String nombre, Thread predecesor) {
        this.nombre = nombre;
        this.predecesor = predecesor;
    }

    @Override
    public void run() {
        if (predecesor != null ) {

            try {
                System.out.println("WAIT... " + nombre + " esta esperando a que " + predecesor.getName() + " termine");
                predecesor.join();
                System.out.println("TERMINADO " + nombre + " ya puede empezar ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        for (int i=1; i<=8; i++) {
            System.out.println(" [ " + nombre + " ] " + " Iteracion " + i);

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }


        }




    }
}
