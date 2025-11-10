package Ej2;

class Profes implements Runnable {

    private final int LimitePaciencia;
    private final String Nombre;

    public Profes(int limitePaciencia, String nombre) {
        LimitePaciencia = limitePaciencia;
        Nombre = nombre;
    }



    @Override
    public void run() {

        int nivelCabreo = 0;

        for (int i=1; i<=LimitePaciencia; i++) {
            nivelCabreo = i;

        String mensaje = "[ " + Nombre + " ] " + "Cabreo nivel : " + nivelCabreo;


        if (nivelCabreo==LimitePaciencia) {
            mensaje += "... ¡He llegado a mi límite!";
        }

            System.out.println(mensaje);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        }

    }



}




