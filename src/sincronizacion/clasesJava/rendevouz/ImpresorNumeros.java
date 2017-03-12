package sincronizacion.clasesJava.rendevouz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by ander on 09/02/2017.
 */
public class ImpresorNumeros extends Thread {

    List<Integer> numeros = new ArrayList<>();
    Exchanger<List<Integer>> exchanger;

    public ImpresorNumeros(Exchanger<List<Integer>> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public synchronized void run() {
        for (int vez = 0; vez < 10; vez++) {
            for (int i : numeros) {
                System.out.println(i);
            }

            System.out.println("Impresion finalizada");

            try {
                numeros = exchanger.exchange(numeros);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
