package cuentaCorriente.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ander on 09/02/2017.
 */
public class AccesoCuenta {
    Cuenta cuenta;
    Lock acceso = new ReentrantLock();

    public AccesoCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void meter(int cantidad) {

        acceso.lock();
        cuenta.meter(cantidad);
        acceso.unlock();

    }

    public void sacar(int cantidad) {

        acceso.lock();
        cuenta.sacar(cantidad);
        acceso.unlock();

    }
}