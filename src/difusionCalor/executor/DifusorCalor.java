package difusionCalor.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.*;

public class DifusorCalor extends Observable {

    List<Malla> secuencia;
    double coeficienteDifusionX;
    double coeficienteDifusionY;
    Malla mallaInicial, nuevaMalla;
    List<PuntoCalor> focosCalor;
    CompletionService<FragmentoMalla> pool_difusion = new ExecutorCompletionService<>(Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()));

    public DifusorCalor(double cfX, double cfY, Malla malla, List<PuntoCalor> focosCalor) {
        secuencia = new ArrayList<>();
        this.coeficienteDifusionX = cfX;
        this.coeficienteDifusionY = cfY;
        this.mallaInicial = malla;
        this.focosCalor = focosCalor;
        for (PuntoCalor punto : this.focosCalor) {
            mallaInicial.setValor(punto.getX(), punto.getY(), punto.getTemperatura());
        }
        secuencia.add(mallaInicial);
    }

    public void difundir() {
        int dimensionX = mallaInicial.getDimensionX();
        int dimensionY = mallaInicial.getDimensionY();

        nuevaMalla = new Malla(dimensionX, dimensionY);
        nuevaMalla.malla.clear();

        for (FragmentoMalla fm : mallaInicial.malla) {
            pool_difusion.submit(new difusorFragmento(fm));
        }
        for (FragmentoMalla fm : mallaInicial.malla) {
            try {
                nuevaMalla.malla.add(pool_difusion.take().get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        inicializarBordes(nuevaMalla);
    }

    private void inicializarBordes(Malla malla) {
        int dimensionX = malla.getDimensionX();
        int dimensionY = malla.getDimensionY();
        for (int i = 0; i < dimensionX; i++) {
            malla.setValor(i, 0, 10);
            malla.setValor(i, dimensionY - 1, 10);
        }
        for (int i = 0; i < dimensionY; i++) {
            malla.setValor(0, i, 10);
            malla.setValor(dimensionX - 1, i, 10);
        }

    }

    private boolean esFocoCalor(PuntoCalor punto) {
        for (PuntoCalor puntoCalor : focosCalor) {
            if (puntoCalor.getX() == punto.getX() && puntoCalor.getY() == punto.getY()) {
                punto.setTemperatura(puntoCalor.getTemperatura());
                return true;
            }

        }
        return false;
    }

    public Malla getNuevaMalla() {

        return nuevaMalla;
    }

    public void cambiar() {
        this.setChanged();
        this.notifyObservers(nuevaMalla);
        mallaInicial = nuevaMalla;

    }

    public Malla getMallaInicial() {

        return mallaInicial;
    }

    private class difusorFragmento implements Callable<FragmentoMalla> {

        FragmentoMalla fm;

        public difusorFragmento(FragmentoMalla fm) {
            this.fm = fm;
        }

        @Override
        public FragmentoMalla call() throws Exception {
            FragmentoMalla newFm = new FragmentoMalla(fm.desde, fm.hasta, fm.alto);

            for (int x = 0; x < fm.fragmento.length; x++) {
                for (int y = 1; y < fm.fragmento[x].length - 1; y++) {
                    PuntoCalor punto = new PuntoCalor(x + fm.desde, y, 0);
                    if (esFocoCalor(punto)) {
                        newFm.fragmento[x][y] = punto.getTemperatura();
                    } else {
                        double temperatura = mallaInicial.getValor(x + fm.desde, y) +
                                coeficienteDifusionX * ((mallaInicial.getValor(x + fm.desde - 1, y) +
                                        mallaInicial.getValor(x + fm.desde + 1, y)) -
                                        (2 * mallaInicial.getValor(x + fm.desde, y))) +
                                coeficienteDifusionY * ((mallaInicial.getValor(x + fm.desde, y - 1) +
                                        mallaInicial.getValor(x + fm.desde, y + 1)) -
                                        (2 * mallaInicial.getValor(x + fm.desde, y)));

                        newFm.fragmento[x][y] = temperatura;
                    }
                }
            }
            return newFm;
        }
    }

}
