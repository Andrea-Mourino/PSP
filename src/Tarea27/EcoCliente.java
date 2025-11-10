package Tarea27;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class EcoCliente {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String host = "localhost";
        int puerto = 5000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

            System.out.println("Conectado al servidor en " + host + ":" + puerto);
            System.out.println("Escribe mensajes para enviar al servidor (escribe 'adios' para salir):");

            while (true) {
                System.out.print("> ");
                String linea = scanner.nextLine();
                salida.println(linea);

                if (linea.equalsIgnoreCase("adios")) {
                    System.out.println("Cerrando conexión...");
                    break;
                }

                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    System.out.println("El servidor cerró la conexión.");
                    break;
                }

                System.out.println(respuesta);
            }

        } catch (IOException e) {
            System.err.println("Error en la comunicación con el servidor: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
