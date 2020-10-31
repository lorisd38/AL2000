package Vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class Carousel extends JPanel {

	public Carousel(JPanel window) {
		int nbFilm = 100;
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		this.setBackground(new Color(153, 126, 145));
		this.setPreferredSize(new Dimension(nbFilm*60, 50));
		for (int i = 0; i < nbFilm; i++) {
			JButton btn = new JButton(Integer.toString(i));
			btn.setPreferredSize(new Dimension(50, 50));
			this.add(btn);
		}
		JScrollPane jsp = new JScrollPane(this);
		jsp.setPreferredSize(new Dimension(980, 70));
		window.add(jsp);
	}
}
