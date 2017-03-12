package sincronizacion.clasesPropias.AhorradoresGastadores;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ander on 09/02/2017.
 */
public class AccesoCuenta extends Thread {

    Cuenta cuenta;
    boolean fin = false;
    private List<Integer> acciones = new ArrayList<>();

    public AccesoCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public synchronized void run() {
        while (!fin || acciones.size() > 0) {
            if (acciones.size() > 0) {
                synchronized (acciones) {
                    for (int i : acciones) {
                        cuenta.meter(i);
                    }
                    acciones.clear();
                }
            } else {
                try {
                    this.wait(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void finalizar() {
        fin = true;
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void meter(int cantidad) {
        if (!fin) {
            synchronized (acciones) {
                acciones.add(cantidad);
                this.notify();
            }
        }

    }

    public synchronized void sacar(int cantidad) {
        if (!fin) {
            synchronized (acciones) {
                acciones.add(-cantidad);
            }
            this.notify();
        }
    }
}