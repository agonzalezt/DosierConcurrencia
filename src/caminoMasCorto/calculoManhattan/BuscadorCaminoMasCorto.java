package caminoMasCorto.calculoManhattan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BuscadorCaminoMasCorto {

    ListaRutas listaRutas;
    Espacio espacio;
    Punto origen, destino;
    Ruta rutaMasCorta;


    public BuscadorCaminoMasCorto(Espacio espacio) {
        listaRutas = new ListaRutas();
        this.espacio = espacio;
        this.origen = espacio.getOrigen();
        this.destino = espacio.getDestino();
        listaRutas.add(new Ruta(origen, manhattan(origen, destino), 0));
    }

    public static int manhattan(Punto punto1, Punto punto2) {

        return Math.abs(punto1.getCoordenadaX() - punto2.getCoordenadaX()) + Math.abs(punto1.getCoordenadaY() - punto2.getCoordenadaY());
    }

    public Ruta buscarCaminoMasCorto() {
        ExecutorService executor = Executors.newFixedThreadPool(8);
        while (!listaRutas.vacia()) {
            Ruta ruta = listaRutas.take();

            Nodo nodo = ruta.getLastNodo();
            if (nodo.getPunto().equals(destino)) {
                rutaMasCorta = ruta;
                break;
            }
            List<Nodo> adyacentes = espacio.getNodosAdyacentes(nodo);
            Collection<Callable<Integer>> runs = new ArrayList<>();
            for (Nodo n : adyacentes) {
                runs.add(new RunnableNodo(ruta, n));
            }
            try {
                executor.invokeAll(runs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return rutaMasCorta;
    }

    public class RunnableNodo implements Callable<Integer> {
        Ruta ruta;
        Nodo nodo;

        public RunnableNodo(Ruta ruta, Nodo nodo) {
            this.ruta = ruta;
            this.nodo = nodo;
        }

        @Override
        public Integer call() throws Exception {
            nodo.setDistancia(BuscadorCaminoMasCorto.manhattan(nodo.getPunto(), destino));
            listaRutas.addRutas(ruta, nodo);
            return 0;
        }
    }

}
