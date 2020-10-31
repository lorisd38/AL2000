package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EmplacementCarte extends JPanel {

	public EmplacementCarte() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		this.setBackground(new Color(150, 150, 150));
		this.setPreferredSize(new Dimension(220, 50));
		JPanel trait = new JPanel();
		trait.setBackground(Color.BLACK);
		trait.setPreferredSize(new Dimension(200, 5));
		this.add(trait);
	}
}
