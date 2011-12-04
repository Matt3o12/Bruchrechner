package de.matt3o.bruchrechner;

public class MathException extends Exception {
	private static final long serialVersionUID = -6917201719943911789L;

	public MathException( String message ) {
		super( "Math-Error: " + message );
	}
}
