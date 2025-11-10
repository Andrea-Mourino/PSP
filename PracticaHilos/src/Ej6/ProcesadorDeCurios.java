package Ej6;

class ProcesadorDeCurios implements Runnable{

    private final String nombre;
    private int numLimite;


    public ProcesadorDeCurios(String nombre, int numLimite) {
        this.nombre = nombre;
        this.numLimite = numLimite;
    }

    @Override
    public void run() {

        for (int i=1; i<=numLimite; i++) {
            System.out.println(" [ " + nombre + " ] " + "procesando item: " + i);

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }



        }









    }
}
