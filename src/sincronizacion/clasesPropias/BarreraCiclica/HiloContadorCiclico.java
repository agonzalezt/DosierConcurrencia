package sincronizacion.clasesPropias.BarreraCiclica;


import sincronizacion.clasesPropias.Barrera.Barrera;
import sincronizacion.clasesPropias.Barrera.HiloContador;

/**
 * Created by ander on 09/02/2017.
 */
public class HiloContadorCiclico extends HiloContador {


    public HiloContadorCiclico(String name, Barrera barrera) {
        super(name, barrera);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            super.run();
        }
        System.out.println("RIP hilo " + Thread.currentThread().getName());
    }
}
