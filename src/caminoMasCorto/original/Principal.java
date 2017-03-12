package caminoMasCorto.original;

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

    public static void main(String[] args) {
        Principal programa = new Principal();
        long instanteInicial = System.currentTimeMillis();
        programa.buscarCaminoMasCorto();
        long instanteFinal = System.currentTimeMillis();
        System.out.println("Tiempo utilizado:  " + (instanteFinal - instanteInicial));
        //programa.mostrarCaminoMasCorto();
    }

	public void buscarCaminoMasCorto(){
		rutaMasCorta = buscador.buscarCaminoMasCorto();

	}

    private void mostrarCaminoMasCorto() {
		espacio.verRutaEnEspacio(rutaMasCorta);


    }
	

}
