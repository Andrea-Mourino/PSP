public class Tarea15 {
    static class MiHilo extends Thread {
        private String nombre;

        public MiHilo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public void run() {
            for (int i=1; i <=8; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }

                System.out.println("Hola muchacho soy el " + nombre + " - " + "Iteracion " + i);
            }
        }


    }


    public static void main(String[] args) {
        System.out.println("Inicio del programa principal");

        MiHilo hilo1 = new MiHilo("Hilo 1");
        MiHilo hilo2 = new MiHilo("Hilo 2");
        MiHilo hilo3 = new MiHilo("Hilo 3");

        try { //Ejecucion secuencial inversa
            hilo3.start();
            hilo3.join();

            hilo2.start();
            hilo2.join();

            hilo1.start();
            hilo1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Programa principal terminado");
    }


}
