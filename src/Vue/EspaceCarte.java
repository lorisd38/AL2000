package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EspaceCarte extends JPanel {
	
	public EspaceCarte() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 230));
		this.setBackground(new Color(44, 155, 188));
		this.setPreferredSize(new Dimension(292, 0));
		
		this.add(new EmplacementCarte());
	}
}
