package pi.forkJoin;

import java.util.concurrent.ForkJoinPool;

public class CalculadorPi {
    final static double REFERENCIAPI = 3.14159265358979323846;
    private long numIntervalos;
    private double valorPi;
    private double anchuraIntervalo;

    public CalculadorPi(long numIntervalos) {
        this.numIntervalos = numIntervalos;
        anchuraIntervalo = 1.0 / numIntervalos;
    }

    public static void main(String args[]) {
        CalculadorPi programa = new CalculadorPi(100000000000L);

        long inicio = System.currentTimeMillis();
        double pi = programa.calcularPi();
        long fin = System.currentTimeMillis();
        System.out.println("valor de pi: " + pi);
        System.out.println("Error referencia = " + (CalculadorPi.REFERENCIAPI - pi));
        System.out.println("Tiempo empleado : " + (fin - inicio) + " ms");
    }

    public double calcularPi() {
        valorPi = 0;
        ForkJoinPool pool = new ForkJoinPool();
        valorPi = pool.invoke(new RecursivePiTask(anchuraIntervalo, 0, numIntervalos));
        return valorPi * anchuraIntervalo;
    }
}
