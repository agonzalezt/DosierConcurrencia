package sincronizacion.clasesJava.rendevouz;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * Created by ander on 09/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        GeneradorNumeros generadorNumeros = new GeneradorNumeros(exchanger);
        ImpresorNumeros impresorNumeros = new ImpresorNumeros(exchanger);

        generadorNumeros.start();
        impresorNumeros.start();

        try {
            impresorNumeros.join();
            generadorNumeros.join();
        } catch (InterruptedException ie) {
        }

    }


}
