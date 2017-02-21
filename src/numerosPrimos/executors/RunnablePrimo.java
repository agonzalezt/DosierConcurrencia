package numerosPrimos.executors;

/**
 * Created by ander on 15/02/2017.
 */
public class RunnablePrimo implements Runnable {
    int desde, hasta;
    BuscadorNumerosPrimos buscador;
    public RunnablePrimo(BuscadorNumerosPrimos buscador, int desde, int hasta) {
        this.desde = desde;
        this.hasta = hasta;
        this.buscador = buscador;
    }

    @Override
    public void run() {
        buscador.buscarNumerosPrimos(desde, hasta);
    }
}