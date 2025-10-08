import java.util.Random;
import java.util.Scanner;

public class Tarea11 {
    public static void main(String[] args) {

        int N = preguntarUsuario(); // valor por defecto

        Ficha hilo1 = new Ficha("Hilo-1", 1, N);

        long inicio = System.currentTimeMillis();

        hilo1.start();

        while (hilo1.isAlive()) {
            System.out.println(": [Control Central] La primera ficha aún no ha terminado su secuencia...");
            try {
                Thread.sleep(500); // revisar cada 500 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long fin = System.currentTimeMillis();
        long total = fin - inicio;

        System.out.println("--------------------------------------------------");
        System.out.println("[Control Central] Todas las fichas han terminado.");
        System.out.println("[Control Central] Tiempo total de la caída: " + total + " ms");
        System.out.println("--------------------------------------------------");


    }


    public static int preguntarUsuario() {
        Scanner sc = new Scanner(System.in);
        int valor=0;
        System.out.println("Ingrese el numero de hilos que quieras muchacho: ");
        try {
            valor = sc.nextInt();
            if (valor < 1) {
                valor = 5;
                System.out.println("Muy mal muchacho");
            } else {
                System.out.println("Muy bien muchacho");
            }
        } catch (NumberFormatException e) {
            System.out.println("Muy mal muchacho");
        } finally {
            sc.close();
        }
        return valor;

    }


static class Ficha extends Thread {
    private String nombre;
    private int restantes;
    private int indice;
    private Ficha siguiente;
    private Random random = new Random();

    public Ficha(String nombre, int indice, int restantes) {
        this.nombre = nombre;
        this.restantes = restantes;
        this.indice = indice;
    }

    @Override
    public void run() {

        if (indice < restantes) {
            siguiente = new Ficha("Hilo-" + (indice + 1), indice + 1, restantes);
            siguiente.start();
        }


        for (int i = 1; i <= 5; i++) {
            System.out.println("[" + nombre + "]" + " Iteración " + i);
            try {
                Thread.sleep(100 + random.nextInt(501));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        if (siguiente != null) {
            try {
                siguiente.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Acabó hilo " + nombre);
    }


}
}