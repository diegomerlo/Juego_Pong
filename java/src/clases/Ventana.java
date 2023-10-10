package clases;
import javax.swing.JFrame;


public class Ventana extends JFrame {
	
	private final int ANCHO=800, ALTO=500;
	private TableroJuego lamina;
	private Hilo hilo;
	
	public Ventana () {
		
		setTitle("Pong");
	    setSize(ANCHO, ALTO);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    lamina = new TableroJuego();
	    add(lamina);
	    EventoTeclado eventoTeclado = new EventoTeclado();
	    addKeyListener(eventoTeclado);

	    setUndecorated(true);

	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    hilo = new Hilo(lamina);
	    hilo.start();

		
	}

}
