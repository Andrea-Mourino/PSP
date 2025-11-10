package Ej7;

class Cofre {

    private long capital= 0;

    public long getCapital() {
        return capital;
    }

    public synchronized void transtracciones ( String nombre, int monto) {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        capital += monto;


    }

}
