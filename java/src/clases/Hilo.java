package clases;

import java.util.logging.Logger;

public class Hilo extends Thread {
	
	TableroJuego lamina;
	
	public static double tiempo;
	
	public Hilo(TableroJuego lamina) {
		this.lamina=lamina;
	}
	
	public void run() {
		
		while(true) {
			
			try {
				Thread.sleep((long) tiempo);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			lamina.repaint();
			
		}
	}
	
	public static boolean tiempo_modificador(boolean tempo) {
		
		tiempo = tiempo;
		
		return tempo;
		
	}
	
	

}
