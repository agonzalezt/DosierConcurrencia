package numerosPrimos.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BuscadorNumerosPrimos {

    ReadWriteLock lock = new ReentrantReadWriteLock();
    List<Integer> numerosPrimos;

    public BuscadorNumerosPrimos() {
        numerosPrimos = new ArrayList<>();
    }

    public void buscarNumerosPrimos(int desde, int hasta) {
        for (int i = desde; i < hasta; i++) {
            if (esPrimo(i)) {
                lock.writeLock().lock();
                numerosPrimos.add(i);
                lock.writeLock().unlock();
            }
        }

    }

    private boolean esPrimo(int valor) {
        double raiz = Math.sqrt(valor);
        for (int i = 2; i < (raiz + 1); i++) {
            if (valor % i == 0) {
                return false;
            }
        }
        return true;
    }

    public void mostrarResultados(boolean all) {
        lock.readLock().lock();
        if (all) {
            for (int i = 0; i < numerosPrimos.size(); i++) {
                if (i % 20 == 0) {
                    System.out.println();
                } else {
                    System.out.print(numerosPrimos.get(i) + " ");
                }
            }
        }
        System.out.println();
        System.out.println("Numeros primos encontrados: " + numerosPrimos.size());
        lock.readLock().unlock();
    }

    public static void main(String[] args) {
        BuscadorNumerosPrimos programa = new BuscadorNumerosPrimos();
        int rango = 10000000;
        int seccion = 1000;
        int procesadores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(procesadores);
        long inicio = System.currentTimeMillis();
        for (int i = 0; i < rango / seccion; i++) {
            RunnablePrimo hp = new RunnablePrimo(programa, i * seccion, (i + 1) * seccion);
            executorService.submit(hp);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long fin = System.currentTimeMillis();

        programa.mostrarResultados(false);
        System.out.println("Tiempo empleado: " + (fin - inicio));
    }

}
