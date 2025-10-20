package Tarea19;

import java.util.Scanner;

public class ContadorVocales {

    private static int totalVocales = 0;

    private static final Object lock = new Object();

    static class ContadorVocal extends Thread {
        private final char vocal;
        private final String texto;

        public ContadorVocal(char vocal, String texto) {
            this.vocal = vocal;
            this.texto = texto.toLowerCase();
        }

        @Override
        public void run() {
            int contadorLocal = 0;
            for (char c : texto.toCharArray()) {
                if (c == vocal) {
                    contadorLocal++;
                }
            }

            synchronized (lock) {
                totalVocales += contadorLocal;
                System.out.println(" Hilo " + vocal + " ha encontrado " + contadorLocal + " vocal " + "'" + vocal + "'");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce un texto: ");
        String texto = sc.nextLine();
        sc.close();

        Thread[] hilos = new Thread[5];
        char[] vocales = {'a', 'e', 'i', 'o', 'u'};

        for (int i = 0; i < vocales.length; i++) {
            hilos[i] = new ContadorVocal(vocales[i], texto);
            hilos[i].start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }

        System.out.println("----------------------------------");
        System.out.println("Total de vocales en el texto: " + totalVocales);
    }
}