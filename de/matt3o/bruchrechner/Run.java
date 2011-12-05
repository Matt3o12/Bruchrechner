package de.matt3o.bruchrechner;

import de.matt3o.bruchrechner.gui.Window;

public class Run {
	public static void main( String... args ){
		Logger.getLogger();		
		new Window();
		
		
		try {
			Fraction b1 = new Fraction( 1, 3 );
			Fraction b2 = new Fraction( 1, 3 );
			
			System.out.println( b1.addition( b2 ) );
		} catch ( MathException e) {
			e.printStackTrace();
		}
	}
}
