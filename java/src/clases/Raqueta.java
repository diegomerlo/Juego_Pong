package clases;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class Raqueta {

    private int x, y;
    private static final int ANCHO = 10, ALTO = 60;

    public Raqueta(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static int valorAncho() {
    	
    	return ANCHO;
    }
    public Rectangle2D getRaqueta() {
        return new Rectangle2D.Double(x, y, ANCHO, ALTO);
    }
    
    public void mover1(Rectangle limites) {
    	
    	if(EventoTeclado.w&&y>limites.getMinY()) {
    		y--;
    	}
    	if(EventoTeclado.s&&y<limites.getMaxY()-ALTO) {
    		y++;
    	}
    	
    }
}
