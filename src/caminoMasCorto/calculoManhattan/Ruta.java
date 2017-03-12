package caminoMasCorto.calculoManhattan;

import java.util.ArrayList;
import java.util.List;

public class Ruta {
    List<Nodo> ruta;
    int distancia;
    int recorrido;

    public Ruta(Punto origen, int distancia, int recorrido) {
        ruta = new ArrayList<>();
        ruta.add(new Nodo(origen, distancia, recorrido));
        this.distancia = distancia;
        this.recorrido = recorrido;
    }

    public Ruta(Ruta ruta) {
        this.ruta = new ArrayList<>();
        this.ruta.addAll(ruta.ruta);
    }

    public int getDistancia() {
        return distancia;
    }

    public int getRecorrido() {
        return recorrido;
    }

    public Nodo getLastNodo() {

        return ruta.get(ruta.size() - 1);
    }

    public boolean contiene(Nodo nodo) {

        return ruta.contains(nodo);
    }

    public void add(Nodo nodo) {
        ruta.add(nodo);
        this.distancia = nodo.getDistancia();
        this.recorrido = nodo.getRecorrido();
    }

    public List<Nodo> getListaNodos() {
        return this.ruta;
    }

}
