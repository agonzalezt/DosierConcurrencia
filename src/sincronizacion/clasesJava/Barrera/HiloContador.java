package sincronizacion.clasesJava.Barrera;

import java.util.concurrent.CountDownLatch;

/**
 * Created by ander on 09/02/2017.
 */
public class HiloContador extends Thread {

    CountDownLatch barrera;

    public HiloContador(String name, CountDownLatch barrera) {
        super(name);
        this.barrera = barrera;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println("El contador " + Thread.currentThread().getName() + " a terminado de contar");
        barrera.countDown();
        try {
            barrera.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Adiosito por parte del contador " + Thread.currentThread().getName());
    }
}
