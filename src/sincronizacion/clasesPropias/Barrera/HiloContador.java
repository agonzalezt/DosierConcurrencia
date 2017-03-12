package sincronizacion.clasesPropias.Barrera;

/**
 * Created by ander on 09/02/2017.
 */
public class HiloContador extends Thread {

    Barrera barrera;

    public HiloContador(String name, Barrera barrera) {
        super(name);
        this.barrera = barrera;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println("El contador " + Thread.currentThread().getName() + " a terminado de contar");
        barrera.esperar();
        System.out.println("Adiosito por parte del contador " + Thread.currentThread().getName());
    }
}
