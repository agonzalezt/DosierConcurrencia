package cuentaCorriente.lock;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class Main {


    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta(1000);
        AccesoCuenta accesoCuenta = new AccesoCuenta(cuenta);
        List<Thread> hilos = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            hilos.add(new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        accesoCuenta.meter(10);
                        accesoCuenta.sacar(10);
                    }
                }
            }));
        }
        long instanteInicial = System.currentTimeMillis();
        for (Thread hilo : hilos)
            hilo.start();

        for (Thread hilo : hilos) {
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long instanteFinal = System.currentTimeMillis();
        System.out.println("Tiempo utilizado:  " + (instanteFinal - instanteInicial));
        System.out.println("Saldo: " + cuenta.getSaldo());
    }


}
