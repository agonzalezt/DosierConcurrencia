package sincronizacion.clasesPropias.rendevouz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class GeneradorNumeros extends Thread {

    Semaphore truequeFinalizado;
    private List<Integer> numeros = new ArrayList<>();
    private Random rng = new Random();
    private Semaphore miSemaphore = new Semaphore(0);
    private Semaphore suSemaphore;
    private Mercado mercado;

    @Override
    public synchronized void run() {
        for (int vez = 0; vez < 10; vez++) {
            try {
                truequeFinalizado.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                numeros.add(rng.nextInt(100));
            }

            System.out.println("Generacion finalizada");

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

    public List<Integer> getNumeros() {
        return numeros;
    }

    public void setNumeros(List<Integer> numeros) {
        this.numeros = numeros;
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
}
