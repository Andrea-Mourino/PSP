package Ejemplo2;

import java.util.HashMap;
import java.util.Map;

class ContadorGlobal {
    // Variable compartida para el total de dígitos pares encontrados.
    private int totalPares = 0;
    // Mapa para contar las ocurrencias de cada dígito par.
    private final Map<Character, Integer> ocurrencias = new HashMap<>();

    // MÉTODO CRÍTICO: Debe ser sincronizado.
    public synchronized void actualizarContadores(char digito, int count) {
        // Al usar 'synchronized', nos aseguramos de que solo UN hilo pueda ejecutar
        // este bloque de código a la vez.

        // 1. Aumentamos el total global.
        this.totalPares += count;

        // 2. Registramos la cuenta individual.
        this.ocurrencias.put(digito, count);

        System.out.println(">>> Registro Sincronizado: " + Thread.currentThread().getName() +
                " añadió " + count + " ocurrencias del dígito " + digito);
    }

    // Métodos Getter (no necesitan ser sincronizados, ya que solo leen datos estables)
    public int getTotalPares() {
        return totalPares;
    }

    public Map<Character, Integer> getOcurrencias() {
        return ocurrencias;
    }
}