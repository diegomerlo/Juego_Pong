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
import java.awt.Font;


public class TableroJuego extends JPanel {
	
    private Pelota pelota = new Pelota(0, 0);
    private Raqueta r1 = new Raqueta(20, 200); // Corrige el orden de ancho y alto

    private Image fondoimagen;
    private JLabel vidaLabel;
    private JLabel score;
    private JLabel score_text;
    private JLabel[] corazones = new JLabel[3];
    private JLabel game_over;
    private JLabel excellent;
    private JLabel puntos;


    public TableroJuego() {
        setBackground(Color.BLACK);
        setLayout(null);
        
        ImageIcon icon = new ImageIcon("Imagenes/corazon2.png");
        Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        int sum = 650;
        
        for (int i = 0; i < 3; i++) {
        	
        	corazones[i]= new JLabel();
        	corazones[i].setIcon(new ImageIcon(image));
        	corazones[i].setBounds(sum, 5, 30, 30);
        	
        	add(corazones[i]);
        	
        	sum+= 40;
        }
        
        score = new JLabel();
        ImageIcon iconScore = new ImageIcon("Imagenes/Your_Score_Arcade.png");
        Image imageScore = iconScore.getImage().getScaledInstance(80, 20, Image.SCALE_SMOOTH);
        score.setIcon(new ImageIcon(imageScore));
        score.setBounds(650, 35, 80, 20);
        
        score_text = new JLabel();
        score_text.setForeground(Color.WHITE);
        score_text.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        score_text.setBounds(740,35,80,20);
        
        game_over = new JLabel();
        ImageIcon iconGame_Over = new ImageIcon("Imagenes/Game-Over.png");
        Image imageGame_Over = iconGame_Over.getImage().getScaledInstance(450, 125, Image.SCALE_SMOOTH);
        game_over.setIcon(new ImageIcon(imageGame_Over));
        game_over.setBounds(175,50,450,125);
        game_over.setVisible(false);
        
        excellent = new JLabel();
        ImageIcon iconExcellent = new ImageIcon("Imagenes/EXCELLENT.png");
        Image imageExcellent = iconExcellent.getImage().getScaledInstance(450, 125, Image.SCALE_SMOOTH);
        excellent.setIcon(new ImageIcon(imageExcellent));
        excellent.setBounds(175,50,450,125);
        excellent.setVisible(false);
        
        puntos = new JLabel();
        puntos.setForeground(Color.WHITE);
        puntos.setFont(new Font("Berlin Sans FB", Font.PLAIN, 25));
        puntos.setBounds(320, 160, 450, 50);
        puntos.setVisible(false);
        
        
        add(puntos);
        add(excellent);
        add(game_over);
        add(score_text);
        add(score);
        
        
        vidaLabel = new JLabel();
        vidaLabel.setForeground(new Color(255, 255, 255));
        vidaLabel.setBounds(750, 5, 40, 40);
        
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                inicializarPelota(getWidth(), getHeight());
            }
        });
        
        add(vidaLabel);
       
        
        ImageIcon img = new ImageIcon("Imagenes/fondo.jpg");
        Image Scaledimg = img.getImage().getScaledInstance(700, 600, Image.SCALE_SMOOTH);
        ImageIcon ScaledimgToIcon = new ImageIcon(Scaledimg);
        fondoimagen = ScaledimgToIcon.getImage();
        
        
    }
    
    private void inicializarPelota(int maxX, int maxY) {
        Random random = new Random();
        int x = random.nextInt(maxX - Pelota.DIAMETRO);
        int y = random.nextInt(maxY - Pelota.DIAMETRO);
        pelota = new Pelota(x, y);
    }
    
    public void actualizarVidaLabel() {
        
        if(pelota.getVidaCount()<3) {
        	
        	corazones[pelota.getVidaCount()].setVisible(false);
        	
        }
        
    }
    
    public void actualizarScore() {
        score_text.setText(String.valueOf(pelota.getScore()));
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.drawImage(fondoimagen, 0, 0, getWidth(),getHeight(),this);
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.WHITE);
        dibujar(g2);

        actualizar();
    }

    public void dibujar(Graphics2D g) {
        g.fill(pelota.getPelota());
        g.fill(r1.getRaqueta());
    }
    public void textSet() {
    	puntos.setText("Puntos Totales: "+String.valueOf(pelota.getScore()));
    }
    
    public void show() {
    	if(pelota.getVidaCount()==0) {
    		game_over.setVisible(true);
    		
    		puntos.setVisible(true);
    	}
    	if(pelota.getScore()>=200) {
    		excellent.setVisible(true);
    		puntos.setVisible(true);
    	}
    }

    public void actualizar() {
        pelota.mover(getWidth(), getHeight(), colision(r1.getRaqueta()));
        r1.mover1(getBounds());
        actualizarVidaLabel();
        actualizarScore(); // Llama al método para actualizar el puntaje en score_text
        show();
        textSet();
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
	       if(intersecta==true) {
	    	   //System.out.println("Colisión con raqueta: " + intersecta);
	       }
        return intersecta;
    }


}
