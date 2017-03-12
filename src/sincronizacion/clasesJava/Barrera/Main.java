package sincronizacion.clasesJava.Barrera;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by ander on 09/02/2017.
 */
public class Main {

    public static void main(String[] args) {

        int numeroDeHilos = 10;
        CountDownLatch barrera = new CountDownLatch(numeroDeHilos);
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
