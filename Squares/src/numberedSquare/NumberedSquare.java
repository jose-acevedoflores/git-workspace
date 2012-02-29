package numberedSquare;

import java.awt.Rectangle;

public class NumberedSquare extends Rectangle{

	private static final long serialVersionUID = 1L;
	private int number;
	
	
	public NumberedSquare(int number)
	{
		super(80,80);
		this.number = number;
	}
	
	public int getNumber()
	{
		return number;
	}
	
}
