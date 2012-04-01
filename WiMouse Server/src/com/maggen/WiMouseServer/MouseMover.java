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
	}
	
	/**
	 * Moves the pointer according to the movement in the android device.
	 * @param movement Receives a string in this format (x-coordinate, y-coordinate)-on the android device  
	 */
	public void updatePointer(String movement)
	{
		this.inf  = MouseInfo.getPointerInfo();
		Point p = inf.getLocation();
		
		String[] split = movement.split(",");
		
		int xFromAndroid = Integer.parseInt(split[0].trim());
		int yFromAndroid = Integer.parseInt(split[1].trim());
		
		int x = (int) p.getX();
		int y = (int) p.getY();
		
		robot.mouseMove(x+xFromAndroid, y+yFromAndroid);
	}
	
}
