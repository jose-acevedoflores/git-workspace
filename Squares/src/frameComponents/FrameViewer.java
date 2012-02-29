package frameComponents;

import javax.swing.JFrame;


public class FrameViewer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JFrame f = new JFrame("Squares");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500,500);
		f.setLocationRelativeTo(null);
		
		FrameComponent fc = new FrameComponent(5);
		
		f.add(fc);
		f.setVisible(true);
	}

}
