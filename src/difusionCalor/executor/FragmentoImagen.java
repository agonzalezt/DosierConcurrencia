package difusionCalor.executor;

/**
 * Created by ander on 12/03/2017.
 */
public class FragmentoImagen {

    private int rgb[];
    private int alto;
    private int desde;
    private int hasta;

    public FragmentoImagen(int alto, int desde, int hasta) {
        this.rgb = new int[alto * (hasta - desde)];
        this.alto = alto;
        this.desde = desde;
        this.hasta = hasta;
    }

    public int[] getRgb() {
        return rgb;
    }

    public void setRgb(int[] rgb) {
        this.rgb = rgb;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
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
}
