package caminoMasCorto.original;

import java.util.List;

public class BuscadorCaminoMasCorto {

	ListaRutas listaRutas;
	Espacio espacio;
	Punto origen, destino;
	Ruta rutaMasCorta;
	
	public BuscadorCaminoMasCorto(Espacio espacio){
		listaRutas = new ListaRutas();
		this.espacio = espacio;
		this.origen = espacio.getOrigen();
		this.destino = espacio.getDestino();
		
		listaRutas.add(new Ruta(origen,this.manhattan(origen,destino),0));
	}

	private int manhattan(Punto punto1, Punto punto2) {
		
		return Math.abs(punto1.getCoordenadaX()-punto2.getCoordenadaX())+Math.abs(punto1.getCoordenadaY()-punto2.getCoordenadaY());
	}
	
	public Ruta buscarCaminoMasCorto(){
		while (!listaRutas.vacia()){
			Ruta ruta = listaRutas.take();
			
			Nodo nodo = ruta.getLastNodo();
			if (nodo.getPunto().equals(destino)){
				rutaMasCorta = ruta;
				break;
			}
			List<Nodo> adyacentes = espacio.getNodosAdyacentes (nodo);
			for (Nodo n : adyacentes){
				n.setDistancia(this.manhattan(n.getPunto(), destino));
			}
			listaRutas.addRutas(ruta,adyacentes);
		}
		
		return rutaMasCorta;
	}
	
}
