package sincronizacion.clasesJava.EscritoresLectores;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by ander on 09/02/2017.
 */
public class BufferNumeros {

    List<Integer> buffer = new ArrayList<>();
    ReadWriteLock lock = new ReentrantReadWriteLock(true);
    private Integer lectores = 0;

    public void escribir(int numero) {
        lock.writeLock().lock();
        System.out.println("Escribiendo");
        buffer.add(numero);
        lock.writeLock().unlock();
    }

    public Object[] leer() {
        Object[] retVal = null;
        lock.readLock().lock();
        System.out.println("Leyendo");
        retVal = buffer.toArray();
        lock.readLock().unlock();
        return retVal;
    }
}
