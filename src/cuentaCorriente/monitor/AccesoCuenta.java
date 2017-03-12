package cuentaCorriente.monitor;

/**
 * Created by ander on 09/02/2017.
 */
public class AccesoCuenta {
    Cuenta cuenta;

    public AccesoCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public void meter(int cantidad) {

        synchronized (cuenta) {
            cuenta.meter(cantidad);
        }


    }

    public void sacar(int cantidad) {

        synchronized (cuenta) {
            cuenta.sacar(cantidad);
        }
    }
}