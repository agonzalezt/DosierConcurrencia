package sincronizacion.clasesPropias.rendevouz;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class ImpresorNumeros extends Thread {

    List<Integer> numeros = new ArrayList<>();
    Semaphore miSemaphore = new Semaphore(0);
    Semaphore suSemaphore;
    Semaphore truequeFinalizado;
    Mercado mercado;

    @Override
    public synchronized void run() {
        for (int vez = 0; vez < 10; vez++) {

            try {
                truequeFinalizado.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i : numeros) {
                System.out.println(i);
            }

            System.out.println("Impresion finalizada");

            suSemaphore.release();
            try {
                miSemaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mercado.trucar();
        }
    }

    public Semaphore getTruequeFinalizado() {
        return truequeFinalizado;
    }

    public void setTruequeFinalizado(Semaphore truequeFinalizado) {
        this.truequeFinalizado = truequeFinalizado;
    }

    public Mercado getMercado() {
        return mercado;
    }

    public void setMercado(Mercado mercado) {
        this.mercado = mercado;
    }

    public Semaphore getMiSemaphore() {
        return miSemaphore;
    }

    public void setMiSemaphore(Semaphore miSemaphore) {
        this.miSemaphore = miSemaphore;
    }

    public Semaphore getSuSemaphore() {
        return suSemaphore;
    }

    public void setSuSemaphore(Semaphore suSemaphore) {
        this.suSemaphore = suSemaphore;
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
    }
}
