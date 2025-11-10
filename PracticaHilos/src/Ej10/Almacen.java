package Ej10;

public class Almacen {

    private long cantidadMateriales = 500;

    public long getCantidadMateriales() {
        return cantidadMateriales;
    }


    public synchronized void agregarMaterial (int cantidad) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        cantidadMateriales += cantidad;

    }

    public synchronized void consumirMaterial (int cantidad) {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        cantidadMateriales -= cantidad;

    }



}
