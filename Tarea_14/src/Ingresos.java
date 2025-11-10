public class Ingresos extends Thread{

    private Object candado;

    public Ingresos(Object candado) {
        this.candado = candado;
    }


    @Override
    public void run() {
        for (int i=0; i<5000; i++){
            synchronized (candado) {
                Caja.capital = Caja.capital+10;
            }


        }
    }



}
