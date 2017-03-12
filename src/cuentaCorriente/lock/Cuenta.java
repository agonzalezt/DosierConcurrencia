package cuentaCorriente.lock;

/**
 * Created by ander on 09/02/2017.
 */
public class Cuenta {

    private int saldo;

    public Cuenta(int saldo) {
        this.saldo = saldo;
    }

    public void meter(int balance) {
        saldo += balance;
    }

    public void sacar(int balance) {
        this.saldo -= balance;
    }

    public int getSaldo() {
        return saldo;
    }
}
