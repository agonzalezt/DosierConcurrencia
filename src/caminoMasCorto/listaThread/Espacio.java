package caminoMasCorto.listaThread;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Espacio {
    final String NOMBREFICHERO = "files/espacio.txt";
    final int VALORORIGEN = 2;
    final int VALORDESTINO = 3;
    final int DIMENSION = 80;

    int espacio[][];
    Punto origen, destino;

    public Espacio() {
        espacio = new int[DIMENSION][DIMENSION];
        leerEspacioFichero();

    }

    private void leerEspacioFichero() {
        try (BufferedReader in = new BufferedReader(new FileReader(NOMBREFICHERO))) {
            String linea;
            int fila = 0;
            while ((linea = in.readLine()) != null) {
                String valores[] = linea.split("[ ]");
                for (int i = 0; i < valores.length; i++) {
                    espacio[fila][i] = Integer.valueOf(valores[i]);
                }
                fila++;
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Punto getOrigen() {
        return origen;
    }

    public void setOrigen(Punto p) {
        origen = p;
        espacio[origen.coordenadaX][origen.coordenadaY] = VALORORIGEN;
    }

    public Punto getDestino() {
        return destino;
    }

    public void setDestino(Punto p) {
        destino = p;
        espacio[destino.coordenadaX][destino.coordenadaY] = VALORDESTINO;
    }

    public void verEspacio() {
        for (int i = 0; i < DIMENSION; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                System.out.print(espacio[i][j] + " ");
            }
            System.out.println();
        }
    }

    public List<Nodo> getNodosAdyacentes(Nodo nodo) {
        List<Nodo> adyacentes = new ArrayList<>();
        Punto punto = nodo.getPunto();
        int coordenadaX = punto.getCoordenadaX();
        int coordenadaY = punto.getCoordenadaY();
        int limiteInferiorX = (coordenadaX == 0) ? 0 : coordenadaX - 1;
        int limiteSuperiorX = (coordenadaX == DIMENSION - 1) ? DIMENSION - 1 : coordenadaX + 1;
        int limiteInferiorY = (coordenadaY == 0) ? 0 : coordenadaY - 1;
        int limiteSuperiorY = (coordenadaY == DIMENSION - 1) ? DIMENSION - 1 : coordenadaY + 1;

        for (int i = limiteInferiorX; i <= limiteSuperiorX; i++) {
            for (int j = limiteInferiorY; j <= limiteSuperiorY; j++) {
                if (espacio[i][j] != 1) {
                    Punto newPunto = new Punto(i, j);
                    if (!punto.equals(newPunto)) {
                        adyacentes.add(new Nodo(newPunto, 0, nodo.getRecorrido() + 1));
                    }
                }
            }
        }

        return adyacentes;
    }

    public void verRutaEnEspacio(Ruta ruta) {
        if (ruta == null) {
            System.out.println("No se ha encontrado camino");
        } else {
            List<Nodo> nodos = ruta.getListaNodos();
            for (Nodo nodo : nodos) {
                Punto posicion = nodo.getPunto();
                espacio[posicion.coordenadaX][posicion.coordenadaY] = 4;
            }
        }

        this.verEspacio();
    }
}
