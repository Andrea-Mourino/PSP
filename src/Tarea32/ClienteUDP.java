package Tarea32;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = new DatagramSocket();

        String palabras = "hola pi pompompurin sol";
        byte[] datos = palabras.getBytes();

        InetAddress ip = InetAddress.getByName("localhost");
        DatagramPacket paquete = new DatagramPacket(datos, datos.length, ip, 5000);
        socket.send(paquete);

        byte[] buffer = new byte[1024];
        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length);
        socket.receive(respuesta);

        System.out.println("Servidor dice: " +
                new String(respuesta.getData(), 0, respuesta.getLength()));

        socket.close();
    }
}
