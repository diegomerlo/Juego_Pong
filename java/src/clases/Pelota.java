package clases;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pelota {

    private int x;
    private int y;
    private int dx = 1;
    private int dy = 1;
    private int vida_count = 3;
    private int score = 0;
    
    private List<bloques> bloques = new ArrayList<>();
    
    public static final int DIAMETRO = 15; // Diámetro de la pelota

    public Pelota(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void agregarBloque(bloques bloque) {
        bloques.add(bloque);
    }

    public List<bloques> getBloques() {
        return bloques;
    }

    public Ellipse2D getPelota() {
        // Utiliza un Ellipse2D.Double para representar la pelota como un círculo
        return new Ellipse2D.Double(x, y, DIAMETRO, DIAMETRO);
    }
    
    public int getX() {
    	
    	return x;
    }
    public int getY() {
    	return y;
    }

    public int getVidaCount() {
        return vida_count;
    }

    public void setVidaCount(int vida_count) {
        this.vida_count = vida_count;
    }

    public int getScore() {
    	return score;
    }

    public void setScore(int score) {
    	this.score = score;
    }
    
    public void moreScore() {
    	score+=100;
    }
    
    public void mover(int maxX, int maxY, boolean colision1) {
        x += dx;
        y += dy;

        if (x < 0) {
            vida_count--;
            Hilo.tiempo_modificador(true);
            x = 0;
            dx = -dx;
        } else if (x > maxX - DIAMETRO) {

            x = maxX - DIAMETRO;
            dx = -dx;
        }
        
        if(vida_count==0||score>=200) { //�a barra toma la velocidad de la pleota MALLL
        
        	x = 400;
        	y = 100;
        	
        	
        	
        }
       /* if(score>=100) {
        	TableroJuego tableroJuego = new TableroJuego();
			tableroJuego.visibilidadfalsa();
        }*/

        if (colision1) {
            dx = -dx;
            x = 30+Raqueta.valorAncho();
            score+=100;
        }
        
        if(colision1) {
        	Hilo.tiempo_modificador(colision1);
        }

        // Restringe el movimiento para evitar que la pelota se salga del área del juego
        if (y < 0) {
            dy = -dy;
            y = 0;
        } else if (y > maxY - DIAMETRO) {
            dy = -dy;
            y = maxY - DIAMETRO;
        }
    }
    
    public void verificarColisionBloques() {
           
    	dx = -dx;
            
    }
    
    
}
