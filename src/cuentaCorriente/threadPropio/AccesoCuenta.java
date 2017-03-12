package cuentaCorriente.threadPropio;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ander on 09/02/2017.
 */
public class AccesoCuenta {
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    Cuenta cuenta;

    public AccesoCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void finalizar() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(100, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void meter(int cantidad) {
        executorService.submit(new Ahorrador(cuenta, cantidad));
    }

    public void sacar(int cantidad) {
        executorService.submit(new Gastador(cuenta, cantidad));
    }

    private class Ahorrador implements Runnable {
        Cuenta cuenta;
        int cantidad;

        public Ahorrador(Cuenta cuenta, int cantidad) {
            this.cuenta = cuenta;
            this.cantidad = cantidad;
        }

        @Override
        public void run() {
            cuenta.meter(cantidad);
        }
    }

    private class Gastador implements Runnable {

        Cuenta cuenta;
        int cantidad;

        public Gastador(Cuenta cuenta, int cantidad) {
            this.cuenta = cuenta;
            this.cantidad = cantidad;
        }

        @Override
        public void run() {
            cuenta.sacar(cantidad);
        }
    }
}