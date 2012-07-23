package euclideanalgo.util;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		System.out.println("GCD: "+GreatestCommonDivisor.gcd(2,11));

		System.out.println("Testing extended algorithm");

		int res[] = GreatestCommonDivisor.extendedGcd(3, 2);
		System.out.println(res[0]);
		System.out.println("s = "+res[1]);
		System.out.println("t = "+res[2]);
		
		System.out.println("Mult inverse: "+GreatestCommonDivisor.multiplicativeInverse(4, 16));

		//Figure 2.26 
		System.out.println((3*15+5*23+7*15+2*24)%26);
		
		System.out.println(-7%16);
	}

}
