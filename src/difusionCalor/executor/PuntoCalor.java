package difusionCalor.executor;

public class PuntoCalor {
    int x;
    int y;
    double temperatura;

    public PuntoCalor(int x, int y, double temperatura) {
        this.x = x;
        this.y = y;
        this.temperatura = temperatura;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

}
