package caminoMasCorto.original;

import java.util.ArrayList;
import java.util.List;

public class ListaRutas {
	List<Ruta> rutas;
	
	public ListaRutas(){
		rutas = new ArrayList<>();
	}
	public void add(Ruta ruta){
		rutas.add(0,ruta);
	}
	public boolean vacia() {
		
		return rutas.size() == 0;
	}
	public Ruta take() {
		int minima = 0;
		int minValor = Integer.MAX_VALUE;
		for (int i = 0; i<rutas.size();i++){
			int valor = (rutas.get(i).getDistancia()+rutas.get(i).getRecorrido());
			if (minValor > valor){
				minValor = valor;
				minima = i;
			}
			
		}
		return rutas.remove(minima);
	}
	public void addRutas(Ruta ruta, List<Nodo> adyacentes) {
		for (Nodo nodo : adyacentes){
			if (!explorado(nodo)){
				Ruta newRuta = new Ruta(ruta);
				newRuta.add(nodo);
				
				this.rutas.add(newRuta);
				
			}
		}
		
	}
	private boolean explorado(Nodo nodo) {
		for (Ruta ruta : rutas){
			if (ruta.contiene(nodo)){
				return true;
			}
		}
		return false;
	}
	
}
