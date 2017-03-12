package multiplicacionMatrices.executors;

public class Principal {

    final int DIMENSIONX = 1000;
    final int DIMENSIONY = 1000;
    int matrizA[][];
	int matrizB[][];
	long resultado[][];
	
	MultiplicadorMatrices multiplicador;
	
	public Principal(){
		matrizA = new GeneradorMatriz(DIMENSIONX,DIMENSIONY).getMatriz();
		matrizB = new GeneradorMatriz(DIMENSIONY,DIMENSIONX).getMatriz();
		multiplicador = new MultiplicadorMatrices (matrizA,matrizB);
	}

    public static void main(String[] args) {
        Principal programa = new Principal();
        long instanteInicial = System.currentTimeMillis();
        programa.multiplicar();
        long instanteFinal = System.currentTimeMillis();
        System.out.println("Tiempo utilizado:  " + (instanteFinal - instanteInicial));
        programa.visualizarMatrices();
    }

	public void multiplicar(){
		resultado = multiplicador.multiplicar();
	}

    private void visualizarMatriz(int [][] matriz,String texto){
		System.out.println(texto);
		int dimX = matriz.length;
		int dimY = matriz[0].length;
		for (int i = 0;i<dimX;i++ ){
			for (int j= 0; j<dimY; j++){
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
	}

    private void visualizarMatrizLong (long [][] matriz,String texto){
		System.out.println(texto);
		int dimX = matriz.length;
		int dimY = matriz[0].length;
		for (int i = 0;i<dimX;i++ ){
			for (int j= 0; j<dimY; j++){
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println();
		}
	}

    public void visualizarMatrices(){
		visualizarMatriz(matrizA, "Matriz A: ");
		visualizarMatriz(matrizB, "Matriz : ");
		visualizarMatrizLong(resultado, "Resultado: ");
	}
	

}
