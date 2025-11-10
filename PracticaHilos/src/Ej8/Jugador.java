package Ej8;

public class Jugador implements Runnable {

    private final Marcador marcadorCompartido;
    private final String nombre;
    private final int sumarPunto = 10000;

    public Jugador(Marcador marcadorCompartido, String nombre) {
        this.marcadorCompartido = marcadorCompartido;
        this.nombre = nombre;
    }

    @Override
    public void run() {

        for (int i=1; i<=sumarPunto; i++) {
            marcadorCompartido.sumarPunto(nombre,sumarPunto);


            System.out.println(" [ " + nombre + " ] " +  "Marcador: " + (i + 1));

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

        }


    }
}
