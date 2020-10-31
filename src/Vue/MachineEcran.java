package Vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class MachineEcran extends JPanel {

	public MachineEcran(JPanel window) {
		int nbCarousel = 5;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(980, nbCarousel*150));
		this.setBackground(new Color(237, 229, 214));
		Border loweredBevel = BorderFactory.createLoweredBevelBorder();
		Border raisedBevel = BorderFactory.createRaisedBevelBorder();
		Border compound = BorderFactory.createCompoundBorder(raisedBevel, loweredBevel);
		this.setBorder(compound);
		
		this.add(Box.createVerticalStrut(nbCarousel*10));
		for (int i = 0; i < nbCarousel; i++) {
			new Carousel(this);
			this.add(Box.createVerticalStrut(nbCarousel*10));
		}
		
		
		JScrollPane jsp = new JScrollPane(this);
		jsp.setPreferredSize(new Dimension(980, 500));
		window.add(jsp);
	}
}
