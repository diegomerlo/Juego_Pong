package clases;

import java.util.logging.Logger;

public class Hilo extends Thread {
	
	TableroJuego lamina;
	
	public Hilo(TableroJuego lamina) {
		this.lamina=lamina;
	}
	
	public void run() {
		
		while(true) {
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			lamina.repaint();
			
		}
	}
	
	

}
