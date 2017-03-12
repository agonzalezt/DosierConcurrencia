package sincronizacion.clasesPropias.EscritoresLectores.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by ander on 09/02/2017.
 */
public class BufferNumeros {

    List<Integer> buffer = new ArrayList<>();
    Semaphore preferenciaSemaphore = new Semaphore(1);
    Semaphore acceso = new Semaphore(1);
    private Integer lectores = 0;

    public void escribir(int numero) {
        try {
            preferenciaSemaphore.acquire();
            acceso.acquire();
            System.out.println("Escribiendo");
            buffer.add(numero);
            acceso.release();
            preferenciaSemaphore.release();
        } catch (InterruptedException ie) {
        }
    }

    public Object[] leer() {
        Object[] retVal = null;
        try {
            preferenciaSemaphore.acquire();
            preferenciaSemaphore.release();

            synchronized (lectores) {
                if (lectores <= 0) acceso.acquire();
                lectores++;
                System.out.println("Lectores " + lectores);
            }
            System.out.println("Leyendo");
            retVal = buffer.toArray();
            synchronized (lectores) {
                lectores--;
                if (lectores <= 0) acceso.release();
                System.out.println("Lectores " + lectores);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
