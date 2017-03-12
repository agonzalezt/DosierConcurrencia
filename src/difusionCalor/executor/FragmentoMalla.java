package difusionCalor.executor;

/**
 * Created by ander on 11/03/2017.
 */
public class FragmentoMalla {

    int desde, hasta;
    int alto;
    double[][] fragmento;

    public FragmentoMalla(int desde, int hasta, int alto) {
        this.desde = desde;
        this.hasta = hasta;
        this.alto = alto;

        fragmento = new double[hasta - desde][alto];
    }

    public int getDesde() {
        return desde;
    }

    public void setDesde(int desde) {
        this.desde = desde;
    }

    public int getHasta() {
        return hasta;
    }

    public void setHasta(int hasta) {
        this.hasta = hasta;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public double[][] getFragmento() {
        return fragmento;
    }

    public void setFragmento(double[][] fragmento) {
        this.fragmento = fragmento;
    }
}
