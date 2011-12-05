package de.matt3o.bruchrechner;

public class Fraction {
	/** Deutsch: Zähler */
	private int numerator;
	
	/** Deutsch: Nenner */
	private int denominator;
	
	/**
	 * Erzeugt eine neue Fraction Instance
	 * 
	 * @param numerator Der Zähler
	 * @param denominator Der Nenner
	 * @throws FractionException Wird ausgelöst, wenn der Nenner oder Zähler eine 0 enthält.
	 */
	public Fraction( int numerator, int denominator ) throws FractionException {
		if ( numerator == 0 || denominator == 0 ){
			throw new FractionException();
		}
		
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	/**
	 * Gibt den numerator (Zähler) zurück.
	 * 
	 * @return numerator (Zähler)
	 */
	public int getNumerator(){
		return numerator;
	}
	
	/**
	 * Gibt den denominator (Nenner) zurück.
	 * @return denominator (Nenner)
	 */
	public int getDenominator(){
		return denominator;
	}
	
	/**
	 * Gibt das GGF zurück.
	 * 
	 * @author http://de.wikipedia.org/wiki/Euklidischer_Algorithmus#Rekursive_Variante
	 * @return Den GGF
	 */
	private int getGGF( int a, int b ){
		if ( b == 0 )
			return a;
		else
			return getGGF( b, a % b );
	}
	
	/**
	 * Gibt das GGF zurück
	 * @return Den GGF
	 */
	public int getGGF(){
		return getGGF( getNumerator(), getDenominator() );
	}
	
	/**
	 * Kürtzt den Aktuellen bruch.
	 * 
	 * @return Ein neuer - aber gekürztz Bruch.
	 */
	public Fraction trim(){
		int ggf = getGGF();
		Fraction resul;
		try {
			resul = new Fraction( getNumerator()/ggf , getDenominator()/ggf );
		} catch (FractionException e) {
			e.printStackTrace();
			return null;
		}
		
		return resul;
	}
	
	
	/**
	 * Addiert 2 Brüche miteinander
	 * Es können nur Brüche miteinander Multipliziert werden, wenn die Zähler gleich sein!
	 * 
	 * @param summand2 Den Wert, mit dem der Bruch Addiert werden soll.
	 * @return Ein neuen Bruch, wo beide Brüche Addiert werden.
	 * @throws FractionException Wird geworfen, wenn der neue Bruch eine 0 enthält.
	 * @throws MathException Wird geworfen, wenn nenner und zähler ungleich sind.
	 */
	public Fraction addition( Fraction summand2 ) throws FractionException, MathException{
		int numerator;
		int denominator;
		
		if ( summand2.getDenominator() == this.getDenominator() ){
			numerator = summand2.getNumerator() + this.getNumerator();
			denominator = this.getDenominator();
			return new Fraction( numerator, denominator ).trim();
		}
		
		throw new MathException( "Die beiden Nenner müssen gleich sein!" );
	}
	
	/**
	 * Subtrahiert 2 Brüche miteinander.
	 * 
	 * @param subtrahend Der Subtrahend (2. Zahl, mit der der Bruch Subtrahiert wird).
	 * @return Ein neuen Bruch, wo beide Brüche Addiert werden.
	 * @throws FractionException Wird geworfen, wenn der neue Bruch eine 0 enthält.
	 * @throws MathException Wird geworfen, wenn nenner und zähler ungleich sind.
	 */
	public Fraction subtract( Fraction subtrahend ) throws FractionException, MathException{
		if ( subtrahend.getDenominator() == this.getDenominator() ){
			numerator = this.getNumerator() - subtrahend.getNumerator();
			denominator = this.getDenominator();
			
			return new Fraction( numerator, denominator );
		}
		
		throw new MathException( "Die beiden Nenner müssen gleich sein!" );
	}
	
	/**
	 * Multipliziert die beiden Brüche miteinander.
	 * 
	 * @param multiplier Der Multiplikator (2. Zahl, mit der der Bruch Multipliziert wird.)
	 * @return Ein neuer Burch, wo beide Brüche Multipliziert werden.
	 * @throws FractionException Wird geworfen, wenn der neue Bruch eine 0 enthält.
	 */
	public Fraction multiplicat( Fraction multiplier ) throws FractionException{
		int numerator = multiplier.getNumerator() * this.getNumerator();
		int denominator = multiplier.getDenominator() * this.getDenominator();
		
		return new Fraction( numerator, denominator ).trim();
	}
	
	/**
	 * Dividiert die beiden Brüche miteinander.
	 * @param divisor Der Divisor (2. Zahl, mit der der Bruch Division wird.)
	 * @return Ein neuer Bruch, wo beide Brüche Multipliziert werden.
	 * @throws FractionException Wird geworfen, wenn ein neuer Bruch eine 0 enthält.
	 */
	public Fraction division( Fraction divisor ) throws FractionException{
		Fraction reciprocalValue = new Fraction( divisor.getDenominator(), divisor.getNumerator() );
		
		return multiplicat( reciprocalValue ).trim();
	}
		
	@Override
	public String toString(){
		return "Fraction: [" + numerator + "/" + denominator + "]";
	}
}
