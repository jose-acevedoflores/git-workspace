package tester;
import complexNumber.ComplexNumber;
import complexNumber.phasor.Phasor;


public class Tester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		test();
		
		 //Comment after added repo
		//circuit10_28();
		//circuit10_29();
		
		
	}

	private static void circuit10_29() {
		Phasor z1 = new Phasor(new ComplexNumber(4 , 3));
		Phasor load = new Phasor(new ComplexNumber(120, 90));
		Phasor vs = new Phasor(465, 0);
		
		Phasor vd = vs.multiply(z1.divide( z1.add(load) ));
		
		System.out.println("vd = "+vd);
		
		Phasor il = vs.divide(load.add(z1) );
		
		System.out.println("il = " + il);
		
		System.out.println("load = "+load);
		
		System.out.println("get reactance = " + (new Phasor(new ComplexNumber(120, 90) ) ).getAdmitance().getCuadraticForm() );
		
		Phasor vdc = vs.multiply(new Phasor(4,0).divide(new Phasor(new ComplexNumber(191.6,3))) );
		
		System.out.println("vdc = " + vdc);
	}

	private static void circuit10_28() {
		Phasor n = new Phasor(40 , 53.1 );
		Phasor n2 = new Phasor(96.3, -48.36);
		Phasor n3 = new Phasor(32, 0);
		
		Phasor result = n.add(n2);
		
		System.out.println("Il = "+result.add(n3));
		Phasor il = result.add(n3);
		
		Phasor i1 = new Phasor(24000, 48.36);
		
		Phasor v = new Phasor(250 , 0);
		
		System.out.println("I1 = " + new Phasor((i1.divide(v).getCuadraticForm() ).conjugate() ) );
		
		Phasor vl = il.multiply(new Phasor(new ComplexNumber(0.01, 0.08))).add(v) ;
		
		System.out.println("vl = " + vl);
		
		
		
		Phasor sl = vl.multiply(new Phasor( il.getCuadraticForm().negate().conjugate() ) ) ;
		
		//System.out.println(new Phasor( il.getCuadraticForm().negate().conjugate() ));
		
		System.out.println("sl = "+ sl);
	}

	private static void test() {
		Phasor n = new Phasor(54, 67);
		System.out.println(n);
		System.out.println("Standard form "+ n.getCuadraticForm());
		
		System.out.println("-------");
		n = new Phasor(new ComplexNumber( 54,76 ));
		System.out.println(n);
		System.out.println("Standard form "+ n.getCuadraticForm());
		
		System.out.println("-------");
		n = new Phasor(new ComplexNumber( 65.023, -89.92 ));
		System.out.println(n);
		System.out.println("Standard form "+ n.getCuadraticForm());		
		
		System.out.println("-------");
		n = new Phasor(new ComplexNumber( -95.023, 89.92 ));
		System.out.println(n);
		System.out.println("Standard form "+ n.getCuadraticForm());
		
		System.out.println("-------");
		n = new Phasor(new ComplexNumber( -90.023, -70.92 ));
		System.out.println(n);
		System.out.println("Standard form "+ n.getCuadraticForm());
	}

}
