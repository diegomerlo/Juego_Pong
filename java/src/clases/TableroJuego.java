package clases;

import java.util.Random;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;


public class TableroJuego extends JPanel {
	
    private Pelota pelota = new Pelota(0, 0);
    private Level level = new Level();
    private List<bloques> bloques;
    private Raqueta r1 = new Raqueta(20, 200);
    
    private static CountDownLatch latch = new CountDownLatch(1);
    private Image fondoimagen;
    private JLabel vidaLabel;
    private JLabel score;
    private JLabel score_text;
    private JLabel[] corazones = new JLabel[3];
    //private PanelExcllenet panel2;
    private JLabel puntos;
    private JLabel suma_puntos;
    private JLabel excellent;
    private JLabel game_over;
    private static JButton siguiente_level;
    private static JButton anterior_level;
    private boolean valor;
    private Ventana ventana;
    
    private int numFilas; 
    private int numColumnas;
    private int vidas;
    
    private int bloqueAncho = 20;
    private int bloqueAlto = 50; 
    private int espacioEntreBloques = 10; 


    public TableroJuego() {
    	
    	String[] opciones = {"Nivel 1", "Nivel 2", "Nivel 3"};
        int dificultad = JOptionPane.showOptionDialog(
            null,
            "Seleccione la dificultad:",
            "Dificultad",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            opciones,
            opciones[0]
        );
        
        if(dificultad==0) {
        	numFilas=1;
        	numColumnas=1;
        	vidas=3;
        	Hilo.tiempo=2;
        }
        if(dificultad==1) {
        	numFilas=2;
        	numColumnas=1;
        	vidas=2;
        	Hilo.tiempo=1.8;
        }
        if(dificultad==2) {
        	numFilas=3;
        	numColumnas=1;
        	vidas=3;
        	Hilo.tiempo=1.3;
        }
        
        setBackground(Color.BLACK);
        setLayout(null);
        
        ImageIcon icon = new ImageIcon("Imagenes/corazon2.png");
        Image image = icon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        
        int sum = 650;
        
        for (int i = 0; i < vidas; i++) {
        	
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
        
        suma_puntos= new JLabel();
        ImageIcon iconSuma_Puntos = new ImageIcon("Imagenes/100.png");
        Image imageSuma_Puntos = iconSuma_Puntos.getImage().getScaledInstance(80, 30, Image.SCALE_SMOOTH);
        suma_puntos.setIcon(new ImageIcon(imageSuma_Puntos));
        suma_puntos.setBounds(175,50,80,30);
        suma_puntos.setVisible(false);
        
        /*siguiente_level = new JButton();
        ImageIcon iconNextLevel = new ImageIcon("Levels/next.png");
        Image imageNextLevel = iconNextLevel.getImage().getScaledInstance(100, 86, Image.SCALE_SMOOTH);
        siguiente_level.setIcon(new ImageIcon(imageNextLevel));
        siguiente_level.setBounds(500,200,100,86);
        siguiente_level.setVisible(false);
        
        siguiente_level.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    //System.out.println("Hola");
                    //change_valor_false();
                	//Level.reiniciar_panel();
                	//excellent.setVisible(false);
                	//JOptionPane.showMessageDialog(null, "Este es un mensaje de información.", "Información", JOptionPane.INFORMATION_MESSAGE);
                	pelota.moreScore();
                	anterior_level.setVisible(false);
                });
            }
        });
        //anterior_level
        anterior_level = new JButton();
        ImageIcon iconanterior_level= new ImageIcon("Levels/next.png");
        Image imageanterior_level = iconanterior_level.getImage().getScaledInstance(100, 86, Image.SCALE_SMOOTH);
        anterior_level.setIcon(new ImageIcon(imageanterior_level));
        anterior_level.setBounds(250,200,100,86);
        anterior_level.setVisible(false);
        
        anterior_level.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    //System.out.println("Hola");
                    //change_valor_false();
                	//Level.reiniciar_panel();
                	//excellent.setVisible(true);
                	//JOptionPane.showMessageDialog(null, "Este es un mensaje de información.", "Información", JOptionPane.INFORMATION_MESSAGE);
                	pelota.moreScore();
                });
            }
        });*/
        
        
        
        //add(siguiente_level);
        //add(anterior_level);
        add(puntos);
        add(suma_puntos);
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
        
        Random random = new Random();
        int anchoPantalla = random.nextInt(800); // Genera una posición x aleatoria entre 0 y 799
        int altoPantalla = random.nextInt(400);
        inicializarBloques(anchoPantalla,altoPantalla);
        
        
        
        
    }
    
    
    
    private void inicializarBloques(int anchoPantalla, int altoPantalla) {
        bloques = new ArrayList<>();
        Random random = new Random();

        for (int fila = 0; fila < numFilas; fila++) {
            for (int columna = 0; columna < numColumnas; columna++) {
                int x = random.nextInt(anchoPantalla - bloqueAncho); // Generar posición x aleatoria
                int y = random.nextInt(altoPantalla - bloqueAlto);

                bloques bloque = new bloques(x, y, bloqueAncho, bloqueAlto);
                pelota.agregarBloque(bloque);
                bloques.add(bloque);
            }
        }
    }

    
     public void visibilidadfalsa() {
    	
    	    	if(pelota.getVidaCount()==0) {
    	    		game_over.setVisible(true);
    	    		puntos.setVisible(true);
    	    	}
    	    	if(pelota.getScore()>=200) {
    	    		excellent.setVisible(true);
    	    		puntos.setVisible(true);
    	    	}
    }
     
    /*
     public void visibilidadTrue() {
		
    	this.setVisible(true);
    }
    

     
    
    public static void change_valor() {
        //excellent.setVisible(true);
        siguiente_level.setVisible(true);
        anterior_level.setVisible(true);

    }

    public static void change_valor_false() {
        SwingUtilities.invokeLater(() -> {
            //excellent.setVisible(false);
            siguiente_level.setVisible(false);
        });
    }*/

    
    
    public void timer() {
        int tiempoVisible = 0;
        Timer time = new Timer(tiempoVisible, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suma_puntos.setVisible(true);
                ((Timer) e.getSource()).stop();
            }
        });

        time.start();
        
        int tiempoNoVisible = 700;
        Timer disappearTimer = new Timer(tiempoNoVisible, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                suma_puntos.setVisible(false);
                ((Timer) e.getSource()).stop();
            }
        });

        disappearTimer.start();
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
        
        for (bloques bloque : bloques) {
        	g.fillRect(bloque.x, bloque.y, bloque.ancho, bloque.alto);
        }
        
    }
    
    public void textSet() {
    	puntos.setText("Puntos Totales: "+String.valueOf(pelota.getScore()));
    }
    
    /*public void show() {
    	if(pelota.getVidaCount()==0) {
    		game_over.setVisible(true);
    		
    		puntos.setVisible(true);
    	}
    	if(pelota.getScore()>=100) {
    		timer();
    		excellent.setVisible(true);
    		siguiente_level.setVisible(true);
    		puntos.setVisible(true);
    	}
    }*/
    
    /*public void change(boolean valor) {
    	excellent.setVisible(valor);
    }*/

    public void actualizar() {
        pelota.mover(getWidth(), getHeight(), colision(r1.getRaqueta()));
        r1.mover1(getBounds());
        actualizarVidaLabel();
        actualizarScore(); // Llama al método para actualizar el puntaje en score_text
        //show();
        textSet();
        //change(true);
        colisionBloques();
        visibilidadfalsa();
        //timer();
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
        if (intersecta) {
            suma_puntos.setBounds(pelota.getX() + 5, pelota.getY(), 80, 30);
            timer();
        }
        
        
        return intersecta;
    }
    
    private void colisionBloques() {
        Iterator<bloques> iterator = bloques.iterator();
        while (iterator.hasNext()) {
            bloques bloque = iterator.next();
            if (pelota.getPelota().intersects(bloque.getBounds())) {
                iterator.remove();
                pelota.verificarColisionBloques();
            }
        }
    }

    
  
}
