package sincronizacion.clasesPropias.Barrera;

import java.util.concurrent.Semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class Barrera {

    protected int numeroHilos = 0;
    protected Integer contador = 0;
    protected Semaphore semaphore = new Semaphore(0);

    public Barrera(int numeroHilos) {
        this.numeroHilos = numeroHilos;
    }

    public void esperar() {
        System.out.println("El hilo " + Thread.currentThread().getName() + " a llegado a la barrera");
        synchronized (contador) {
            contador++;
            System.out.println("Barrera: Ya somos " + contador + "!");
            if (contador >= numeroHilos) {
                semaphore.release(numeroHilos);
                contador = 0;
            }
        }
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("El hilo " + Thread.currentThread().getName() + " a salido de la barrera");
    }


}
