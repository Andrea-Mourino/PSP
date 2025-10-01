import java.util.Random;

class Profesor extends Thread {
    private String nombre;
    private int limite;
    private Random random = new Random();

    public Profesor(String nombre, int limite) {
        this.nombre = nombre;
        this.limite = limite;
    }

    @Override
    public void run() {
        for (int nivel = 1; nivel <= limite; nivel++) {
            try {
                Thread.sleep(200 + random.nextInt(600)); // 200-800 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (nivel < limite) {
                System.out.println("[" + nombre + "] Cabreo nivel: " + nivel);
            } else {
                System.out.println("[" + nombre + "] Cabreo nivel: " + nivel + "... ¡He llegado a mi límite!");
            }
        }
    }
}
//algo
public class CabreoProfesores {
    public static void main(String[] args) {
        // Crear y lanzar hilos de profesores
        Thread diego = new Profesor("Diego", 4);
        Thread manuel = new Profesor("Manuel", 5);
        Thread damian = new Profesor("Damian", 3);
        Thread araujo = new Profesor("Araujo", 5);

        diego.start();
        manuel.start();
        damian.start();
        araujo.start();

        // Programa principal no espera
        System.out.println("Programa principal terminado.");
    }
}
