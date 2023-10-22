package clases;

import java.awt.*;

public class bloques {
	public int x;
    public int y;
    public int ancho;
    public int alto;

    public bloques(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public void dibujar(Graphics2D g) {
        g.setColor(Color.BLUE); // Puedes cambiar el color del bloque según tus preferencias
        g.fillRect(x, y, ancho, alto);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, ancho, alto);
    }
}
