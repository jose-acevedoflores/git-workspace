package euclideanalgo.util;

/**
 * This class provides an implementation of the Euclidean Algorithm to find
 * the greates common divisor of two integer numbers.
 * @author jose
 *
 */
public class GreatestCommonDivisor {
	
	
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int gcd(long a, long b)
	{
		long r1 = Math.max(a, b);
		long r2 = Math.min(a, b);
		int temp =0;
		
		while(r2>0)
		{
			temp = (int) (r1%r2);
			
			r1 = r2;
			r2 = temp;
		}		
		return (int)r1;
		
	}
	
	/**
	 * This method takes the two parameters it receives and computes
	 * the greatest common divisor. It also computes the numbers 
	 * s and t such that s*a + b*t = gcd(a,b) 
	 * @param a the first number
	 * @param b the second number
	 * @return the gcd value (index: 0), the s value (index: 1), the t 
	 * value (index 2). 
	 */
	public static int[] extendedGcd(long a, long b)
	{
		long r1 = Math.max(a, b);
		long r2 = Math.min(a, b);
		long div=0;
		long s1=1, s2 =0 , t1=0, t2 =1;
		long s=0, t =0;
		long r =0;
		
		while(r2>0)
		{
			div =  (r1/r2);
			
			r = (r1%r2);
			r1 = r2;
			r2 = r;
			
			s =  s1 - div*s2;
			s1 = s2;
			s2 = s;
			
			t = t1 - div*t2;
			t1 = t2;
			t2 = t;
		}		
		
		int result[] = {(int) r1, (int) s1, (int)t1};
		return result;
		
	}
	
	public static int multiplicativeInverse(int b, int n)
	{
		if(gcd(26,11) != 1)
			return 0;
		
		long r1 = n;
		long r2 = b;
		long div=0;
		long t1=0, t2 =1;
		long t =0;
		long r =0;
		
		while(r2>0)
		{
			div =  (r1/r2);
			
			r = (r1%r2);
			r1 = r2;
			r2 = r;
			
			t = t1 - div*t2;
			t1 = t2;
			t2 = t;
		}		
		
		if(r1 == 1)
			return (int)t1; 
		
		return 0;
	}
}
