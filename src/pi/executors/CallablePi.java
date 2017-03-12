package pi.executors;

import java.util.concurrent.Callable;

/**
 * Created by ander on 22/02/2017.
 */
public class CallablePi implements Callable<Double> {

    double anchoIntervalo;
    long desde;
    long hasta;

    public CallablePi(double anchoIntervalo, long desde, long hasta) {
        this.anchoIntervalo = anchoIntervalo;
        this.desde = desde;
        this.hasta = hasta;
    }

    @Override
    public Double call() throws Exception {
        double valorParcial = 0;
        double x;
        for (long i = desde; i < hasta; i++) {
            x = (i + 0.5) * anchoIntervalo;
            valorParcial += 4 / (1 + (x * x));
        }
        return valorParcial;
    }
}
