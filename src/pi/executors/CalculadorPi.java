package pi.executors;

import java.util.concurrent.*;

public class CalculadorPi {
    final static double REFERENCIAPI = 3.14159265358979323846;
    private long numIntervalos;
    private double valorPi;
    private double anchuraIntervalo;
    private long numeroSecciones;

    public CalculadorPi(long numIntervalos, long numeroSeciones) {
        this.numIntervalos = numIntervalos;
        this.numeroSecciones = numeroSeciones;
        anchuraIntervalo = 1.0 / numIntervalos;
    }

    public static void main(String args[]) {
        CalculadorPi programa = new CalculadorPi(100000000000L, 1000000);

        long inicio = System.currentTimeMillis();
        double pi = programa.calcularPi();
        long fin = System.currentTimeMillis();
        System.out.println("valor de pi: " + pi);
        System.out.println("Error referencia = " + (CalculadorPi.REFERENCIAPI - pi));
        System.out.println("Tiempo empleado : " + (fin - inicio) + " ms");
    }

    public double calcularPi() {
        valorPi = 0;
        ExecutorService executor = Executors.newFixedThreadPool(8);
        CompletionService<Double> doubleCompletionService = new ExecutorCompletionService<>(executor);
        long intervaloPorSeccion = numIntervalos / numeroSecciones;
        for (int i = 0; i < numeroSecciones; i++) {
            doubleCompletionService.submit(new CallablePi(anchuraIntervalo, i * intervaloPorSeccion, (i + 1) * intervaloPorSeccion));
        }
        for (int i = 0; i < numeroSecciones; i++)
            try {
                valorPi += doubleCompletionService.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return valorPi * anchuraIntervalo;
    }
}
