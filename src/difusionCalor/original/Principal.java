package difusionCalor.original;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Principal extends JFrame implements ActionListener {
	final int DIMENSIONX = 100;
	final int DIMENSIONY = 100;
	final double TEMPERATURAINICIAL = 10.0;
	final double TEMPERATURAFOCOCALOR = 2000.0;
	final double coeficienteDifusionCalorX = 0.2;
	final double coeficienteDifusionCalorY = 0.2;
	final int NUMCICLOS = 100000;
	
	Malla mallaInicial;
	List<PuntoCalor> focosCalor;
	DifusorCalor difusor;
	
	public Principal(){
		super("Difusion calor en malla");
		mallaInicial = new Malla (DIMENSIONX,DIMENSIONY);
		mallaInicial.inicializarMalla(TEMPERATURAINICIAL);
		this.definirFocosCalor();
		
		this.setLocation(100,10);
		this.setSize(DIMENSIONX,DIMENSIONY);
		this.setContentPane(crearPanelVentana());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private Container crearPanelVentana() {
		JPanel panel = new JPanel (new BorderLayout(0,10));
		JButton iniciar = new JButton ("Start");
		iniciar.addActionListener(this);
		MiPanel miPanel = new MiPanel( difusor);
		panel.add(miPanel,BorderLayout.CENTER);
		panel.add(iniciar,BorderLayout.SOUTH);
	
		return panel;
	}
	public void definirFocosCalor(){
		focosCalor = new ArrayList<>();
		for (int i = -10; i<10; i++){
			for (int j = -10; j<10; j++){
				focosCalor.add(new PuntoCalor((DIMENSIONX/2)+i,(DIMENSIONY/2)+j,TEMPERATURAFOCOCALOR));
				
			}
		}
		
		difusor = new DifusorCalor (coeficienteDifusionCalorX, coeficienteDifusionCalorY, mallaInicial, focosCalor);
	}
	
	private void visualizarMalla(Malla nuevaMalla) {
		for (int i = 0; i<DIMENSIONX; i++){
			for (int j = 0; j < DIMENSIONY; j++){
				System.out.print(String.format("%5.2f", nuevaMalla.getValor(i, j))+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	public static void main(String[] args) {
		Principal programa = new Principal();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Thread hilo = new Thread(new Runnable(){

			@Override
			public void run() {
				long inicio = System.currentTimeMillis();
				for (int i = 0; i<NUMCICLOS; i++){
					System.out.println(i);
					difusor.difundir();
					difusor.cambiar();
				}
				long fin = System.currentTimeMillis();
				System.out.println("Tiempo total: "+ (fin-inicio)+"ms");
			}
			
		});
		hilo.start();
		
	}

}
