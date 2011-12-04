package de.matt3o.bruchrechner;

public class FractionException extends MathException {
	private static final long serialVersionUID = 0L;

	public FractionException() {
		super( "Bruch enthält eine 0!" );
	}
}
