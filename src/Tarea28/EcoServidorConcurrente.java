package Tarea28;

import java.io.*;
import java.net.*;

public class EcoServidorConcurrente {

    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor ECO escuchando en el puerto " + puerto + "...");

            while (true) {
                Socket socketCliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress().getHostAddress());

                // Crear un nuevo hilo para manejar al cliente
                Thread hiloCliente = new Thread(new ManejadorCliente(socketCliente));
                hiloCliente.start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}

// Clase que maneja la comunicación con un cliente
class ManejadorCliente implements Runnable {
    private Socket socketCliente;

    public ManejadorCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true)
        ) {
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("[" + socketCliente.getInetAddress().getHostAddress() + "] dice: " + mensaje);

                if (mensaje.equalsIgnoreCase("adios")) {
                    System.out.println("[" + socketCliente.getInetAddress().getHostAddress() + "] se desconectó.");
                    break;
                }

                salida.println("ECO: " + mensaje);
            }

        } catch (IOException e) {
            System.err.println("Error con el cliente " + socketCliente.getInetAddress().getHostAddress() + ": " + e.getMessage());
        } finally {
            try {
                socketCliente.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar el socket del cliente: " + e.getMessage());
            }
        }
    }
}
