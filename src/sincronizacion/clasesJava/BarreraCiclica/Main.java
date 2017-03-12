package sincronizacion.clasesJava.BarreraCiclica;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ander on 09/02/2017.
 */
public class Main {

    public static void main(String[] args) {

        int numeroDeHilos = 10;

        CyclicBarrier barrera = new CyclicBarrier(numeroDeHilos);
        List<HiloContadorCiclico> hiloContadors = new ArrayList<>();


        for (Integer i = 0; i < numeroDeHilos; i++) {
            HiloContadorCiclico hiloContador = new HiloContadorCiclico(i.toString(), barrera);
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