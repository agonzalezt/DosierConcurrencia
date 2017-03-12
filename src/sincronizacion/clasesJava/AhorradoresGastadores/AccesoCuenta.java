package sincronizacion.clasesJava.AhorradoresGastadores;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by ander on 09/02/2017.
 */
public class AccesoCuenta extends Thread {

    Cuenta cuenta;
    BlockingQueue<Integer> acciones = new ArrayBlockingQueue<>(100);
    boolean fin = false;
    boolean interrumpido = false;

    public AccesoCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public void run() {
        while (!interrumpido) {
            try {

                int accion = acciones.take();
                cuenta.meter(accion);
            } catch (InterruptedException e) {
            }
        }
    }

    public void finalizar() {
        fin = true;
        while (acciones.peek() != null) {
        }
        interrumpido = true;
        this.interrupt();
    }

    public void meter(int cantidad) {
        if (!fin) {
            try {
                acciones.put(cantidad);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void sacar(int cantidad) {
        if (!fin) {
            try {
                acciones.put(-cantidad);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}