package complexNumber;

public class RealNumber {
	
	private double number;
	
	public RealNumber()
	{
		number = 0.0;
	}
	
	public RealNumber(double num)
	{
		number = num;
	}
	
	public RealNumber(RealNumber num)
	{
		number = num.getNum();
	}
	
	
	public RealNumber absoluteValue()
	{
		RealNumber r = new RealNumber(Math.abs(number));
		
		return r;
	}
	
	public RealNumber add(RealNumber R)
	{
		RealNumber r = new RealNumber(number + R.getNum());
		return r;
	}
	
	public RealNumber substract(RealNumber R)
	{
		RealNumber r = new RealNumber(number - R.getNum());
		return r;
	}
	
	public RealNumber multiply(RealNumber R)
	{
		RealNumber r = new RealNumber(number*R.getNum());
		return r;
	}
	
	public RealNumber divide(RealNumber R)
	{
		RealNumber r = new RealNumber(number/R.getNum());
		return r;
	}
	
	public RealNumber sqrt()
	{
		RealNumber r = new RealNumber(Math.sqrt(number));
		return r;
	}
	
	public RealNumber sqr()
	{
		RealNumber r = new RealNumber(number*number);
		return r;	
	}
	
	public boolean equals(RealNumber R)
	{
		if(Math.abs(R.getNum()- number ) < 1e-8)
		{
			return true;
		}
		else
			return false;
	}
	
	public int compareTo(RealNumber R)
	{
		if(Math.abs(R.getNum()- number ) < 1e-8)
		{
			return 0;
		}
		
		else if(number > R.getNum())
		{
			return 1;
		}
		
		else
			return -1;
		
	}
	
	public String toString()
	{
		return Double.toString(number);
	}
	
	public double getNum()
	{
		return number;
	}

}
