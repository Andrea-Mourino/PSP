package Ej10;

public class OperacionSintesis implements Runnable{

    private final Almacen almacenCompartido;
    private final String nombre;
    private final int monto;
    private final boolean esAgregado;
    private final int numOperaciones = 7000;

    public OperacionSintesis(Almacen almacenCompartido, String nombre, int monto, boolean esAgregado) {
        this.almacenCompartido = almacenCompartido;
        this.nombre = nombre;
        this.monto = monto;
        this.esAgregado = esAgregado;
    }

    @Override
    public void run() {

        for (int i=0; i<numOperaciones; i++) {

            if (esAgregado) {
                almacenCompartido.agregarMaterial(monto);

            } else {
                almacenCompartido.consumirMaterial(monto);

            }
            System.out.println(" [ " + nombre + " ] " + "Materiales: " + i);



        }



    }
}
