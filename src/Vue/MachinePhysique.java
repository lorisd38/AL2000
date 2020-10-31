package Vue;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MachinePhysique extends JPanel {
	
	public MachinePhysique() {
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(44, 155, 188));
		// 1366x768 Taille de la machine visible sur la fenetre grapqhique
		// 1024x576 Taille de l'écran de la machine (16:9)
		this.add(Box.createVerticalStrut(96), BorderLayout.NORTH);
		this.add(Box.createHorizontalStrut(50), BorderLayout.EAST);
		this.add(Box.createVerticalStrut(96), BorderLayout.SOUTH);
		this.add(new EspaceCarte(), BorderLayout.WEST);
		new MachineEcran(this);
	}
}
