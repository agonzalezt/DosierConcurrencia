package sincronizacion.clasesPropias.Barrera;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 09/02/2017.
 */
public class Main {

    public static void main(String[] args) {

        int numeroDeHilos = 10;
        Barrera barrera = new Barrera(numeroDeHilos);
        List<HiloContador> hiloContadors = new ArrayList<>();


        for (Integer i = 0; i < numeroDeHilos; i++) {
            HiloContador hiloContador = new HiloContador(i.toString(), barrera);
            hiloContadors.add(hiloContador);
            hiloContador.start();
        }

        for (Thread hilo : hiloContadors) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
