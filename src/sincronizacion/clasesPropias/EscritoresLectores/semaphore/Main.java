package sincronizacion.clasesPropias.EscritoresLectores.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ander on 09/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        BufferNumeros buffer = new BufferNumeros();
        List<Thread> hilos = new ArrayList<>();
        Random random = new Random(12);

        for (int i = 0; i < 3; i++) {
            if (i < 2) {
                Escritor escritor = new Escritor(buffer, random);
                hilos.add(escritor);
                escritor.start();
            }
            Lector lector = new Lector(buffer);
            hilos.add(lector);
            lector.start();
        }

        for (Thread t : hilos) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
