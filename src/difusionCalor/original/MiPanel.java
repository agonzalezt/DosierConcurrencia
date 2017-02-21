package difusionCalor.original;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MiPanel extends JPanel implements Observer{

	DifusorCalor difusor;
	BufferedImage image;
	Malla malla;
	BufferedImage colores;
	
	public MiPanel (DifusorCalor difusor){
		super();
		try {
			colores = ImageIO.read(new File("files/colores.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.difusor= difusor;
		difusor.addObserver(this);
		malla = difusor.getMallaInicial();
		this.setPreferredSize(new Dimension(malla.dimensionX,malla.dimensionY));
		image = mallaToImage();
	}
	private BufferedImage mallaToImage() {
		BufferedImage image = new BufferedImage(malla.getDimensionX(),malla.getDimensionY(),BufferedImage.TYPE_4BYTE_ABGR);
		for (int i = 0; i<image.getHeight();i++){
			for (int j = 0; j<image.getWidth(); j++){
				image.setRGB(j, i, calcularRGB(malla.getValor(j, i)));
			}
		}
		return image;
	}
	private int calcularRGB(double valor) {
		int ancho = colores.getWidth()-1;
		
		int posicion = (int) (valor/3000.0 *ancho);
		posicion = (posicion<0)?0:posicion;
		posicion = (posicion>ancho-1)?ancho-1:posicion;
		
		int color = colores.getRGB(posicion, 0);
		return color;
	}
	@Override
	public void update(Observable o, Object arg) {
		malla = (Malla) arg;
		image = mallaToImage();
		this.repaint();
		
	}
	@Override
	public void paint(Graphics g) {
		
		Graphics2D gr = (Graphics2D) g;
		super.paint(gr);
		gr.drawImage(image, null, 0, 0);
		this.setOpaque(true);
		
	}
}
