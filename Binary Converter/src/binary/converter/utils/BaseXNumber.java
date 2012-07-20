package binary.converter.utils;


/**
 * This class saves a number of any of the four following bases: 2, 8, 10 ,16.
 * @author 
 *
 */
public class BaseXNumber {
	
	private long numberDecimal;
	private String originalNumber;
	private boolean binary=false;
	private boolean hexadecimal=false;
	//private boolean decimal=false;
	private boolean octal=false;
	
	
	
	/**
	 * Creates a new number, saves the original input, save the base the user gave and changes the number to a decimal.
	 * An example would be the user said it wanted a hexadecimal number and gave "e4" as an input. The original input is saved 
	 * and "e4" is converted to decimal for the methods to operate.
	 * @param base the base the user wants to save.
	 */
	public BaseXNumber(String base, String number)
	{
		if(number.isEmpty())
			number = "0";
		
		originalNumber = number.toLowerCase();
		
		
		if(base.equalsIgnoreCase("hexadecimal"))
		{
			hexadecimal = true;
			numberDecimal = this.hexadecimalToDecimal();
		}
		
		else if(base.equalsIgnoreCase("binary"))
		{
			binary = true;
			numberDecimal = this.binaryToDecimal();
		}
		
		else if(base.equalsIgnoreCase("octal"))
		{
			octal = true;
			numberDecimal= this.octalToDecimal();
		}
		
		else if(base.equalsIgnoreCase("decimal"))
		{
		//	decimal = true;
			numberDecimal = this.validDecimalToDecimal();
		}
	}
	
	/**
	 * constructor for custom bases from 3 to 9
	 */
	public BaseXNumber(int base, String number )
	{
		originalNumber = number.toLowerCase();
		numberDecimal = this.customToDecimal(base);
	}

	
	
	/** 
	 * Method 1
	 * @author-Wesley O. Martinez Camacho
	 * 
	 *  A method that verifies if the number entered by the user is or it is not prime 
	 * @return  if the number is or it is not prime. 
	 */
	public boolean isPrime()
	{ 
		boolean result=false; 
		for ( int i = 2; i<numberDecimal; i++)
		{ 
			long prime = numberDecimal%i;
			if (prime == 0)
			{
				result = false; 
			    break;
			}
			
			else if (prime != 0)
				result = true;
		}
		return result; 
	}
	
	
	/** 
	 * Method 2
	 * @author-Wesley O. Martinez Camacho 
	 * 
	 * A method that verifies if the number given by the user is a power of two. 
	 * @return if the number is a power of two.
	 */
	public boolean isPowerOfTwo()
	{ 
		boolean result =false; 
		double power = 0; 
		
		for (int i = 0; i<numberDecimal; i++)
		{ 
		  power = Math.pow(2,i);
		 
		 if (numberDecimal % power ==0 && power != 1 )
		 {
			 result = true;
			 break;    
		 }
		 
		 else if (numberDecimal % power != 0 && power != 1)
			 result = false;
		}
		return  result; 
	}
	
	/** 
	 * Method 3
	 * @author-Wesley O. Martinez Camacho 
	 * 
	 * A method that calculated the greatest common divisor between the first number and a new input number. 
	 * @param number the number that is going to be used to look for the GCD
	 * @return the greatest common divisor between the numbers 
	 */
	public int getGCD(long number) 
	{ 
	  int gcd = 0;
	  
	  long minNumber = Math.min(number, numberDecimal);
	  
	  for (int i = 1; i<= minNumber; i++)
	  { 
		  if (numberDecimal % i ==0 && number % i ==0)
			  gcd=i; 
	  }
	  return gcd; 
	}
	
	/** 
	 * Method 4
	 * @author-Wesley O. Martinez Camacho
	 * 
	 * A method that verifies if the originalNumber and a new number (both entered by the user) are co-prime.
	 * The method first has to calculate if the new number is prime or not. After it verifies that it follows 
	 * to see if both numbers are co-primes.
	 * @param number the number that the user wants to verifies if its co-prime
	 * @return if both numbers are coprime 
	 */
	public boolean isCoPrime(long number)
	{ 
		if(this.getGCD(number) == 1)
		{
			return true;
		}
		else 
			return false;
	}
	
	
	/** 
	 * Method 5
	 * @author-Wesley O. Martinez Camacho
	 * 
	 * A method that calculates the amount of times a given digit  (given by the user)  repeats. 
	 * @param digit The number that the user wants to know how many times it repeats 
	 * @return the amount of times the number repeats 
	 */
	public int getTimesitRepeats(String digit)
	{ 
	   int repeats = 0; 
	   
	   for (int i = 0 ; i < originalNumber.length() ; i++)
	   { 
		  if (originalNumber.substring(i, i+1).equals(digit))
			  repeats++;
	   }
	  
	   return repeats;
	}
	

	/**
	 * Method 6
	 * @author Jose L. Acevedo Flores
	 * Computes the binary representation of the given number.
	 * @return the binary representation.
	 */
	public String toBinary()
	{
		if(numberDecimal == -1)
			return "-1";
		
		if(binary)
			return originalNumber;
		else
		{
			String binaryRepReversed =""; 
			int res = 0;
			long num = numberDecimal;
			// Since this while loop gets the binary representation in reverse order
			// where 2^0 is the leftmost digit not the rightmost the for loop below 
			// is used to correct this.
			while(num>0) 			
			{			
				res = (int) num%2;
				binaryRepReversed += res;
				num /= 2; 
			}
			
			String binaryRep = "";
			for(int i = binaryRepReversed.length() -1 ; i>=0; i--) //Reverse the number
			{
				binaryRep += binaryRepReversed.charAt(i);
			}
			
			return binaryRep;		
		}
	}

	
	/**
	 * Method 7
	 * @author Jose L. Acevedo Flores
	 * Computes the octal representation of the given number.
	 * @return the octal representation.
	 */
	public String toOctal()
	{
		if(numberDecimal == -1)
			return "-1";
		
		if(octal)
			return originalNumber;
		else
		{
			String octalReversed = "";
			int res = 0;
			long num = numberDecimal;
			while(num>0)
			{
				res = (int) num%8;
				octalReversed += res;
				num /= 8;
			}
			
			String octalRep="";
			for(int i = octalReversed.length()-1; i >=0 ; i--)
			{
				octalRep += octalReversed.charAt(i);
			}
			
			return octalRep;
		}
	}
	
	/**
	 * Method 8
	 * @author Jose L. Acevedo Flores
	 * Computes the decimal representation of the given number.
	 * @return the decimal representation.
	 */
	public String toDecimal()
	{
		return String.valueOf(numberDecimal);
	}
	
	/**
	 * Method 9
	 * @author Jose L. Acevedo Flores
	 * Computes the hexadecimal representation of the given number.
	 * @return the hexadecimal representation.
	 */
	public String toHexadecimal()
	{
		if(numberDecimal == -1)
			return "-1";
		
		if(hexadecimal)
			return originalNumber;
		else
		{
			char[] hex = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
			//This algorithm gets the digits in reverse order that's why everything is saved in hex reversed.
			String hexReversed ="";
			long num = numberDecimal;
			int res=0;
			while(num>0)
			{
				res = (int) num%16;
				hexReversed += hex[res];
				num /=16;
			}
			
			//Here we set the hexReversed in the correct order.
			String hexRep="";
			for(int i = hexReversed.length()-1; i >=0 ; i--)
			{
				hexRep += hexReversed.charAt(i);
			}
			
			return hexRep;
		}
	}
	
	/**
	 * 
	 * @param base
	 * @return
	 */
	public String toCustom(int base)
	{		
		if(base <= 0)
			return originalNumber;
		
		if(numberDecimal == -1)
			return "-1";
		
		String number ="";
		long num = numberDecimal;
		int res=0;
		while(num>0)
		{
			res = (int) num % base;
			number += res;
			num /= base;
		}
		
		String numberCorrect="";
		for(int i = number.length()-1 ; i >=0 ; i--  )
			numberCorrect += number.charAt(i);
		
		return numberCorrect;
	}
	
	
	/**
	 * Method 10
	 * @author Jose L. Acevedo Flores
	 * Gets the digits of the decimal representation of the given number in reverse order.
	 * @return the decimal representation in reverse.
	 */
	public String reverse()
	{
		String numberDecimal = String.valueOf(this.numberDecimal);
		String reverse = "";
		for(int i =  numberDecimal.length() -1 ; i>=0; i--)
		{
			reverse += numberDecimal.charAt(i);
		}
		
		return reverse;
	}
	
		
	/** 
	 * Mehotd 11
	 * @author-Wesley O. Martinez Camacho 
	 * 
	 * A method that calculates the sum of all the odd numbers up to the  the user input number 
	 * @return the sum of all odd numbers. 
	 */
	public long getSumofOddNumbers()
	{ 
	  long sum = 0; 
	  
	  for (int i = 0; i < numberDecimal; i++)
	  { 
		  if ( i % 2 != 0)
			  sum += i; 
	  }
	  return sum; 
	}
	
	
	/** 
	 * Method 12
	 * @author-Wesley O. Martinez Camacho
	 * 
	 * A method that calculates the sum of all the even numbers up to the user input number.
	 * @return the sum of all even numbers.
	 */
	public long getSumofEvenNumbers()
	{ 
	  long sum = 0; 
	  
	  for (int i = 0; i < numberDecimal; i++)
	  { 
		  if (i % 2 == 0)
			  sum += i; 
	  }
	  return sum; 	
	}
	
	
	
	/**
	 * @author Jose L. Acevedo Flores
	 * Gets the number in the decimal representation. 
	 * @return the number in the decimal representation.
	 */
	public long getNumberDecimal()
	{
		return numberDecimal;
	}
	
	/*
	 * From here on the methods are private. This is to convert the number the user gave as in input 
	 * and save it's decimal representation for all the methods.
	 */
	
	/**
	 * @author Jose L. Acevedo Flores.
	 * Convert the number form binary to decimal. Private because it's for the internal representation.
	 * @return the binary number in decimal form or -1 if the number is illegal.
	 */
	private long binaryToDecimal()
	{
		int flag = 0;
		//Here we check if the number has any illegal characters.
		for(int u = 0 ; u < originalNumber.length(); u++)// Checking for illegal characters.
		{
			if(!(originalNumber.charAt(u)=='0') && !(originalNumber.charAt(u)=='1'))
				flag=1;
		}
		
		if( flag==1 )
			return -1;
		
		else // Converting the number from binary to decimal
		{
			long result=0;
			int counter=0;
			for(int i = originalNumber.length()-1; i >= 0  ; i--)
			{
				if(originalNumber.charAt(i)== '1')
				{
					result +=  Math.pow(2, counter);
				}
				counter++;
			}
			return result;
		}
	}
	
	
	/**
	 * @author Jose L. Acevedo Flores.
	 * Convert the number form octal to decimal. Private because it's for the internal representation. 
	 * @return the octal number in decimal form or -1 if the number is illegal.
	 */
	private long octalToDecimal()
	{
		int flag;
		char[] octal = {'0','1','2','3','4','5','6','7'};
		for(int u = 0 ; u < originalNumber.length(); u++)
		{
			flag =1;
			for(int i = 0 ; i < octal.length; i++)
			{
				if(originalNumber.charAt(u) == octal[i] )
					flag = 0;
			}
			if(flag==1)
				return -1;
		}
	
		//Converting the number from octal to decimal.
		long result=0;
		long number;
		int u;
		int counter=0;
		for(int i = originalNumber.length()-1; i >= 0  ; i--)
		{
			for(u =0; originalNumber.charAt(i) != octal[u]; u++)
			{}//Loops through the octal array. The index represents the decimal representation. 
			number = u;  
			result += number*Math.pow(8, counter);
			counter++;
		}

		return result;
		
	}
	
	
	/**
	 * @author Jose L. Acevedo Flores.
	 * Convert the number form hexadecimal to decimal. Private because it's for the internal representation. 
	 * @return the hexadecimal number in decimal form or -1 if the number is illegal.
	 */
	private long hexadecimalToDecimal()
	{
		int flag;
		char[] hex = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
		
		//Here we check if the number has any illegal characters.
		for(int u = 0 ; u < originalNumber.length(); u++)
		{
			flag =1;
			for(int i = 0 ; i < hex.length; i++)
			{
				if(originalNumber.charAt(u) == hex[i] )
					flag = 0;
			}
			if(flag==1)
				return -1;
		}
		
		//Converting the number from hexadecimal to decimal.
		long result=0;
		long number;
		int u;
		int counter=0;
		for(int i = originalNumber.length()-1; i >= 0  ; i--)
		{
			for(u =0; originalNumber.charAt(i) != hex[u]; u++)
			{}			//Loops through the hex array until a match is found,
			number = u; // then the index represents the decimal representation.  
			result += number*Math.pow(16, counter);
			counter++;
		}
	
		return result;
	}
	
	/**
	 * @author Jose L. Acevedo Flores.
	 * Checks if the user input is correct for a decimal number. Example: no letters and symbols.
	 * @return -1 if illegal character is found, the real decimal number otherwise.
	 */
	private long validDecimalToDecimal()
	{
		
		int flag;
		char[] validInput = {'0','1','2','3','4','5','6','7','8','9'};
		
		//Here we check if the number has any illegal characters.
		for(int u = 0; u < originalNumber.length() ; u++)
		{
			flag=1;
			for(int i = 0; i< validInput.length; i++)
			{
				if(originalNumber.charAt(u) == validInput[i])
					flag = 0;
			}
			if(flag == 1)
				return -1;
		}
		
		return Long.parseLong(originalNumber);
	}
	
	/**
	 * @author Jose L. Acevedo Flores.
	 * Checks if the user input is correct for a number of the given base. Example: no letters and symbols and all numbers less than the base.
	 * @return -1 if illegal character is found, the real decimal number otherwise.
	 */
	private long customToDecimal(int base)
	{
		if(base <= 0 )
			return -1;
		int flag;
		
		char[] validInput = new char[base];
		
		for(int d=0; d < base; d++)
		{
			validInput[d] = Integer.toString(d).charAt(0); // Array that contains the valid inputs															// from 0 to the base minus one. 
		}		
		
		//Here we check if the number has any illegal characters.
		for(int u = 0; u < originalNumber.length() ; u++)
		{
			flag=1;
			for(int i = 0; i< validInput.length; i++)
			{
				if(originalNumber.charAt(u) == validInput[i])
					flag = 0;
			}
			if(flag == 1)
				return -1;
		}
		
		long resultDecimal = 0;
		int power = 0;
		//if we pass to these section then no illegal characters were found.
		for(int i = originalNumber.length()-1; i >=0; i--)
		{
			int numberAtPosition = Integer.parseInt(String.valueOf(originalNumber.charAt(i)));
			
			resultDecimal +=  numberAtPosition*Math.pow(base, power); 
			power++;
		}
		
		return resultDecimal;
	}
	

}
