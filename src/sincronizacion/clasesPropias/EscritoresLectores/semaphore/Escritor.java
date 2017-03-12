package sincronizacion.clasesPropias.EscritoresLectores.semaphore;

import java.util.Random;

/**
 * Created by ander on 09/02/2017.
 */
public class Escritor extends Thread {
    private BufferNumeros buffer;
    private Random rng;

    public Escritor(BufferNumeros buffer, Random rng) {
        this.buffer = buffer;
        this.rng = rng;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int num = rng.nextInt(100);
            buffer.escribir(num);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
