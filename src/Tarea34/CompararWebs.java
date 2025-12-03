package Tarea34;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Scanner;

public class CompararWebs {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Introduce la primera URL muchacho: ");
        String url1 = scan.nextLine();

        System.out.print("Introduce la segunda URL muchacho: ");
        String url2 = scan.nextLine();

        try {
            // Crear cliente HTTP
            HttpClient client = HttpClient.newHttpClient();

            // Obtener métricas de la primera URL
            Metrics metrics1 = medirWeb(client, url1);

            // Obtener métricas de la segunda URL
            Metrics metrics2 = medirWeb(client, url2);

            // Comparar tiempos de respuesta
            if (metrics1.tiempo < metrics2.tiempo) {
                System.out.println("La web más rápida ha sido: " + url1 + " con " + metrics1.tiempo + " ms.");
            } else {
                System.out.println("La web más rápida ha sido: " + url2 + " con " + metrics2.tiempo + " ms.");
            }

            // Comparar tamaños de contenido
            if (metrics1.tamano > metrics2.tamano) {
                System.out.println("La web con más contenido ha sido: " + url1 + " con " + metrics1.tamano + " caracteres.");
            } else {
                System.out.println("La web con más contenido ha sido: " + url2 + " con " + metrics2.tamano + " caracteres.");
            }

        } catch (Exception e) {
            System.out.println("Error al realizar la petición: " + e.getMessage());
        }

        scan.close();
    }

    // Método para medir tiempo y tamaño de una web
    private static Metrics medirWeb(HttpClient client, String url) throws Exception {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(5))
                    .GET()
                    .build();

            long inicio = System.currentTimeMillis();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            long fin = System.currentTimeMillis();

            long tiempo = fin - inicio; // tiempo en ms
            int tamano = response.body().length(); // tamaño del cuerpo en caracteres

            return new Metrics(tiempo, tamano);
        } catch (Exception e) {
            System.out.println("Error al conectar con " + url + ": " + e.getMessage());
            // Devuelve métricas "infinitas" para que no gane en la comparación
            return new Metrics(Long.MAX_VALUE, 0);
        }
    }

    // Clase auxiliar para almacenar métricas
    static class Metrics {
        long tiempo;
        int tamano;

        Metrics(long tiempo, int tamano) {
            this.tiempo = tiempo;
            this.tamano = tamano;
        }
    }
}
