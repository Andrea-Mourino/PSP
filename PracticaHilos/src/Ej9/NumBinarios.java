package Ej9;

public class NumBinarios implements Runnable{

    private final String nombre;
    private final char numeros;
    private final String texto;

    public NumBinarios(String nombre, String texto, char numeros) {
        this.nombre = nombre;
        this.numeros = numeros;
        this.texto = texto;
    }

    @Override
    public void run() {

        int contador= 0;
        char numeroActual;
        for (int i=0; i<texto.length(); i++) {
            numeroActual= texto.charAt(i);

            System.out.println(" [ " + nombre +  " ] " + "Total: " + contador);

            if ( numeros == numeroActual) {
                contador++;
            }


        }


        System.out.println(" [ " + nombre +  " ] " + "TotalFinal: " + contador);


    }
}
