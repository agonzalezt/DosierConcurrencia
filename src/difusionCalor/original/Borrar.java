package difusionCalor.original;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Borrar {

	public static void main(String[] args) {
		try {
			BufferedImage colores = ImageIO.read(new File("files/colores.jpg"));
			for (int i = 0; i<colores.getWidth();i++){
				int color = colores.getRGB(i, 0);
				System.out.println(i+" "+String.format("%x",color));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
