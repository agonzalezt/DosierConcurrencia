package multiplicacionMatrices.resultadoComunExecutors;

import java.util.Random;

public class GeneradorMatriz {
    final int LIMITEVALORES = 10;
    final int dimensionX;
    final int dimensionY;
    final int matriz[][];
    Random random;

    public GeneradorMatriz(int x, int y) {
        this.dimensionX = x;
        this.dimensionY = y;
        matriz = new int[dimensionX][dimensionY];
        random = new Random();
        this.generarMatriz();
    }

    private void generarMatriz() {
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                matriz[i][j] = random.nextInt(LIMITEVALORES);
            }
        }

    }

    int[][] getMatriz() {
        return matriz;
    }
}
