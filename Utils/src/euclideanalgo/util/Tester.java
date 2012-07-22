package euclideanalgo.util;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		System.out.println("GCD: "+GreatestCommonDivisor.gcd(10, 8));

		System.out.println("Testing extended algorithm");

		int res[] = GreatestCommonDivisor.extendedGcd(3, 2);
		System.out.println(res[0]);
		System.out.println("s = "+res[1]);
		System.out.println("t = "+res[2]);

	}

}
