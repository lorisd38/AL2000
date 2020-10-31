package Vue;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

@SuppressWarnings("serial")
public class InterfaceGraphique extends JFrame {
	
	public InterfaceGraphique() {
		super("AL2000");
		this.setVisible(true);
		this.setPreferredSize(new Dimension(1366, 768));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(new NimbusLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.add(new MachinePhysique());
		
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	
	public static void main(String[] argv) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() { 
				new InterfaceGraphique(); 
			}
		});
	}
}
