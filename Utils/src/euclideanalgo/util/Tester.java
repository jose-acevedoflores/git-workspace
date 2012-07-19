package euclideanalgo.util;

public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		System.out.println(GreatestCommonDivisor.gcd(2740, 1760));

		System.out.println("Testing extended algorithm");

		int res[] = GreatestCommonDivisor.extendedGcd(161, 28);
		System.out.println(res[0]);
		System.out.println(res[1]);
		System.out.println(res[2]);

	}

}
