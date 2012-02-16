package complexNumber.phasor;
import complexNumber.ComplexNumber;


public class Phasor {
	
	private ComplexNumber num;
	private double magnitude;
	private double angle;
	
	public Phasor(double magnitude, double angle)
	{
		this.magnitude = magnitude;
		this.angle = angle;
		
		num = new ComplexNumber(this.magnitude * Math.cos(Math.toRadians(angle)), this.magnitude * Math.sin(Math.toRadians(angle)));
	}
	
	public Phasor(ComplexNumber n)
	{
		num = n;
		
		this.magnitude = Math.pow(n.getRealPart().getNum(), 2) + Math.pow(n.getImaginaryPart().getNum(), 2);
		this.magnitude = Math.sqrt(magnitude);
		
		if(n.getRealPart().getNum() > 0 && n.getImaginaryPart().getNum() > 0)
			angle = Math.toDegrees(Math.atan(num.getImaginaryPart().getNum() / num.getRealPart().getNum()));
		
		else if(n.getRealPart().getNum() > 0 && n.getImaginaryPart().getNum() < 0)
			angle =  Math.toDegrees(Math.atan(num.getImaginaryPart().getNum() / num.getRealPart().getNum()));
		
		else if(n.getRealPart().getNum() < 0 && n.getImaginaryPart().getNum() > 0)
			angle = 180 +Math.toDegrees(Math.atan(num.getImaginaryPart().getNum() / num.getRealPart().getNum()));
		
		else if(n.getRealPart().getNum() < 0 && n.getImaginaryPart().getNum() < 0)
			angle = 180 + Math.toDegrees(Math.atan(num.getImaginaryPart().getNum() / num.getRealPart().getNum()));
	}
	
	public Phasor multiply(Phasor p)
	{
		return new Phasor(num.multiply(p.getCuadraticForm()));
	}
	
	public Phasor add(Phasor p)
	{
		return new Phasor(num.add(p.getCuadraticForm()));
	}
	
	public Phasor substract(Phasor p)
	{
		return new Phasor(num.substract(p.getCuadraticForm()));
	}
	
	public Phasor divide(Phasor p)
	{
		return new Phasor(num.divide(p.getCuadraticForm()));
	}
	
	public Phasor getAdmitance()
	{
		ComplexNumber n= new ComplexNumber(Math.pow(num.norm().getNum(), 2),0);	
		return new Phasor(this.getCuadraticForm().conjugate().divide(n));
	}
	
	public ComplexNumber getCuadraticForm()
	{
		return num;
	}
	
	public String toString()
	{
		return magnitude+" ANG "+angle;
	}

}
