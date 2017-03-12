package sincronizacion.clasesPropias.BarreraCiclica;

import sincronizacion.clasesPropias.Barrera.Barrera;

import java.util.concurrent.Semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class BarreraCiclica extends Barrera {

    private Semaphore semaphore2 = new Semaphore(0);
    private Integer contador2 = 0;

    public BarreraCiclica(int numeroHilos) {
        super(numeroHilos);
    }

    @Override
    public void esperar() {
        synchronized (contador2) {
            contador2++;
            if (contador2 >= numeroHilos) {
                semaphore2.release(numeroHilos);
                contador2 = 0;
            }
        }
        try {
            semaphore2.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.esperar();
    }
}
