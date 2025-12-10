package Tarea35;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.util.Scanner;

public class Crypto {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre o símbolo de la criptomoneda muchacho: ");
        String input = scanner.nextLine().trim().toLowerCase();

        try {
            String url = "https://api.coinlore.net/api/tickers/";
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();

            boolean found = false;
            String[] coins = json.split("\\},\\{"); // separar monedas

            for (String coin : coins) {
                coin = coin.replace("{", "").replace("}", ""); // limpiar llaves

                if (coin.toLowerCase().contains("\"name\":\"" + input + "\"") ||
                        coin.toLowerCase().contains("\"symbol\":\"" + input + "\"")) {

                    found = true;

                    String name = getValue(coin, "name");
                    String symbol = getValue(coin, "symbol");
                    String price = getValue(coin, "price_usd");
                    String rank = getValue(coin, "rank");
                    double change = Double.parseDouble(getValue(coin, "percent_change_24h"));

                    String sign = change >= 0 ? "+" : "-";
                    String color = change >= 0 ? "\u001B[32m" : "\u001B[31m"; // verde/rojo
                    String reset = "\u001B[0m";

                    System.out.println("\n=== Información de la moneda ===");
                    System.out.println("Nombre: " + name);
                    System.out.println("Símbolo: " + symbol);
                    System.out.println("Precio USD: $" + price);
                    System.out.println("Ranking: " + rank);
                    System.out.println("Variación 24h: " + color + sign + change + "%" + reset);

                    break;
                }
            }

            if (!found) {
                System.out.println("Moneda no encontrada.");
            }

        } catch (Exception e) {
            System.out.println("Error obteniendo datos: " + e.getMessage());
        }

        scanner.close();
    }

    // Extrae valor de JSON simple, maneja sin comillas o con comillas
    private static String getValue(String json, String key) {
        String pattern = "\"" + key + "\":";
        int start = json.indexOf(pattern) + pattern.length();
        if (start < pattern.length()) return "";
        // encontrar fin del valor
        int end;
        if (json.charAt(start) == '"') { // valor con comillas
            start++;
            end = json.indexOf("\"", start);
        } else { // valor sin comillas
            end = json.indexOf(",", start);
            if (end == -1) end = json.length();
        }
        return json.substring(start, end);
    }
}
