package difusionCalor.executor;

import java.util.ArrayList;
import java.util.List;

public class Malla {
    int dimensionX;
    int dimensionY;
    List<FragmentoMalla> malla;
    long instante;

    public Malla(int dimX, int dimY) {
        this.dimensionX = dimX;
        this.dimensionY = dimY;
        malla = crearMalla();
        instante = System.currentTimeMillis();
    }

    private List<FragmentoMalla> crearMalla() {
        int numFragmentos = Runtime.getRuntime().availableProcessors();
        int chunkSize = dimensionX / numFragmentos + ((dimensionX % numFragmentos == 0) ? 0 : 1);
        List<FragmentoMalla> malla = new ArrayList<>();


        for (int i = 0; i < numFragmentos; i++) {
            FragmentoMalla fm = new FragmentoMalla(i * chunkSize, Math.min((i + 1) * chunkSize, dimensionX), dimensionY);

            malla.add(fm);
        }

        return malla;
    }


    public long getInstante() {
        return instante;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public List<FragmentoMalla> getMalla() {
        return malla;
    }

    public void inicializarMalla(double valor) {
        for (FragmentoMalla fm : malla) {
            for (double[] da : fm.fragmento) {
                for (double d : da) {
                    d = valor;
                }
            }
        }
    }

    public void setValor(int x, int y, double temperatura) {

        for (FragmentoMalla fm : malla) {
            if (fm.desde <= x && fm.hasta > x) {
                fm.fragmento[x - fm.desde][y] = temperatura;
                break;
            }
        }

    }

    public double getValor(int x, int y) {
        for (FragmentoMalla fm : malla) {
            if (fm.desde <= x && fm.hasta > x) {
                return fm.fragmento[x - fm.desde][y];
            }
        }
        return -1;
    }
}
