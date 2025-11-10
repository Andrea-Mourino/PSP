package Ej7;

public class Main {
    public static void main(String[] args) {

                // 1. Crear el UNICO recurso compartido (el cofre)
                Cofre cofre = new Cofre();
                System.out.println("--- Iniciando Cofre de Créditos Estelares ---");
                System.out.println("Capital Inicial: " + cofre.getCapital());


                // 3. Crear los hilos
                Thread hilo1 = new Thread(new OperacionesCofre(cofre, "March 7th", 1),"tareaMarch");
                Thread hilo2 = new Thread(new OperacionesCofre(cofre, "Dang Heng", 1),"tareaDang");
                Thread hilo3 = new Thread(new OperacionesCofre(cofre, "Prota", -2),"tareaProta");


                // 4. Lanzar los hilos concurrentemente (¡Caos controlado!)
        hilo1.start();
        hilo2.start();
        hilo3.start();

                // 5. Esperar la finalización de los tres hilos (join())
                try {
                    hilo1.join();
                    hilo2.join();
                    hilo3.join();
                } catch (InterruptedException e) {
                    System.err.println("Misión interrumpida inesperadamente.");
                    Thread.currentThread().interrupt();
                }

                // 6. Imprimir el resultado final
                // Resultado esperado: (5000 * 1) + (5000 * 1) + (5000 * -2) = 0
                System.out.println("\n--- Informe Final de la Misión ---");
                System.out.println("Capital Esperado (Cálculo): 0");
                System.out.println("💰 Capital Final Obtenido: " + cofre.getCapital());
            }
        }

