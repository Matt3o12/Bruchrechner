package de.matt3o.bruchrechner;

// import de.matt3o.bruchrechner.gui.Window;

public class Run {
	public static void main( String... args ){
		Logger.getLogger();		
//		new Window();
		
		
		try {
			Fraction b1 = new Fraction( 1, 2 );
			Fraction b2 = new Fraction( 6, 4 );
			
			System.out.println( b1.multiplicat( b2 ) );
		} catch ( MathException e ) {
			e.printStackTrace();
		}
	}
}
