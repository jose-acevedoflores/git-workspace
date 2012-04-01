package com.maggen.WiMouseServer;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;

public class MouseMover {

	private Robot robot;
	private PointerInfo inf;
	
	public MouseMover() throws AWTException
	{
		this.robot = new Robot();
		this.inf  = MouseInfo.getPointerInfo();
	}
	
	/**
	 * Moves the pointer according to the movement in the android device.
	 * @param movement Receives a string in this format (x-coordinate, y-coordinate)-on the android device  
	 */
	public void updatePointer(String movement)
	{
		Point p = inf.getLocation();
		
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		robot.mouseMove(0, 0);
	}
	
}
