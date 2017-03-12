package sincronizacion.clasesPropias.BarreraCiclica;

import sincronizacion.clasesPropias.Barrera.Barrera;
import sincronizacion.clasesPropias.Barrera.HiloContador;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 09/02/2017.
 */
public class Main {

    public static void main(String[] args) {

        int numeroDeHilos = 10;

        Barrera barrera = new BarreraCiclica(numeroDeHilos);
        List<HiloContador> hiloContadors = new ArrayList<>();


        for (Integer i = 0; i < numeroDeHilos; i++) {
            HiloContador hiloContador = new HiloContadorCiclico(i.toString(), barrera);
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