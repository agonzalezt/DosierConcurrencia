package multiplicacionMatrices.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MultiplicadorMatrices {

    final int[][] matrizA;
    final int[][] matrizB;


    public MultiplicadorMatrices(int[][] matrizA, int[][] matrizB) {
        this.matrizA = matrizA;
        this.matrizB = matrizB;
    }

    public long[][] multiplicar() {
        long[][] resultado;
        int dimensionX = matrizA.length;
        int dimensionY = matrizB[0].length;
        resultado = new long[dimensionX][dimensionY];

        int procesadores = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(procesadores);
        for (int i = 0; i < procesadores; i++) {
            FragmentoMx task = new FragmentoMx(resultado, matrizA, matrizB, i * dimensionX / procesadores, (i + 1) * dimensionX / procesadores);
            executorService.submit(task);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultado;

    }

    class FragmentoMx implements Runnable {
        int[][] matrizA;
        int[][] matrizB;
        int desde, hasta;
        public long[][] resultado;
        public long[][] resultado_final;


        public FragmentoMx(long[][] resultado_final, int[][] A, int[][] B, int desde, int hasta) {
            matrizA = A;
            matrizB = B;
            this.desde = desde;
            this.hasta = hasta;
            this.resultado_final = resultado_final;
        }

        @Override
        public void run() {
            int dimensionX = hasta - desde;
            int dimensionY = matrizB[0].length;
            resultado = new long[dimensionX][dimensionY];

            for (int i = 0; i < dimensionX; i++) {
                for (int j = 0; j < dimensionY; j++) {
                    int dimensionZ = matrizB.length;
                    int suma = 0;
                    for (int k = 0; k < dimensionZ; k++) {
                        suma += (matrizA[i][k] * matrizB[k][j]);
                    }
                    resultado[i][j] = suma;
                }
            }
            for (int i = desde; i < hasta; i++) {
                resultado_final[i] = resultado[i - desde];
            }
        }
    }


}
