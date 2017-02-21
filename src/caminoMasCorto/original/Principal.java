package caminoMasCorto.original;

import java.util.List;

public class Principal {

	Espacio espacio;
	BuscadorCaminoMasCorto buscador;
	Ruta rutaMasCorta;
	
	public Principal(){
		espacio = new Espacio();
		espacio.setOrigen(new Punto(0,0));
		espacio.setDestino(new Punto (79,79));
		//espacio.verEspacio();
		buscador = new BuscadorCaminoMasCorto(espacio);
	}
	public void buscarCaminoMasCorto(){
		rutaMasCorta = buscador.buscarCaminoMasCorto();
		
	}
	private void mostrarCaminoMasCorto() {
		espacio.verRutaEnEspacio(rutaMasCorta);
		
		
	}
	public static void main(String[] args) {
		Principal programa = new Principal();
		programa.buscarCaminoMasCorto();
		programa.mostrarCaminoMasCorto();
	}
	

}
