package Tarea32;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket(5000);

        byte[] buffer = new byte[1024];
        DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);

        socket.receive(paquete);

        String mensaje = new String(paquete.getData(), 0, paquete.getLength());
        String[] palabras = mensaje.split(" ");

        String mayor = "";
        for (String p : palabras)
            if (p.length() > mayor.length())
                mayor = p;

        String respuesta = mayor + " " + mayor.length();
        byte[] datos = respuesta.getBytes();

        DatagramPacket salida = new DatagramPacket(
                datos, datos.length, paquete.getAddress(), paquete.getPort());

        socket.send(salida);
        socket.close();
    }
}
