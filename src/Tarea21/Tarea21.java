package Tarea21;

import java.util.Random;


class Parking {
    private final int[] plazas;
    private int libres;

    public Parking(int numPlazas) {
        plazas = new int[numPlazas];
        libres = numPlazas;
    }


    public synchronized void entrar(int cocheId) {
        // Esperar hasta que haya una plaza libre
        while (libres == 0) {
            try {
                System.out.println("Coche " + cocheId + " esperando plaza...");
                wait(); // espera activa hasta que se libere una plaza
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        // Asignar primera plaza libre
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == 0) {
                plazas[i] = cocheId;
                libres--;
                System.out.println("ENTRADA: Coche " + cocheId + " aparca en la plaza numero " + i + ".");
                mostrarEstado();
                notifyAll(); // notifica a otros coches que puedan estar esperando
                return;
            }
        }
    }


    public synchronized void salir(int cocheId) {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == cocheId) {
                plazas[i] = 0;
                libres++;
                System.out.println("SALIDA: Coche " + cocheId + " saliendo de " + i + ".");
                mostrarEstado();
                notifyAll(); // notifica a coches esperando
                return;
            }
        }
    }

    // Mostrar el estado actual del parking
    private void mostrarEstado() {
        System.out.println("Plazas libres: " + libres);
        System.out.print("Parking: ");
        for (int plaza : plazas) {
            System.out.print(" [ " + plaza + " ] ");
        }
        System.out.println("\n");
    }
}


class Coche extends Thread {
    private final int id;
    private final Parking parking;
    private final Random random = new Random();

    public Coche(int id, Parking parking) {
        this.id = id;
        this.parking = parking;
    }

    @Override
    public void run() {
        while (true) {
            // Intentar entrar
            parking.entrar(id);

            // Simular tiempo aparcado
            try {
                Thread.sleep(3000 + random.nextInt(4000)); // sleep aleatorio
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Salir del parking
            parking.salir(id);

            // Simular tiempo fuera del parking
            try {
                Thread.sleep(3000 + random.nextInt(4000)); // sleep aleatorio
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

public class Tarea21 {
    public static void main(String[] args) {
        int N = 5;
        int M = 10;

        Parking parking = new Parking(N);

        // Crear y arrancar hilos coches
        for (int i = 1; i <= M; i++) {
            new Coche(i, parking).start();
        }
    }
}
