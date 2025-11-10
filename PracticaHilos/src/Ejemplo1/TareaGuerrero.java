package Ejemplo1;

import java.util.concurrent.TimeUnit;

/**
 * Define la tarea que ejecutará cada hilo (T1, T2, T3).
 */
class TareaGuerrero implements Runnable {
    private final String nombre;
    // Referencia al hilo que debe terminar antes de que este hilo continúe.
    private final Thread predecesor;

    public TareaGuerrero(String nombre, Thread predecesor) {
        this.nombre = nombre;
        this.predecesor = predecesor;
    }

    @Override
    public void run() {
        // PASO 1: Control de Secuencia Inversa (El 'join' interno)
        if (predecesor != null) {
            try {
                System.out.println("⏳ " + nombre + " está esperando a que " + predecesor.getName() + " termine su misión...");
                // **Técnica del Guerrero: Join** - El hilo actual se bloquea hasta que el predecesor termina.
                predecesor.join();
                System.out.println("✅ " + predecesor.getName() + " ha terminado. " + nombre + " comienza su ejecución.");
            } catch (InterruptedException e) {
                // Manejo de la interrupción (ejemplo simplificado)
                Thread.currentThread().interrupt();
                return;
            }
        } else {
            // El tercer hilo (sin predecesor) puede empezar inmediatamente
            System.out.println("🚀 " + nombre + " comienza inmediatamente (Tercer Hilo).");
        }

        // PASO 2: La Ejecución de la Tarea (Bucle de 8 iteraciones)
        for (int i = 1; i <= 8; i++) {
            System.out.println("   [" + nombre + "]: Iteración " + i);

            // Bloqueo temporal para simular el trabajo
            try {
                // Bloqueamos el hilo por un tiempo.
                TimeUnit.MILLISECONDS.sleep(150);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("🏁 " + nombre + " ha completado sus 8 iteraciones.");
    }
}
