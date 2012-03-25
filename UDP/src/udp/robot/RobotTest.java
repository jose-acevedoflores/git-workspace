package udp.robot;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Timer;

public class RobotTest {
	
	private Robot rb;
	private MouseMover t;
	public RobotTest() throws AWTException{
		 this.rb = new Robot();
		 this.t = new MouseMover();
	}
	
	public ActionListener getMouseMover()
	{
		return this.t;
	}
	
	private class MouseMover implements ActionListener{

		//private Robot rb;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			PointerInfo inf = MouseInfo.getPointerInfo();
			
			Point p = inf.getLocation();
			
			int x = (int) p.getX();
			int y = (int) p.getY();
			
			rb.mouseMove(x + 1, y + 1);
			
		}
		
	}


	/**
	 * @param args
	 * @throws AWTException 
	 */
	public static void main(String[] args) throws AWTException {

		Scanner in = new Scanner(System.in);
		String inp = "y";
		RobotTest rt = new RobotTest();
	
		Timer ti = new Timer(500, rt.getMouseMover());
		ti.start();
		while(!inp.equalsIgnoreCase("End"))
		{
			System.out.print("Type something: ");
			inp=in.nextLine();
			
			System.out.println("Dat robot movement");
			
		}
		ti.stop();
	}
	
}
