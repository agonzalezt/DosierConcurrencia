package difusionCalor.executor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.*;

public class MiPanel extends JPanel implements Observer {

    DifusorCalor difusor;
    BufferedImage image;
    Malla malla;
    BufferedImage colores;
    int numThreads = Runtime.getRuntime().availableProcessors();
    CompletionService<FragmentoImagen> fiCompletionSrv =
            new ExecutorCompletionService<>(Executors.newFixedThreadPool(numThreads));


    public MiPanel(DifusorCalor difusor) {
        super();
        try {
            colores = ImageIO.read(new File("files/colores.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.difusor = difusor;
        difusor.addObserver(this);
        malla = difusor.getMallaInicial();
        this.setPreferredSize(new Dimension(malla.dimensionX, malla.dimensionY));
        try {
            image = mallaToImage();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage mallaToImage() throws InterruptedException, ExecutionException {
        BufferedImage image = new BufferedImage(malla.getDimensionX(), malla.getDimensionY(), BufferedImage.TYPE_4BYTE_ABGR);
        for (FragmentoMalla fm : malla.malla) {
            fiCompletionSrv.submit(new CalculadorFragmentoImagen(fm));
        }
        for (int i = 0; i < malla.malla.size(); i++) {
            FragmentoImagen fi = fiCompletionSrv.take().get();
            int ancho = fi.getHasta() - fi.getDesde();
            image.setRGB(fi.getDesde(), 0, ancho, fi.getAlto(), fi.getRgb(), 0, ancho);
        }
        return image;
    }

    private int calcularRGB(double valor) {
        int ancho = colores.getWidth() - 1;

        int posicion = (int) (valor / 3000.0 * ancho);
        posicion = (posicion < 0) ? 0 : posicion;
        posicion = (posicion > ancho - 1) ? ancho - 1 : posicion;

        int color = colores.getRGB(posicion, 0);
        return color;
    }

    @Override
    public void update(Observable o, Object arg) {
        malla = (Malla) arg;
        try {
            image = mallaToImage();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        this.repaint();

    }

    @Override
    public void paint(Graphics g) {

        Graphics2D gr = (Graphics2D) g;
        super.paint(gr);
        gr.drawImage(image, null, 0, 0);
        this.setOpaque(true);

    }

    private class CalculadorFragmentoImagen implements Callable<FragmentoImagen> {

        FragmentoMalla fm;

        public CalculadorFragmentoImagen(FragmentoMalla fm) {
            this.fm = fm;
        }

        @Override
        public FragmentoImagen call() throws Exception {
            FragmentoImagen fi = new FragmentoImagen(fm.alto, fm.desde, fm.hasta);
            int index = 0;
            for (int y = 0; y < fm.alto; y++) {
                for (int x = 0; x < fm.hasta - fm.desde; x++) {
                    fi.getRgb()[index] = calcularRGB(fm.fragmento[x][y]);
                    index++;
                }
            }
            return fi;
        }
    }
}
