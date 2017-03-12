package cuentaCorriente.semaphore;

import java.util.concurrent.Semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class AccesoCuenta {
    Cuenta cuenta;
    Semaphore acceso = new Semaphore(1);

    public AccesoCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void meter(int cantidad) {

        try {
            acceso.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cuenta.meter(cantidad);
        acceso.release();

    }

    public void sacar(int cantidad) {

        try {
            acceso.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        cuenta.sacar(cantidad);
        acceso.release();

    }
}