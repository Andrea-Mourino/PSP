package Tarea38;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginSeguro {

    // Método para calcular hash SHA-256 y devolverlo en hexadecimal
    public static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());

            // Convertir bytes a hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al calcular el hash", e);
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Cree una contraseña: ");
        String passwordRegistro = scanner.nextLine();

        String hashGuardado = sha256(passwordRegistro);
        System.out.println("\nUsuario registrado. Inicie sesión para probar.\n");

        System.out.print("Introduzca su contraseña: ");
        String passwordLogin = scanner.nextLine();

        String hashLogin = sha256(passwordLogin);

        // Comparación de hashes
        if (hashLogin.equals(hashGuardado)) {
            System.out.println("\nACCESO CONCEDIDO");
        } else {
            System.out.println("\nERROR: Credenciales inválidas");
        }

        scanner.close();
    }
}
