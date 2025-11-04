package Tarea25;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EscanerPuertosYResolucion {
    private static final int TIMEOUT_MS = 200;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String direccionOrHost;

        System.out.println("=== Escáner de Puertos y Resolución de Dominios ===");

        while (true) {
            System.out.print("\nIntroduce una dirección IP o 'localhost' (o escribe 'salir' para terminar): ");
            direccionOrHost = scan.nextLine().trim();

            if (direccionOrHost.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            // Mostrar información de la máquina local
            imprimirInfoMaquinaLocal();

            System.out.println("\nElige una opción:");
            System.out.println("  1) Escanear puertos famosos (21, 22, 80, 443)");
            System.out.println("  2) Escanear un puerto específico");
            System.out.println("  3) Resolver una URL/domino y mostrar su dirección IP");
            System.out.print("Opción (1/2/3): ");
            String opcion = scan.nextLine().trim();

            switch (opcion) {
                case "1":
                    int[] puertosFamosos = {21, 22, 80, 443};
                    System.out.printf("\nResumen para %s:%n", direccionOrHost);
                    for (int puerto : puertosFamosos) {
                        comprobarYMostrar(direccionOrHost, puerto);
                    }
                    break;

                case "2":
                    System.out.print("Introduce el número de puerto a verificar: ");
                    try {
                        int puerto = Integer.parseInt(scan.nextLine().trim());
                        System.out.printf("\nResumen para %s:%n", direccionOrHost);
                        comprobarYMostrar(direccionOrHost, puerto);
                    } catch (NumberFormatException e) {
                        System.out.println("Puerto no válido. Debe ser un número entero.");
                    }
                    break;

                case "3":
                    System.out.print("Introduce una URL o dominio (ej: https://www.google.com/search?q=google.com o www.google.com): ");
                    String entrada = scan.nextLine().trim();
                    resolverYMostrarIP(entrada);
                    break;

                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }

        scan.close();
    }

    private static void comprobarYMostrar(String host, int puerto) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(host, puerto), TIMEOUT_MS);
            System.out.printf("Puerto %d está ABIERTO en %s%n", puerto, host);
        } catch (IOException e) {
            System.out.printf("Puerto %d está CERRADO o no accesible en %s%n", puerto, host);
        }
    }

    private static void resolverYMostrarIP(String entrada) {
        String host = entrada;

        // Intentar extraer host si el usuario ha proporcionado una URL completa
        try {
            if (entrada.contains("://")) {
                URL url = new URL(entrada);
                host = url.getHost();
            } else if (entrada.startsWith("www.")) {
                // dejar host como está (www.example.com)
                host = entrada;
            } else {
                // intentar crear URL con http para extraer host si el usuario puso algo tipo "example.com/path"
                try {
                    URL url2 = new URL("http://" + entrada);
                    host = url2.getHost();
                } catch (Exception ignored) {
                    // si sigue fallando, usamos la entrada original como host
                    host = entrada;
                }
            }
        } catch (Exception e) {
            // Si el parsing falla, usar la entrada tal cual
            host = entrada;
        }

        try {
            InetAddress[] addresses = InetAddress.getAllByName(host);
            System.out.printf("Direcciones IP para '%s' (host resuelto: %s):%n", entrada, host);
            for (InetAddress addr : addresses) {
                System.out.printf("  - %s%n", addr.getHostAddress());
            }
        } catch (UnknownHostException e) {
            System.out.printf("No se pudo resolver el host '%s'.%n", host);
        }
    }

    private static void imprimirInfoMaquinaLocal() {
        try {
            InetAddress local = InetAddress.getLocalHost();
            String nombre = local.getHostName();
            String ip = local.getHostAddress();
            System.out.println("\n--- Información de tu máquina ---");
            System.out.printf("Nombre de la máquina: %s%n", nombre);
            System.out.printf("Dirección IP local: %s%n", ip);
            System.out.println("---------------------------------");
        } catch (UnknownHostException e) {
            System.out.println("No se pudo obtener la información de la máquina local.");
        }
    }
}
