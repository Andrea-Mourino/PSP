package Ej1;

class ExpresoAstral implements Runnable {
    private final String nombre;
    private Thread predecesor;

    public ExpresoAstral(String nombre, Thread predecesor) {
        this.nombre = nombre;
        this.predecesor = predecesor;
    }


    @Override
    public void run() {
        if (predecesor != null ) {
            try {
                System.out.println("WAIT..." + nombre + " esta esperando a que " + predecesor.getName() + " termine su mision");
                predecesor.join();
                System.out.println("COMPLETADO " + predecesor.getName() + " a terminado " + nombre + " va a empezar su mision");

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

        } else {
            System.out.println(" --COMENZANDO HILO-- ");
        }

        for (int i= 1; i<=5; i++) {
            System.out.println("[ " + nombre + " ]: Iteracion " + i);

            try {
                Thread.sleep(150);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }


        }


    }

}



