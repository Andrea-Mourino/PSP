package Ej7;

public class OperacionesCofre implements Runnable{

    private final Cofre cofreCompartido;
    private final String nombre;
    private final int montoTransacciones;
    private final int numOperaciones= 5000;

    public OperacionesCofre(Cofre cofreCompartido, String nombre, int montoTransacciones) {
        this.cofreCompartido = cofreCompartido;
        this.nombre = nombre;
        this.montoTransacciones = montoTransacciones;
    }

    @Override
    public void run() {

        for (int i= 0; i<=numOperaciones; i++) {

            cofreCompartido.transtracciones(nombre,montoTransacciones);
            System.out.println(" [ " + nombre + " ] " + "ha terminado sus 5000 operaciones.");


        }
    }
}
