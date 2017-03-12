package sincronizacion.clasesJava.rendevouz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;

/**
 * Created by ander on 09/02/2017.
 */
public class GeneradorNumeros extends Thread {

    private List<Integer> numeros = new ArrayList<>();
    private Random rng = new Random();
    private Exchanger<List<Integer>> exchanger;

    public GeneradorNumeros(Exchanger<List<Integer>> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public synchronized void run() {
        for (int vez = 0; vez < 10; vez++) {
            for (int i = 0; i < 10; i++) {
                numeros.add(rng.nextInt(100));
            }

            System.out.println("Generacion finalizada");

            try {
                numeros = exchanger.exchange(numeros);
                numeros.clear();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
