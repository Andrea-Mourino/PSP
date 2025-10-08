
public class Tarea14 {
    static class Caja {
        public static double capital = 1000.0;
    }

    // Hilo de ingresos
    static class HiloIngresos extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 5000; i++) { // 5000 ventas
                synchronized (Caja.class) { //palabra clave para garantizar que el método o bloque de código sea ejecutado por un solo hilo a la vez
                    double saldo = Caja.capital;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Caja.capital = saldo + 10; // suma 10 euros por venta
                }
            }
        }
    }

    // Hilo de extracciones
    static class HiloExtracciones extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 3000; i++) { // 3000 pagos
                synchronized (Caja.class) {
                    double saldo = Caja.capital;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Caja.capital = saldo - 10; // resta 10 euros por pago
                }
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        HiloExtracciones extracciones = new HiloExtracciones();
        HiloIngresos ingresos = new HiloIngresos();

        extracciones.start(); // inicia el hilo de extracciones
        ingresos.start();     // inicia el hilo de ingresos

        try {
            extracciones.join(); // espera a que termine el hilo de extracciones
            ingresos.join();     // espera a que termine el hilo de ingresos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final de la caja: " + Caja.capital + "€");
    }
}
