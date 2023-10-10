package clases;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class TableroJuego extends JPanel {
	
    private Pelota pelota = new Pelota(0, 0);
    private Raqueta r1 = new Raqueta(20, 200); // Corrige el orden de ancho y alto

    private Image fondo;
    private JLabel vidaLabel;


    public TableroJuego() {
        setBackground(Color.BLACK);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        ImageIcon icon = new ImageIcon("E:\\5TO 2DA\\LPOO\\10-09-2023\\java\\Imagenes\\corazon.png");
        Image image = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        lblNewLabel.setIcon(new ImageIcon(image));
        lblNewLabel.setBounds(700, 5, 40, 40);
        vidaLabel = new JLabel();
        vidaLabel.setBounds(750, 5, 40, 40);
        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                inicializarPelota(getWidth(), getHeight());
            }
        });
        
        add(vidaLabel);
        add(lblNewLabel);
        
        
    }
    
    private void inicializarPelota(int maxX, int maxY) {
        Random random = new Random();
        int x = random.nextInt(maxX - Pelota.DIAMETRO);
        int y = random.nextInt(maxY - Pelota.DIAMETRO);
        pelota = new Pelota(x, y);
    }
    
    public void actualizarVidaLabel() {
        vidaLabel.setText(String.valueOf(pelota.getVidaCount()));
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        dibujar(g2);

        actualizar();
    }

    public void dibujar(Graphics2D g) {
        g.fill(pelota.getPelota());
        g.fill(r1.getRaqueta()); // Dibuja la raqueta
    }

    public void actualizar() {
        pelota.mover(getWidth(), getHeight(), colision(r1.getRaqueta()));
        r1.mover1(getBounds());
        actualizarVidaLabel(); // Actualizar el JLabel de vida_count
    }


    public void iterarJuego() {
        while (true) {
            repaint();
            try {
                Thread.sleep(6);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    

    private boolean colision(Rectangle2D r) {
        boolean intersecta = pelota.getPelota().intersects(r);
       // System.out.println("Colisión con raqueta: " + intersecta);
        return intersecta;
    }


}
