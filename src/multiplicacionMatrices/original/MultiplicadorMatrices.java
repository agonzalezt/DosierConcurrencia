package multiplicacionMatrices.original;

public class MultiplicadorMatrices {
	
	final int [][] matrizA;
	final int [][] matrizB;
	
	
	public MultiplicadorMatrices(int [][]matrizA, int [][]matrizB){
		this.matrizA = matrizA;
		this.matrizB = matrizB;
	}
	
	public long [][] multiplicar(){
		long [][] resultado;
		int dimensionX = matrizA.length;
		int dimensionY = matrizB[0].length;
		resultado = new long [dimensionX][dimensionY];
		
		for (int i = 0; i< dimensionX; i++){
			for (int j=0; j<dimensionY; j++){
				int dimensionZ = matrizB.length;
				int suma = 0;
				for (int k = 0; k<dimensionZ; k++){
					suma += (matrizA[i][k]*matrizB[k][j]);
				}
				resultado[i][j]= suma;
			}
		}
		return resultado;
		
	}
}
