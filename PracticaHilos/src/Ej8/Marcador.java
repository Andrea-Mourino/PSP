package Ej8;

class Marcador {

    private long puntuacionGlobal = 0;

    public long getPuntuacionGlobal() {
        return puntuacionGlobal;
    }

    public synchronized void sumarPunto (String nombre, int puntos)  {

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        puntuacionGlobal+=puntos;

    }



}
