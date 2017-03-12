package caminoMasCorto.listaThread;

public class Punto {
    int coordenadaX;
    int coordenadaY;

    public Punto(int valorX, int valorY) {
        coordenadaX = valorX;
        coordenadaY = valorY;
    }

    public int getCoordenadaX() {
        return coordenadaX;
    }

    public void setCoordenadaX(int coordenadaX) {
        this.coordenadaX = coordenadaX;
    }

    public int getCoordenadaY() {
        return coordenadaY;
    }

    public void setCoordenadaY(int coordenadaY) {
        this.coordenadaY = coordenadaY;
    }

    public void mover(Punto p) {
        coordenadaX = p.coordenadaX;
        coordenadaY = p.coordenadaY;
    }

    public double distancia(Punto p) {
        return Math.sqrt(Math.pow((this.coordenadaX - p.coordenadaX), 2.0) + Math.pow(this.coordenadaY - p.coordenadaY, 2.0));
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Punto)) return false;
        Punto punto = (Punto) obj;
        return (this.coordenadaX == punto.coordenadaX) && (this.coordenadaY == punto.coordenadaY);
    }

    @Override
    public String toString() {
        return "(" + coordenadaX + "," + coordenadaY + ")";
    }
}
