package difusionCalor.original;

public class Malla {
	int dimensionX;
	int dimensionY;
	double [][] malla;
	long instante;
	
	public Malla (int dimX, int dimY){
		this.dimensionX = dimX;
		this.dimensionY = dimY;
		malla = new double [dimX][dimY];
		instante = System.currentTimeMillis();
	}

	

	public long getInstante() {
		return instante;
	}



	public int getDimensionX() {
		return dimensionX;
	}

	public int getDimensionY() {
		return dimensionY;
	}

	public double[][] getMalla() {
		return malla;
	}
	public void inicializarMalla(double valor){
		for (int i = 0; i<dimensionX; i++){
			for (int j = 0; j<dimensionY; j++){
				malla[i][j] = valor;
			}
		}
	}
	public void setValor( int x, int y, double valor){
		malla [x][y] = valor;
	}
	public double getValor(int x, int y){
		return malla [x][y];
	}
	
}
