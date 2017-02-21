package caminoMasCorto.original;

public class Nodo {

	Punto punto;
	int distancia;
	int recorrido;
	
	public Nodo (Punto punto, int distancia, int recorrido){
		this.punto = punto;
		this.distancia = distancia;
		this.recorrido = recorrido;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (!(obj instanceof Nodo)) return false;
		Nodo nodo = (Nodo) obj;
		return nodo.punto.equals(this.punto);
	}

	public Punto getPunto() {
		return punto;
	}

	public void setPunto(Punto punto) {
		this.punto = punto;
	}

	public int getDistancia() {
		return distancia;
	}

	public void setDistancia(int distancia) {
		this.distancia = distancia;
	}

	public int getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(int recorrido) {
		this.recorrido = recorrido;
	}
	
}
