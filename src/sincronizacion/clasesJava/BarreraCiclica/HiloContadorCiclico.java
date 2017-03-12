package sincronizacion.clasesJava.BarreraCiclica;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by ander on 09/02/2017.
 */
public class HiloContadorCiclico extends Thread {


    private final CyclicBarrier barrera;

    public HiloContadorCiclico(String name, CyclicBarrier barrera) {
        super(name);
        this.barrera = barrera;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(j);
            }
            System.out.println("El contador " + Thread.currentThread().getName() + " a terminado de contar");
            try {
                barrera.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println("Adiosito por parte del contador " + Thread.currentThread().getName());
        }
        System.out.println("RIP hilo " + Thread.currentThread().getName());
    }
}
