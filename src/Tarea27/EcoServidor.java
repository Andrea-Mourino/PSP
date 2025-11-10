package Tarea27;

import java.io.*;
import java.net.*;

public class EcoServidor {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor ECO escuchando en el puerto " + puerto + "...");

            while (true) {
                Socket socketCliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress().getHostAddress());

                try (
                        BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                        PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)
                ) {
                    String mensaje;
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println("Cliente dice: " + mensaje);
                        if (mensaje.equalsIgnoreCase("adios")) {
                            System.out.println("Cliente se desconectó.");
                            break;
                        }
                        salida.println("ECO: " + mensaje);
                    }
                } catch (IOException e) {
                    System.out.println("Error en la comunicación con el cliente: " + e.getMessage());
                } finally {
                    socketCliente.close();
                }
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
