package sincronizacion.clasesPropias.rendevouz;

import java.util.concurrent.Semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class Mercado {
    GeneradorNumeros generadorNumeros;
    ImpresorNumeros impresorNumeros;
    Semaphore botthSemaphore = new Semaphore(2);
    private Integer contador = 0;

    public Mercado(GeneradorNumeros g, ImpresorNumeros i) {
        generadorNumeros = g;
        impresorNumeros = i;

        generadorNumeros.setSuSemaphore(impresorNumeros.getMiSemaphore());
        impresorNumeros.setSuSemaphore(generadorNumeros.getMiSemaphore());

        impresorNumeros.setTruequeFinalizado(botthSemaphore);
        generadorNumeros.setTruequeFinalizado(botthSemaphore);

        generadorNumeros.setMercado(this);
        impresorNumeros.setMercado(this);

    }

    public synchronized void trucar() {
        boolean trucar = false;
        synchronized (contador) {
            contador++;
            if (contador % 2 != 0) {
                trucar = true;
            }
        }
        if (trucar) {
            impresorNumeros.getNumeros().clear();
            impresorNumeros.getNumeros().addAll(generadorNumeros.getNumeros());
            generadorNumeros.getNumeros().clear();

            System.out.println("Trueque realizado");

            botthSemaphore.release(2);
        }
    }


}
