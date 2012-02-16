package complexNumber;


public class ComplexNumber {
	
	RealNumber a;
	RealNumber bi;
	
	public ComplexNumber()
	{
		a = new RealNumber();
		bi = new RealNumber();
	}
	
	
	public ComplexNumber(double realPart, double imaginaryPart)
	{
		a = new RealNumber(realPart);
		bi = new RealNumber(imaginaryPart);
	}
	
	public ComplexNumber(RealNumber realPart, RealNumber imaginaryPart)
	{
		a = new RealNumber(realPart);
		bi = new RealNumber(imaginaryPart);
	}
	
	
	public ComplexNumber add(ComplexNumber C)
	{
		ComplexNumber co = new ComplexNumber(
				C.getRealPart().getNum() + a.getNum(), 
				C.getImaginaryPart().getNum() + bi.getNum()
		);
		
		return co;
	}
	
	public ComplexNumber substract(ComplexNumber C)
	{
		ComplexNumber co = new ComplexNumber(
				a.getNum() - C.getRealPart().getNum() , 
				bi.getNum() - C.getImaginaryPart().getNum()  
		);
		
		return co;
	}
	
	public ComplexNumber multiply(ComplexNumber C)
	{
		ComplexNumber co = new ComplexNumber(
				a.getNum()*C.getRealPart().getNum() - 
				bi.getNum()*C.getImaginaryPart().getNum() , 
				bi.getNum()*C.getRealPart().getNum() +
				a.getNum()*C.getImaginaryPart().getNum()
		);
		
		return co;
	}
	
	public ComplexNumber divide(ComplexNumber C)
	{
		
		ComplexNumber co = new ComplexNumber(
			(	a.getNum()*C.getRealPart().getNum() +
				bi.getNum()*C.getImaginaryPart().getNum() 
				)
			/ 
			(	Math.pow(C.getRealPart().getNum(), 2)  + 
				Math.pow(C.getImaginaryPart().getNum(), 2) 
				)
			, 
			(	a.getNum()*C.getImaginaryPart().getNum() +
				bi.getNum()*C.getRealPart().getNum() 
				)
			/ 
			(	Math.pow(C.getRealPart().getNum(), 2)  + 
				Math.pow(C.getImaginaryPart().getNum(), 2) 
				)
		);
		
		return co;
	}
	
	public ComplexNumber negate()
	{
		return new ComplexNumber(-this.a.getNum(),-this.bi.getNum() );
	}
	
	public ComplexNumber conjugate()
	{
		return new ComplexNumber(this.a.getNum(),- this.bi.getNum() );
	}
	
	public RealNumber norm()
	{
		RealNumber r = new RealNumber(
				Math.sqrt(a.getNum()*a.getNum() + bi.getNum()*bi.getNum() ) );
		return r;
	}
	
	public String toString()
	{
		String number = a.toString()+ " + " + bi.toString() + "i";
		
		return number;
	}
	
	public boolean equals(ComplexNumber co)
	{
		if(Math.abs(co.getRealPart().getNum()- a.getNum() ) < 1e-8 
				&& Math.abs(co.getImaginaryPart().getNum() - bi.getNum()) < 1e-8 )
		{
			return true;
		}
		else
			return false;
	}
	
	public RealNumber getRealPart()
	{
		return a;
	}
	
	public RealNumber getImaginaryPart()
	{
		return bi;
	}

}
