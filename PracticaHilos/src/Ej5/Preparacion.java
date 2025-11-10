package Ej5;

class Preparacion implements Runnable {

    private final String nombre;

    public Preparacion(String nombre) {
        this.nombre = nombre;
    }


    @Override
    public void run() {
        for (int i=1; i<=3; i++) {

            System.out.println(" [ " + nombre + " ] " + "Iteraciones: " + i);


            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }



}
