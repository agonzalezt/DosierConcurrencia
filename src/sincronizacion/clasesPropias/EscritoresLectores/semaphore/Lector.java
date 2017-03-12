package sincronizacion.clasesPropias.EscritoresLectores.semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class Lector extends Thread {

    private BufferNumeros bufferNumeros;

    public Lector(BufferNumeros bufferNumeros) {
        this.bufferNumeros = bufferNumeros;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String str = "";
            for (Object num : bufferNumeros.leer()) {
                str += num + " ";
            }
            System.out.println(str);
        }
    }
}
