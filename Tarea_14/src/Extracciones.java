public class Extracciones extends Thread {

    private Object candado;


    public Extracciones(Object candado) {
        this.candado = candado;
    }

    @Override
    public void run() {
        for(int i=0; i<3000; i++) {
            synchronized (candado) {
                Caja.capital = Caja.capital-10;
            }


        }
    }



}
