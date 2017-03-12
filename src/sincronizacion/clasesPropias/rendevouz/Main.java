package sincronizacion.clasesPropias.rendevouz;

/**
 * Created by ander on 09/02/2017.
 */
public class Main {

    public static void main(String[] args) {
        GeneradorNumeros generadorNumeros = new GeneradorNumeros();
        ImpresorNumeros impresorNumeros = new ImpresorNumeros();
        Mercado mercado = new Mercado(generadorNumeros, impresorNumeros);

        generadorNumeros.start();
        impresorNumeros.start();

        try {
            impresorNumeros.join();
            generadorNumeros.join();
        } catch (InterruptedException ie) {
        }

    }


}
