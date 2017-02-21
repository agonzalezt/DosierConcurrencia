package difusionCalor.original;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

public class DifusorCalor extends Observable{
	
	List<Malla> secuencia;
	double coeficienteDifusionX;
	double coeficienteDifusionY;
	Malla mallaInicial, nuevaMalla; 
	List<PuntoCalor> focosCalor;
	
	public DifusorCalor( double cfX, double cfY,Malla malla, List<PuntoCalor> focosCalor ){
		secuencia = new ArrayList<>();
		this.coeficienteDifusionX = cfX;
		this.coeficienteDifusionY = cfY;
		this.mallaInicial = malla;
		this.focosCalor = focosCalor;
		for (PuntoCalor punto : this.focosCalor){
			mallaInicial.setValor(punto.getX(), punto.getY(), punto.getTemperatura());
		}
		secuencia.add(mallaInicial);
	}
	public void difundir(){
		int dimensionX = mallaInicial.getDimensionX();
		int dimensionY = mallaInicial.getDimensionY();
		
		nuevaMalla = new Malla(dimensionX,dimensionY);
		inicializarBordes(nuevaMalla);
		
		
		for (int i = 1; i<dimensionX-1; i++){
			for (int j = 1; j<dimensionY-1; j++){
				PuntoCalor punto = new PuntoCalor(i,j,0);
				if (esFocoCalor(punto)){
					nuevaMalla.setValor(i, j, punto.getTemperatura());
				}else{
				  double temperatura = mallaInicial.getValor(i, j) + 
						  coeficienteDifusionX * ((mallaInicial.getValor(i-1, j)+ mallaInicial.getValor(i+1, j))- (2*mallaInicial.getValor(i, j)))+
						  coeficienteDifusionY * ((mallaInicial.getValor(i, j-1)+ mallaInicial.getValor(i, j+1))- (2*mallaInicial.getValor(i, j)));
				  
				  nuevaMalla.setValor(i,j,temperatura);
				}
			}
		}
	}
	private void inicializarBordes(Malla malla) {
		int dimensionX = malla.getDimensionX();
		int dimensionY = malla.getDimensionY();
		for (int i=0; i<dimensionX; i++){
			malla.setValor(i, 0,10);
			malla.setValor(i, dimensionY-1, 10);
		}
		for (int i=0; i<dimensionY; i++){
			malla.setValor(0, i,10);
			malla.setValor(dimensionX-1, i, 10);
		}
		
	}
	private boolean esFocoCalor(PuntoCalor punto) {
		for (PuntoCalor puntoCalor : focosCalor){
			if (puntoCalor.getX() == punto.getX() && puntoCalor.getY() == punto.getY()){
				punto.setTemperatura(puntoCalor.getTemperatura());
				return true;
			}
			
		}
		return false;
	}
	public Malla getNuevaMalla() {
		
		return nuevaMalla;
	}
	public void cambiar() {
		this.setChanged();
		this.notifyObservers(nuevaMalla);
		mallaInicial = nuevaMalla;
		
	}
	public Malla getMallaInicial() {
		
		return mallaInicial;
	}
}
