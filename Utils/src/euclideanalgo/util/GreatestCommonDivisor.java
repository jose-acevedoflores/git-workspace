package euclideanalgo.util;

/**
 * This class provides an implementation of the Euclidean Algorithm to find
 * the greates common divisor of two integer numbers.
 * @author jose
 *
 */
public class GreatestCommonDivisor {
	
	public static long gcd(long a, long b)
	{
		long r1 = Math.max(a, b);
		long r2 = Math.min(a, b);
		long temp =0;
		
		while(r2>0)
		{
			temp = r1%r2;
			
			r1 = r2;
			r2 = temp;
		}		
		return r1;
		
	}
	
}
