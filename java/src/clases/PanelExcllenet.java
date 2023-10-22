package clases;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension; // Importa la clase Dimension para establecer el tamaño

public class PanelExcllenet extends JPanel {

    private JButton boton;

    public PanelExcllenet() {
    	
    	System.out.println("Hola Mundo");
        boton = new JButton("Haz clic");
        add(boton);

        setPreferredSize(new Dimension(300, 200));
        boton.setPreferredSize(new Dimension(100, 50));
        
        setVisible(true);
    }
    
    public void panelTrue() {
    	this.setVisible(true);
    }
    public void panelFalse() {
    	this.setVisible(false);
    }
}
