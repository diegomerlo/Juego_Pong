/*package clases;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotones extends JPanel {
    private JButton terminarButton;
    private JButton reiniciarButton;

    public PanelBotones() {
    	setLayout(new FlowLayout());
	    setOpaque(false); 
	    setVisible(false); 

        terminarButton = new JButton("Terminar Juego");
        reiniciarButton = new JButton("Reiniciar Juego");

        add(terminarButton);
        add(reiniciarButton);

        // Agregar ActionListener para el botón de terminar
        terminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Terminar la aplicación cuando se presiona el botón de terminar
            }
        });

        // Agregar ActionListener para el botón de reiniciar
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Agrega aquí la lógica para reiniciar el juego (por ejemplo, reinicializar posiciones y vidas)
                // Puedes llamar a un método en la clase principal del juego para hacer esto.
            }
        });
    }
}*/
