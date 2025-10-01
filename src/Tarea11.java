import java.util.Random;
import java.util.Scanner;

public class Tarea11 {
    public static void main(String[] args) {

        int N = preguntarUsuario(); // valor por defecto

        Ficha hilo1 = new Ficha("Hilo-1", N, 5);
        hilo1.start();

        try {
            hilo1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static int preguntarUsuario() {
        Scanner sc = new Scanner(System.in);
        int valor=0;
        System.out.println("Ingrese el numero de hilos que quieras muchacho: ");
        try {
            valor = sc.nextInt();
            if (valor < 1) {
                valor = 5;
            } else {
                System.out.println("Número inválido. Usando N=5 por defecto.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Argumento no válido. Usando N=5 por defecto.");
        } finally {
            sc.close();
        }
        return valor;

    }

}


class Ficha extends Thread {
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
            siguiente = new Ficha("Hilo-" + (indice + 1), restantes, indice + 1);
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
