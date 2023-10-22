package clases;
import javax.swing.JFrame;


public class Ventana extends JFrame {
	
	private final int ANCHO=800, ALTO=500;
	private TableroJuego lamina;
	//private PanelExcllenet excellent;
	private Pelota pelota;
	private Hilo hilo;
	
	public Ventana () {
		
		pelota = new Pelota(0,0);
		setTitle("Pong");
	    setSize(ANCHO, ALTO);
	    setLocationRelativeTo(null);
	    setResizable(false);
	    
	    /*excellent = new PanelExcllenet();
        excellent.panelFalse();
        add(excellent);*/
	    
        lamina = new TableroJuego();
        lamina.setVisible(true);
        add(lamina);
	    
	    
	    
	    EventoTeclado eventoTeclado = new EventoTeclado();
	    addKeyListener(eventoTeclado);

	    setUndecorated(true);

	    setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    hilo = new Hilo(lamina);
	    hilo.start();
	    
	    
		
	}
	
	/*public void actualizar() {
		this.excellent.setVisible(true);
	}*/


}
