package com.maggen.WiMouseServer;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;


/**
 * This class holds the robot object that is in charge of moving the pointer
 * @author Jose
 *
 */
public class MouseMover {

	private Robot robot;

	private PointerInfo inf;
	private Point pointerLocation;

	private int xFromAndroid;
	private int yFromAndroid;

	private int prevX;
	private int prevY;

	private int pointerX;
	private int pointerY;

	private int newX;
	private int newY;

	private String[] split;

	public MouseMover() throws AWTException
	{
		this.robot = new Robot();
	}

	/**
	 * Moves the pointer according to the movement of the finger in the android device.
	 * @param movement Receives a string in this format
	 *  (x-coordinate, y-coordinate, previous x-coordinate, previous y-coordinate)-on the android device  
	 */
	public void updatePointer(String movement)
	{
		this.inf  = MouseInfo.getPointerInfo();
		pointerLocation = inf.getLocation();

		if(movement.trim().equals("left"))
		{
			System.out.println("Left");
			robot.mousePress(InputEvent.BUTTON1_MASK);
		}
		else if (movement.trim().equals("right"))
		{
			System.out.println("right");
		}
		else{
			split = movement.split(",");

			xFromAndroid = Integer.parseInt(split[0].trim()) ;
			yFromAndroid = Integer.parseInt(split[1].trim()) ;
			prevX = Integer.parseInt(split[2].trim());
			prevY = Integer.parseInt(split[3].trim());

			pointerX = (int) pointerLocation.getX();
			pointerY = (int) pointerLocation.getY();

			//	System.out.println("X: "+xFromAndroid +", Y: "+yFromAndroid+" PrevX: "+prevX+" PrevY: "+prevY);

			newX = xFromAndroid-prevX;
			newY = yFromAndroid-prevY;
			robot.mouseMove(pointerX+newX,pointerY+newY);
		}
	}

}
