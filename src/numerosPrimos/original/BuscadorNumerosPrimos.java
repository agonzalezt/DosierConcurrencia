package numerosPrimos.original;

import java.util.ArrayList;
import java.util.List;

public class BuscadorNumerosPrimos {
	
	List<Integer> numerosPrimos;
	
	public BuscadorNumerosPrimos(){
		numerosPrimos = new ArrayList<>();
	}
	public void buscarNumerosPrimos(int limite){
		for (int i = 2; i<limite; i++){
			if (esPrimo(i)){
				numerosPrimos.add(i);
			}
		}
		
	}
	private boolean esPrimo(int valor){
		for (int i = 2; i<(Math.sqrt(valor)+1);i++){
			if (valor%i ==0 ){
				return false;
			}
		}
		return true;
	}
	public void mostrarResultados(boolean all){
		if (all){
			for(int i= 0; i<numerosPrimos.size();i++){
				if (i%20 == 0){
					System.out.println();
				}else{
					System.out.print(numerosPrimos.get(i)+" ");
				}
			}
		}
		System.out.println();
		System.out.println("Numeros primos encontrados: "+ numerosPrimos.size());
	}
	public static void main(String[] args) {
		BuscadorNumerosPrimos programa = new BuscadorNumerosPrimos();
		long inicio = System.currentTimeMillis();
		programa.buscarNumerosPrimos(10000000);
		long fin = System.currentTimeMillis();
		
		programa.mostrarResultados(false);
		System.out.println("Tiempo empleado: "+(fin - inicio));
	}

}
